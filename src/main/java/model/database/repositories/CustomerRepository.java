package model.database.repositories;

import model.database.DatabaseManager;
import model.exceptions.DatabaseOperationException;
import model.users.Customer;
import model.users.CustomerFactory;
import model.users.UserFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements IRepository<Customer> {
    private final static DatabaseManager databaseManager = DatabaseManager.getInstance();
    private final static ShoppingCartRepository shoppingCartRepo = new ShoppingCartRepository();
    private final static PreferencesRepository preferencesRepo = new PreferencesRepository();
    private final static AddressRepository addressRepo = new AddressRepository();
    private final static OrderRepository orderRepo = new OrderRepository();

    @Override
    public Customer getById(int id) {
        String query = "SELECT * FROM Customers c " +
                "INNER JOIN Users u ON c.customerId = u.id " +
                "LEFT JOIN Preferences p ON c.preferencesId = p.id " +
                "LEFT JOIN ShoppingCarts s ON c.shoppingCartId = s.cartId " +
                "WHERE c.customerId = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToCustomer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getByUsername(String username) {
        String query = "SELECT * FROM Customers c " +
                "INNER JOIN Users u ON c.customerId = u.id " +
                "LEFT JOIN Preferences p ON c.preferencesId = p.id " +
                "LEFT JOIN ShoppingCarts s ON c.shoppingCartId = s.cartId " +
                "WHERE u.username = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToCustomer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no customer is found
    }

    @Override
    public List<Customer> getAll() {
        String query = "SELECT * FROM Customers c " +
                "INNER JOIN Users u ON c.customerId = u.id " +
                "LEFT JOIN Preferences p ON c.preferencesId = p.id " +
                "LEFT JOIN ShoppingCarts s ON c.shoppingCartId = s.cartId";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                customers.add(mapResultSetToCustomer(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void save(Customer customer) throws DatabaseOperationException {
        String userQuery = "INSERT INTO Users (userType, username, fullName, email, phoneNumber, hashedPassword, balance) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, customer.getUserType());
            preparedStatement.setString(2, customer.getUsername());
            preparedStatement.setString(3, customer.getFullName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getHashedPassword());
            preparedStatement.setDouble(7, customer.getBalance());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1); // Retrieve the generated user ID
                customer.setId(userId);
                // Insert into Customers table
                String customerQuery = "INSERT INTO Customers (customerId, preferencesId, shoppingCartId) VALUES (?, ?, ?)";
                try (PreparedStatement customerStmt = connection.prepareStatement(customerQuery)) {
                    customerStmt.setInt(1, userId);
                    customerStmt.setObject(2, (customer.getPreferences() == null) ? null : customer.getPreferences().getId(), java.sql.Types.INTEGER);
                    customerStmt.setObject(3, (customer.getShoppingCart() == null) ? null : customer.getShoppingCart().getCartId(), java.sql.Types.INTEGER);
                    customerStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while saving customer in method 'CustomerRepository.save()'", e);
        }
    }

    @Override
    public void update(Customer entity) {
        String query = "UPDATE Users SET username = ?, fullName = ?, email = ?, phoneNumber = ?, hashedPassword = ?, balance = ? WHERE id = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getFullName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPhoneNumber());
            preparedStatement.setString(5, entity.getHashedPassword());
            preparedStatement.setDouble(6, entity.getBalance());
            preparedStatement.setInt(7, entity.getId());

            preparedStatement.executeUpdate();

            // Update Customers table
            String customerQuery = "UPDATE Customers SET preferencesId = ?, shoppingCartId = ? WHERE customerId = ?";
            try (PreparedStatement customerStmt = connection.prepareStatement(customerQuery)) {
                customerStmt.setInt(1, entity.getPreferences().getId());
                customerStmt.setInt(2, entity.getShoppingCart().getCartId());
                customerStmt.setInt(3, entity.getId());
                customerStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Users WHERE id = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("customerId");
        //String userType = resultSet.getString("userType");
        String username = resultSet.getString("username");
        String fullName = resultSet.getString("fullName");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phoneNumber");
        String hashedPassword = resultSet.getString("hashedPassword");
        double balance = resultSet.getDouble("balance");

        // Preferences and ShoppingCart data
        int preferencesId = resultSet.getInt("preferencesId");
        int shoppingCartId = resultSet.getInt("shoppingCartId");

        UserFactory factory = new CustomerFactory();
        Customer.CustomerBuilder builder = (Customer.CustomerBuilder) factory.create(customerId, username, fullName, email, phoneNumber, hashedPassword);
        builder.balance(balance)
                .preferences(preferencesRepo.getById(preferencesId))
                .shoppingCart(shoppingCartRepo.getById(shoppingCartId))
                .savedAddresses(addressRepo.getByOwnerId(customerId))
                .orderHistory(orderRepo.getByCustomerId(customerId))
        ;

        return builder.build();
    }
}
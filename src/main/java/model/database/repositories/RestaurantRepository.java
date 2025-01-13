package model.database.repositories;

import model.database.DatabaseManager;
import model.exceptions.DatabaseConnectionException;
import model.exceptions.DatabaseOperationException;
import model.users.Restaurant;
import model.users.RestaurantFactory;
import model.users.UserFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository implements IRepository<Restaurant> {
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
    private static final ItemRepository itemRepo = new ItemRepository();

    @Override
    public Restaurant getById(int id) throws DatabaseOperationException {
        String query = "SELECT * FROM Restaurants r " +
                "INNER JOIN Users u ON r.restaurantId = u.id " +
                "WHERE r.restaurantId = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToRestaurant(resultSet);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while fetching a restaurant in method 'RestaurantRepository.getById()'.", e);
        }
        return null;
    }

    @Override
    public List<Restaurant> getAll() throws DatabaseConnectionException {
        String query = "SELECT * FROM Restaurants r " +
                "INNER JOIN Users u ON r.restaurantId = u.id";
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                restaurants.add(mapResultSetToRestaurant(resultSet));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while fetching all restaurants in method 'RestaurantRepository.getAll()'.", e);
        }
        return restaurants;
    }

    @Override
    public void save(Restaurant entity) throws DatabaseConnectionException {
        String userQuery = "INSERT INTO Users (userType, username, fullName, email, phoneNumber, hashedPassword, balance) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Insert into Users table
            preparedStatement.setString(1, entity.getUserType());
            preparedStatement.setString(2, entity.getUsername());
            preparedStatement.setString(3, entity.getFullName());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getPhoneNumber());
            preparedStatement.setString(6, entity.getHashedPassword());
            preparedStatement.setDouble(7, entity.getBalance());

            preparedStatement.executeUpdate();

            // Retrieve the generated user ID
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1); // Get the generated userId
                entity.setId(userId);

                // Insert into Restaurants table
                String restaurantQuery = "INSERT INTO Restaurants (restaurantId, restaurantCategory, restaurantName, description, " +
                        "location, rating, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement restaurantStmt = connection.prepareStatement(restaurantQuery)) {
                    restaurantStmt.setInt(1, userId); // Use userId as the restaurantId
                    restaurantStmt.setString(2, entity.getRestaurantCategory());
                    restaurantStmt.setString(3, entity.getRestaurantName());
                    restaurantStmt.setString(4, entity.getDescription());
                    restaurantStmt.setString(5, entity.getLocation());
                    restaurantStmt.setFloat(6, entity.getRating());
                    restaurantStmt.setString(7, entity.getImage());

                    restaurantStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while saving a restaurant in method 'RestaurantRepository.save()'.", e);
        }
    }

    @Override
    public void update(Restaurant entity) throws DatabaseOperationException {
        String query = "UPDATE Restaurants SET restaurantCategory = ?, restaurantName = ?, description = ?, " +
                "location = ?, rating = ?, image = ? WHERE restaurantId = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, entity.getRestaurantCategory());
            preparedStatement.setString(2, entity.getRestaurantName());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setString(4, entity.getLocation());
            preparedStatement.setFloat(5, entity.getRating());
            preparedStatement.setString(6, entity.getImage());
            preparedStatement.setInt(7, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while updating a restaurant in method 'RestaurantRepository.update()'.", e);
        }
    }

    @Override
    public void delete(int id) throws DatabaseOperationException {
        String deleteRestaurantQuery = "DELETE FROM Restaurants WHERE restaurantId = ?";
        String deleteUserQuery = "DELETE FROM Users WHERE id = ?";

        try (Connection connection = databaseManager.getConnection()) {
            // delete the restaurant
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteRestaurantQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }

            // delete the user
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUserQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while deleting a restaurant in method 'RestaurantRepository.delete()'.", e);
        }
    }

    private Restaurant mapResultSetToRestaurant(ResultSet resultSet) throws DatabaseOperationException {
        try {
            int id = resultSet.getInt("restaurantId");
            String restaurantCategory = resultSet.getString("restaurantCategory");
            String restaurantName = resultSet.getString("restaurantName");
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            float rating = resultSet.getFloat("rating");
            String image = resultSet.getString("image");

            // User data
            String username = resultSet.getString("username");
            String fullName = resultSet.getString("fullName");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phoneNumber");
            double balance = resultSet.getDouble("balance");
            String hashedPassword = resultSet.getString("hashedPassword");

            UserFactory factory = new RestaurantFactory();
            Restaurant.RestaurantBuilder builder = (Restaurant.RestaurantBuilder) factory.create(id, username, fullName, email, phoneNumber, hashedPassword);
            builder.restaurantCategory(restaurantCategory)
                    .balance(balance)
                    .restaurantName(restaurantName)
                    .description(description)
                    .location(location)
                    .rating(rating)
                    .image(image);

            return builder.build();
        }
        catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while mapping a restaurant in method 'RestaurantRepository.mapResultSetToRestaurant()'.", e);
        }
    }
}

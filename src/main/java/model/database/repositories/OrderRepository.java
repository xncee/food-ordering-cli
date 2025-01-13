package model.database.repositories;

import model.Order;
import model.Promo;
import model.ShoppingCart;
import model.database.DatabaseManager;

import java.math.BigDecimal;
import java.util.List;

import model.exceptions.DatabaseOperationException;
import model.users.Customer;
import model.users.Restaurant;

import java.sql.*;
import java.util.ArrayList;

public class OrderRepository implements IRepository<Order> {
    private final static DatabaseManager databaseManager = DatabaseManager.getInstance();
    private final static RestaurantRepository restaurantRepo = new RestaurantRepository();
    private final static CustomerRepository customerRepo = new CustomerRepository();
    private final static DeliveryRepository deliveryRepo = new DeliveryRepository();
    private final static ShoppingCartRepository shoppingCartRepo = new ShoppingCartRepository();
    private final static PromoRepository promoRepo = new PromoRepository();
    private final static ReviewRepository reviewRepo = new ReviewRepository();

    @Override
    public Order getById(int id) throws DatabaseOperationException {
        String query = "SELECT * FROM Orders o " +
                "INNER JOIN Restaurants r ON o.restaurantId = r.restaurantId " +
                "INNER JOIN Customers c ON o.customerId = c.customerId " +
                "WHERE o.orderId = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToOrder(resultSet);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while fetching an order by ID.", e);
        }
        return null;
    }

    public List<Order> getByCustomerId(int customerId) throws DatabaseOperationException {
        String query = "SELECT * FROM Orders o " +
                "INNER JOIN Restaurants r ON o.restaurantId = r.restaurantId " +
                "INNER JOIN Customers c ON o.customerId = c.customerId " +
                "WHERE o.customerId = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orders.add(mapResultSetToOrder(resultSet));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while fetching orders by customer ID.", e);
        }
        return orders;
    }

    @Override
    public List<Order> getAll() throws DatabaseOperationException {
        String query = "SELECT * FROM Orders o " +
                "INNER JOIN Restaurants r ON o.restaurantId = r.restaurantId " +
                "INNER JOIN Customers c ON o.customerId = c.customerId";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                orders.add(mapResultSetToOrder(resultSet));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while fetching all orders.", e);
        }
        return orders;
    }

    @Override
    public void save(Order entity) throws DatabaseOperationException {
        String query = "INSERT INTO Orders (restaurantId, customerId, deliveryId, orderType, shoppingCartId, " +
                "promoCode, discount, total, paymentMethod, paymentStatus, placedAt, status, isCanceled, cancellationReason) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, entity.getRestaurantId());
            preparedStatement.setInt(2, entity.getCustomerId());
            preparedStatement.setInt(3, entity.getDeliveryId());
            preparedStatement.setString(4, entity.getOrderType());
            preparedStatement.setInt(5, entity.getShoppingCart().getCartId());
            preparedStatement.setString(6, entity.getPromoCode().getCode());
            preparedStatement.setDouble(7, entity.getDiscount());
            preparedStatement.setBigDecimal(8, BigDecimal.valueOf(entity.getTotal()));
            preparedStatement.setString(9, entity.getPaymentMethod());
            preparedStatement.setString(10, entity.getPaymentStatus());
            preparedStatement.setTimestamp(11, Timestamp.valueOf(entity.getPlacedAt()));
            preparedStatement.setString(12, entity.getStatus());
            preparedStatement.setBoolean(13, entity.isCanceled());
            preparedStatement.setString(14, entity.getCancellationReason());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while saving an order.", e);
        }
    }

    @Override
    public void update(Order entity) throws DatabaseOperationException {
        String query = "UPDATE Orders SET restaurantId = ?, customerId = ?, deliveryId = ?, orderType = ?, " +
                "shoppingCartId = ?, promoCode = ?, discount = ?, total = ?, paymentMethod = ?, paymentStatus = ?, " +
                "placedAt = ?, status = ?, isCanceled = ?, cancellationReason = ? WHERE orderId = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, entity.getRestaurantId());
            preparedStatement.setInt(2, entity.getCustomerId());
            preparedStatement.setInt(3, entity.getDeliveryId());
            preparedStatement.setString(4, entity.getOrderType());
            preparedStatement.setInt(5, entity.getShoppingCart().getCartId());
            preparedStatement.setString(6, entity.getPromoCode().getCode());
            preparedStatement.setDouble(7, entity.getDiscount());
            preparedStatement.setDouble(8, entity.getTotal());
            preparedStatement.setString(9, entity.getPaymentMethod());
            preparedStatement.setString(10, entity.getPaymentStatus());
            preparedStatement.setTimestamp(11, Timestamp.valueOf(entity.getPlacedAt()));
            preparedStatement.setString(12, entity.getStatus());
            preparedStatement.setBoolean(13, entity.isCanceled());
            preparedStatement.setString(14, entity.getCancellationReason());
            preparedStatement.setInt(15, entity.getOrderId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while updating an order.", e);
        }
    }

    @Override
    public void delete(int id) throws DatabaseOperationException {
        String query = "DELETE FROM Orders WHERE orderId = ?";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while deleting an order.", e);
        }
    }

    private Order mapResultSetToOrder(ResultSet resultSet) throws DatabaseOperationException {
        try {
            int orderId = resultSet.getInt("orderId");
            int restaurantId = resultSet.getInt("restaurantId");
            int customerId = resultSet.getInt("customerId");
            int deliveryId = resultSet.getInt("deliveryId");
            String orderType = resultSet.getString("orderType");
            int shoppingCartId = resultSet.getInt("shoppingCartId");
            String promoCode = resultSet.getString("promoCode");
            double discount = resultSet.getDouble("discount");
            double total = resultSet.getDouble("total");
            String paymentMethod = resultSet.getString("paymentMethod");
            String paymentStatus = resultSet.getString("paymentStatus");
            Timestamp placedAt = resultSet.getTimestamp("placedAt");
            String status = resultSet.getString("status");
            boolean isCanceled = resultSet.getBoolean("isCanceled");
            String cancellationReason = resultSet.getString("cancellationReason");

            // Fetch related entities
            Restaurant restaurant = restaurantRepo.getById(restaurantId);
            Customer customer = customerRepo.getById(customerId);
            ShoppingCart shoppingCart = shoppingCartRepo.getById(shoppingCartId);
            Promo promo = promoRepo.getByCode(promoCode);

            Order.OrderBuilder builder = new Order.OrderBuilder(orderId, restaurantId, customerId, deliveryId, orderType, shoppingCart, total, paymentMethod, paymentStatus, placedAt.toLocalDateTime(), status);
            builder.deliveryId(deliveryId)
                    .promoCode(promo)
                    .discount(discount)
                    .total(total)
                    .paymentMethod(paymentMethod)
                    .paymentStatus(paymentStatus)
                    .placedAt(placedAt.toLocalDateTime())
                    .status(status)
                    .isCanceled(isCanceled)
                    .cancellationReason(cancellationReason);

            return builder.build();
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while mapping an order.", e);
        }
    }
}
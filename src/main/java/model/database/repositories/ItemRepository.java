package model.database.repositories;

import model.Item;

import java.util.List;

import model.database.DatabaseManager;
import model.enums.ItemCategory;
import model.exceptions.DatabaseOperationException;

import java.sql.*;
import java.util.ArrayList;

public class ItemRepository implements IRepository<Item> {
    private static final DatabaseManager databaseManager = DatabaseManager.getInstance();

    // This method returns a list of items based on the restaurantId
    public List<Item> getByRestaurantId(int restaurantId) throws DatabaseOperationException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM Items WHERE restaurantId = ?"; // Adjust the query as per your DB schema

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Map each row in the ResultSet to an Item object
                Item item = mapResultSetToItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while fetching menu items by restaurantId: "+restaurantId+", in method 'ItemRepository.getByRestaurantId()'", e);
        }
        return items;
    }

    private Item mapResultSetToItem(ResultSet resultSet) throws SQLException {
        Integer itemId = resultSet.getInt("itemId");
        Integer restaurantId = resultSet.getInt("restaurantId");
        String category = resultSet.getString("category");
        String title = resultSet.getString("title");
        double price = resultSet.getDouble("price");
        String description = resultSet.getString("description");
        String image = resultSet.getString("image");
        double discount = resultSet.getDouble("discount");
        double finalPrice = resultSet.getDouble("finalPrice");
        boolean isAvailable = resultSet.getBoolean("isAvailable");


        // Create and return the Item object using the builder pattern
        return new Item.ItemBuilder(itemId, restaurantId, category, title, price)
                .description(description)
                .image(image)
                .discount(discount)
                .finalPrice(finalPrice)
                .isAvailable(isAvailable)
                .build();
    }

    @Override
    public Item getById(int id) {
        return null; // You can implement this method similarly if needed
    }

    @Override
    public List<Item> getAll() {
        return null; // Implement this if needed
    }

    @Override
    public void save(Item entity) {
        // Implement save logic
    }

    @Override
    public void update(Item entity) {
        // Implement update logic
    }

    @Override
    public void delete(int id) {
        // Implement delete logic
    }
}

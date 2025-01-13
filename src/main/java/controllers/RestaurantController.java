package controllers;

import model.Item;
import model.database.repositories.ItemRepository;
import model.database.repositories.RestaurantRepository;
import model.exceptions.DatabaseOperationException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestaurantController {
    private final RestaurantRepository restaurantRepo = new RestaurantRepository();
    private final ItemRepository itemRepo = new ItemRepository();


    public List<Item> getMenu(int restaurantId) {
        List<Item> menu = null;
        try {
            menu = itemRepo.getByRestaurantId(restaurantId);
        }
        catch (DatabaseOperationException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed to fetch menu items for restaurantId: "+restaurantId, e);
        }

        return menu;
    }
    public void displayMenu(int restaurantId) {
        List<Item> menu = getMenu(restaurantId);
        for (Item item: menu) {
            System.out.println("\nID: "+item.getItemId());
            System.out.println("\tCategory: "+item.getCategory());
            System.out.println("\tTitle: "+item.getTitle());
            System.out.println("\tDescription: "+item.getDescription());
            if (item.getDiscount() != 0) {
                System.out.println("\tDiscount: "+"%"+item.getDiscount());
            }
            System.out.println("\tPrice: "+item.getFinalPrice());
        }
    }
}

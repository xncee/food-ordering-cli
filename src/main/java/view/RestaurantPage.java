package view;

import controllers.RestaurantController;
import model.users.Restaurant;

public class RestaurantPage {
    private Restaurant restaurant;
    private final RestaurantController restaurantController = new RestaurantController();

    public RestaurantPage(int restaurantId) {
        restaurant = restaurantController.getRestaurant(restaurantId);
        displayDetails();
        displayMenu();
    }
    public void displayDetails() {

    }
    public void displayMenu() {
        restaurantController.displayMenu(restaurant.getId());
    }
}

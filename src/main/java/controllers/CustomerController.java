package controllers;

import model.database.repositories.RestaurantRepository;
import model.users.Customer;
import model.users.Restaurant;

import java.security.interfaces.RSAKey;
import java.util.List;

public class CustomerController {
    private final Customer customer;
    private final RestaurantRepository restaurantRepo = new RestaurantRepository();
    public CustomerController(Customer customer) {
        this.customer = customer;
    }


    public void displayRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.getAll();
        for (Restaurant restaurant: restaurants) {
            System.out.println("\nID: "+restaurant.getId());
            System.out.println("\tName: "+restaurant.getRestaurantName());
            System.out.println("\tDescription: "+restaurant.getDescription());
            System.out.println("\tLocation: "+restaurant.getLocation());
            System.out.println("\tRating: "+restaurant.getRating()+"/5.0");
        }
    }
}

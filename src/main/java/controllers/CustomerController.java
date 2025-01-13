package controllers;

import model.database.repositories.RestaurantRepository;
import model.database.repositories.UserRepository;
import model.exceptions.DatabaseOperationException;
import model.users.Customer;
import model.users.Restaurant;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerController {
    private final Customer customer;
    private final UserRepository userRepository = new UserRepository();
    private final RestaurantRepository restaurantRepo = new RestaurantRepository();
    public CustomerController(Customer customer) {
        this.customer = customer;
    }


    public boolean addBalance(double amount) {
        if (amount < 0) {
            System.out.println("<!> Negative Amount Isn't Allowed.");
            return false;
        }

        customer.setBalance(customer.getBalance()+amount);
        try {
            userRepository.update(customer);
            return true;
        }
        catch (DatabaseOperationException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed To Add Balance.", e);
        }
        return false;
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

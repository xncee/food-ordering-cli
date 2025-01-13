package view;

import controllers.CustomerController;
import controllers.RestaurantController;
import model.users.Customer;
import model.users.Restaurant;

import java.util.Scanner;
import java.util.Set;

import static view.PageService.getChoice;
import static view.PageService.waitFor;

public class CustomerPanel {
    private Scanner sc = new Scanner(System.in);
    private final CustomerController customerController;
    private final RestaurantController restaurantController;
    private Customer customer;

    public CustomerPanel(Customer customer) {
        this.customer = customer;
        customerController = new CustomerController(customer);
        restaurantController = new RestaurantController();
    }
    public void show() {
        waitFor(1);
        System.out.println("\n# Customer Panel Page");
        System.out.println("1. Check Balance");
        System.out.println("2. Add Balance");
        System.out.println("3. Display Restaurants");
        System.out.println("4. Place Order");
        System.out.println("5. Orders History");
        System.out.println("0. EXIT");
        int choice = getChoice(Set.of(1, 2, 3, 4, 5, 0));

        switch (choice) {
            case 1: {
                System.out.println("Balance: "+customer.getBalance());
                break;
            }
            case 2: {
                System.out.println("Enter Amount: ");
                double amount = sc.nextDouble(); sc.nextLine();
                if (customerController.addBalance(amount)) {
                    System.out.println("<#> Amount added successfully!");
                }
                break;
            }
            case 3: {
                customerController.displayRestaurants();
                break;
            }
            case 4: {
                System.out.println("Enter restaurantId: ");
                int restaurantId = sc.nextInt();
                restaurantController.displayMenu(restaurantId);
                break;
            }
            case 5: {
                break;
            }
            case 0: {
                System.exit(0);
            }
        }
        show();
    }
}

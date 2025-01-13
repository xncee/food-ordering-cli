package view;

import controllers.CustomerController;
import model.users.Customer;

import java.util.Set;

import static view.PageService.getChoice;
import static view.PageService.waitFor;

public class CustomerPanel {
    private final CustomerController customerController;
    private Customer customer;

    public CustomerPanel(Customer customer) {
        this.customer = customer;
        customerController = new CustomerController(customer);
    }
    public void show() {
        waitFor(1);
        System.out.println("\n# Customer Panel Page");
        System.out.println("1. Check Balance");
        System.out.println("2. Display Restaurants");
        System.out.println("3. Place Order");
        System.out.println("4. Orders History");
        System.out.println("0. EXIT");
        int choice = getChoice(Set.of(1, 2, 3, 0));

        switch (choice) {
            case 1: {
                System.out.println("Balance: "+customer.getBalance());
                break;
            }
            case 2: {
                customerController.displayRestaurants();
                break;
            }
            case 3: {

            }
            case 4: {

            }
            case 0: {
                System.exit(0);
            }
        }
        show();
    }
}

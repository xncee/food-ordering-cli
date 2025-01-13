package view;

import controllers.SignInController;
import controllers.SignUpController;
import dto.UserDTO;
import model.users.Customer;

import java.util.Scanner;
import java.util.Set;

import static view.PageService.getChoice;
import static view.PageService.waitFor;

public class CustomerLoginPage {
    private final Scanner sc = new Scanner(System.in);
    private final SignInController signInController = new SignInController();
    private final SignUpController signUpController = new SignUpController();
    private boolean isLoggedIn;
    public Customer currentCustomer;

    public void show() {
        waitFor(1);
        if (isLoggedIn) return;

        System.out.println("\n# Customer Login Page");
        System.out.println("1. SignIn");
        System.out.println("2. SignUp");
        int choice = getChoice(Set.of(1, 2));

        switch (choice) {
            case 1: {
                System.out.println("Enter Username or Email: ");
                String username = sc.nextLine();
                System.out.println("Enter Password: ");
                String password = sc.nextLine();

                UserDTO userDTO = UserDTO.builder()
                        .username(username)
                        .password(password)
                        .build();

                boolean success = signInController.signIn(userDTO);
                if (success) {
                    System.out.println("<#> Sign-In Successful! Welcome, " + username + "!");
                    isLoggedIn = true;
                    currentCustomer = userDTO.getCustomer();
                } else {
                    System.out.println("<!> Sign-In Failed! Please check your username or password.");
                }
                break;
            }
            case 2: {
                System.out.println("Enter Username: ");
                String username = sc.nextLine();
                System.out.println("Enter Email: ");
                String email = sc.nextLine();
                System.out.println("Enter Phone Number: ");
                String phoneNumber = sc.nextLine();
                System.out.println("Enter Full Name: ");
                String fullName = sc.nextLine();
                System.out.println("Enter Password: ");
                String password = sc.nextLine();

                UserDTO userDTO = UserDTO.builder()
                        .username(username)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .fullName(fullName)
                        .password(password)
                        .build();

                boolean success = signUpController.signUp(userDTO);
                if (success) {
                    System.out.println("<#> Sign-Up Successful! You can now log in.");
                } else {
                    System.out.println("<!> Sign-Up Failed! Please try again.");
                }
                break;
            }
        }
        show();
    }
}
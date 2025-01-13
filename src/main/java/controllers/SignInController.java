package controllers;

import dto.UserDTO;
import model.database.repositories.CustomerRepository;
import model.database.repositories.UserRepository;
import model.exceptions.DatabaseOperationException;
import model.users.Customer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SignInController {
    private static final UserRepository userRepo = new UserRepository();
    private static final CustomerRepository customerRepo = new CustomerRepository();
    public boolean signIn(UserDTO userDTO) {
        String username_or_email = userDTO.getUsername();
        String password = userDTO.getPassword();
        try {
            boolean success = userRepo.authUser(username_or_email, password);
            if (success) {
                Customer customer = customerRepo.getByUsername(userDTO.getUsername());
                userDTO.setCustomer(customer);
            }
            return success;
        }
        catch (DatabaseOperationException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SignIn Failed", e);
        }
        return false;
    }
}

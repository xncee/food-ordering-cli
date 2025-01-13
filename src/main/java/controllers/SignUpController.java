package controllers;

import dto.UserDTO;
import model.database.repositories.CustomerRepository;
import model.database.repositories.UserRepository;
import model.enums.Messages;
import model.exceptions.DatabaseOperationException;
import model.users.Customer;
import model.users.CustomerFactory;
import model.users.UserFactory;
import model.utils.FieldsValidator;
import model.utils.Hashing;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpController {
    private static final UserRepository userRepo = new UserRepository();
    private static final CustomerRepository customerRepo = new CustomerRepository();

    public boolean validateForm(UserDTO userDTO) {
        boolean valid = true;
        // Username Validation
        if (userDTO.getUsername().isBlank()) {
            System.out.println("<!> Username: " + Messages.THIS_FIELD_IS_REQUIRED.getMessage());
            valid = false;
        }
        else if (!userRepo.isUsernameAvailable(userDTO.getUsername())) {
            System.out.println("<!> Username: " + Messages.USERNAME_TAKEN.getMessage());
            valid = false;
        }

        // Email Validation
        if (userDTO.getEmail().isBlank()) {
            System.out.println("<!> Email: " + Messages.THIS_FIELD_IS_REQUIRED.getMessage());
            valid = false;
        }
        else if (!FieldsValidator.validateEmail(userDTO.getEmail())) {
            System.out.println("<!> Email: " + Messages.EMAIL_REQUIREMENTS_NOT_MET.getMessage());
            valid = false;
        }
        else  if (!userRepo.isUsernameAvailable(userDTO.getEmail())) {
            System.out.println("<!> Email: " + Messages.EMAIL_ALREADY_REGISTERED.getMessage());
            valid = false;
        }

        // Phone Number Validation



        return valid;
    }
    public boolean signUp(UserDTO userDTO) {
        if (!validateForm(userDTO)) {
            return false;
        }

        String username = userDTO.getUsername();
        String email = userDTO.getEmail();
        String phoneNumber = userDTO.getPhoneNumber();
        String fullName = userDTO.getFullName();
        String password = userDTO.getPassword();
        UserFactory factory = new CustomerFactory();
        String hashedPassword = null;
        try {
            hashedPassword = Hashing.hash(password);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed to hash password.", e);
            return false;
        }
        Customer.CustomerBuilder builder = (Customer.CustomerBuilder) factory.create(null, username, fullName, email, phoneNumber, hashedPassword);
        Customer customer = builder.build();

        try {
            customerRepo.save(customer);
            return true;
        }
        catch (DatabaseOperationException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SignUp Failed", e);
        }
        return false;
    }
}

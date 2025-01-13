package controllers;

import dto.UserDTO;
import model.database.repositories.UserRepository;
import model.exceptions.DatabaseOperationException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SignInController {
    private static final UserRepository userRepo = new UserRepository();
    public boolean signIn(UserDTO userDTO) {
        String username_or_email = userDTO.getUsername();
        String password = userDTO.getEmail();
        try {
            return userRepo.authUser(username_or_email, password);
        }
        catch (DatabaseOperationException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SignIn Failed", e);
        }
        return false;
    }
}

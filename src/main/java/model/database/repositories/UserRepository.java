package model.database.repositories;

import model.database.DatabaseManager;
import model.exceptions.DatabaseOperationException;
import model.users.User;
import model.utils.Hashing;

import java.sql.*;
public class UserRepository {
    protected final DatabaseManager databaseManager = DatabaseManager.getInstance();

    public boolean authUser(String username_or_email, String password) throws DatabaseOperationException {
        String sql = "SELECT hashedPassword FROM Users WHERE username = ? OR email = ?";

        try (Connection conn = databaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username_or_email);
            stmt.setString(2, username_or_email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("hashedPassword");

                try {
                    return Hashing.match(storedHashedPassword, password);
                }
                catch (Exception e) {
                    throw new DatabaseOperationException("An Error occurred while matching password in 'authUser' method.", e);
                }

            }

            return false; // No user is found
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while retrieving 'hashedPassword' in 'authUser' method.", e);
        }
    }

    public boolean isUsernameAvailable(String username) {
        String sql = "SELECT username FROM USERS WHERE username = ?";
        try (Connection conn = databaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while checking username availability in 'UserRepository.isUsernameAvailable()' method.", e);
        }
    }
}

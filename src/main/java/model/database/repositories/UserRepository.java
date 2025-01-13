package model.database.repositories;

import model.database.DatabaseManager;
import model.exceptions.DatabaseOperationException;
import model.users.Restaurant;
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

    public void update(User user) throws DatabaseOperationException {
        String query = "UPDATE Users SET " +
                "userType = ?, " +
                "username = ?, " +
                "fullName = ?, " +
                "email = ?, " +
                "phoneNumber = ?, " +
                "hashedPassword = ?, " +
                "balance = ? " +
                "WHERE id = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getUserType());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getHashedPassword());
            preparedStatement.setDouble(7, user.getBalance());
            preparedStatement.setInt(8, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while updating a user in method 'UserRepository.update()'.", e);
        }
    }

    public boolean isUsernameAvailable(String username) {
        String sql = "SELECT username FROM USERS WHERE username = ?";
        try (Connection conn = databaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while checking username availability in 'UserRepository.isUsernameAvailable()' method.", e);
        }
    }

    public boolean isEmailAvailable(String email) {
        String sql = "SELECT email FROM USERS WHERE email = ?";
        try (Connection conn = databaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred while checking email availability in 'UserRepository.isEmailAvailable()' method.", e);
        }
    }
}

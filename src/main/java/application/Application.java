package application;

import io.github.cdimascio.dotenv.Dotenv;
import model.database.DatabaseManager;
import view.CustomerLoginPage;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Application {
    public Application() {
        setupDatabase();
        start();

    }
    private void start() {
        CustomerLoginPage customerLoginPage = new CustomerLoginPage();
        customerLoginPage.show();
    }
    private void setupDatabase() {
        try {
            DatabaseManager dbFacade = DatabaseManager.getInstance(Dotenv.load().get("DATABASE_URL"));
            if (!dbFacade.isConnected()) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, "Failed to connect to database");
                System.exit(1);
            }
        }
        catch (Exception e) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, "Failed to connect to database", e);
            System.exit(1);
        }
    }
}

package application;

import io.github.cdimascio.dotenv.Dotenv;
import model.database.DatabaseManager;
import model.database.repositories.CustomerRepository;
import model.users.Customer;
import view.CustomerLoginPage;
import view.CustomerPanel;
import view.MainPage;

import javax.crypto.Cipher;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Application {
    public Application() {
        setupDatabase();
        start();

    }
    private void start() {
        MainPage mainPage = new MainPage();
    }
    private void setupDatabase() {
        try {
            //System.out.println(Dotenv.load().get("DATABASE_URL"));
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

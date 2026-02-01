package main.java.com.example;

import java.util.logging.Logger;
import java.util.logging.Level;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // FIX: Using a Lambda (Supplier) ensures calculate() only runs if INFO is enabled
        LOGGER.log(Level.INFO, () -> "Result: " + calc.calculate(10, 5, "add-again"));

        UserService service = new UserService();
        
        try {
            service.findUser("admin");
            service.deleteUser("admin");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during user operations", e);
        }
    }
}
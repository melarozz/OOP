package ru.nsu.yakovleva;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.application.menu.Menu;

/**
 * The main class representing the JavaFX application entry point.
 */
public class Application extends javafx.application.Application {
    // Default configuration for the game
    private final Configuration defaultConfiguration = new Configuration(40, 20, 20, 30, 5, 100);
    // Icon image for the application window
    private final Image icon = new Image(String.valueOf(getClass()
            .getResource("/ru/nsu/yakovleva/images/fruit/apple.png")));

    /**
     * The main entry point of the JavaFX application.
     *
     * @param primaryStage The primary stage (window) of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Center the stage on the screen
        primaryStage.centerOnScreen();
        // Prevent the stage from being resizable
        primaryStage.setResizable(false);
        // Set the application icon
        primaryStage.getIcons().add(icon);
        // Create an instance of the Menu class with default configuration
        Menu menu = new Menu(defaultConfiguration);
        // Set the stage for the menu
        menu.setStage(primaryStage);
        // Display the stage
        primaryStage.show();
    }

    /**
     * The main method, which is the entry point of the Java application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}

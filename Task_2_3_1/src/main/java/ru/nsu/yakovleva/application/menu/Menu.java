package ru.nsu.yakovleva.application.menu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Represents the menu of the game.
 */
public class Menu {
    private final Configuration configuration;
    private Scene scene;
    private MenuController controller;

    /**
     * Constructs a menu with the given configuration.
     *
     * @param configuration The configuration settings for the game.
     */
    public Menu(Configuration configuration) {
        this.configuration = configuration;
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/nsu/yakovleva/fxml/menu.fxml"));
        try {
            Parent root = loader.load();
            scene = new Scene(root);
            controller = loader.getController();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Sets the stage for the menu.
     *
     * @param stage The stage to set for the menu.
     */
    public void setStage(Stage stage) {
        controller.initialize(stage, configuration);
        stage.setScene(scene);
    }
}

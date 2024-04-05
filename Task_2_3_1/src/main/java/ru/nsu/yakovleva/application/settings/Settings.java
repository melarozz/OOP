package ru.nsu.yakovleva.application.settings;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.menu.Configuration;


/**
 * Represents the settings menu of the game.
 */
public class Settings {
    private final Configuration configuration;
    private SettingsController controller;
    private Scene scene;

    /**
     * Constructs a settings menu with the given configuration.
     *
     * @param configuration The configuration settings for the game.
     */
    public Settings(Configuration configuration) {
        this.configuration = configuration;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/yakovleva/fxml/settings.fxml"));
        try {
            Parent root = loader.load();
            controller = loader.getController();
            scene = new Scene(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Sets the stage for the settings menu.
     *
     * @param stage The stage to set for the settings menu.
     */
    public void setStage(Stage stage) {
        controller.initialize(stage, configuration);
        stage.setScene(scene);
    }
}

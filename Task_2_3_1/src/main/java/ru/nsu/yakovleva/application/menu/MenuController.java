package ru.nsu.yakovleva.application.menu;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.snakegame.SnakeGame;
import ru.nsu.yakovleva.application.settings.Settings;

/**
 * Controller class for the menu UI.
 */
public class MenuController {
    private Stage stage;
    private Configuration configuration;

    /**
     * Initializes the menu controller with the given stage and configuration.
     *
     * @param stage         The stage of the application.
     * @param configuration The configuration settings for the game.
     */
    public void initialize(Stage stage, Configuration configuration) {
        this.stage = stage;
        this.configuration = configuration;
    }

    /**
     * Starts the snake game.
     */
    @FXML
    private void startGame() {
        SnakeGame snakeGame = new SnakeGame(configuration);
        snakeGame.setStage(stage);
    }

    /**
     * Opens the settings menu.
     */
    @FXML
    private void openSettings() {
        Settings settings = new Settings(configuration);
        settings.setStage(stage);
    }

    /**
     * Exits the application.
     */
    @FXML
    private void exit() {
        stage.close();
    }
}

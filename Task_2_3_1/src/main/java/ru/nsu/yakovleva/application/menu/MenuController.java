package ru.nsu.yakovleva.application.menu;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.snakegame.SnakeGame;
import ru.nsu.yakovleva.application.settings.Settings;

public class MenuController {
    private Stage stage;
    private Configuration configuration;

    public void initialize(Stage stage, Configuration configuration) {
        this.stage = stage;
        this.configuration = configuration;
    }

    @FXML
    private void startGame() {
        SnakeGame snakeGame = new SnakeGame(configuration);
        snakeGame.setStage(stage);
    }

    @FXML
    private void openSettings() {
        Settings settings = new Settings(configuration);
        settings.setStage(stage);
    }

    @FXML
    private void exit() {
        stage.close();
    }
}

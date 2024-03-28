package ru.nsu.yakovleva.application.modalwindow;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.snakegame.SnakeGame;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.application.menu.Menu;

public class ModalWindowController {
    private Stage stage;
    private Configuration configuration;
    private Timeline timeline;

    @FXML
    StackPane modalWindow;

    public void initialize(Stage mainStage, Configuration configuration, Timeline timeline) {
        this.stage = mainStage;
        this.configuration = configuration;
        this.timeline = timeline;
    }

    private void closeModalWindow() {
        Stage stage = (Stage) modalWindow.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void restartGame() {
        SnakeGame snakeGame = new SnakeGame(configuration);
        snakeGame.setStage(stage);
        closeModalWindow();
    }

    @FXML
    private void continueGame() {
        timeline.play();
        closeModalWindow();
    }

    @FXML
    private void openMenu() {
        Menu menu = new Menu(configuration);
        menu.setStage(stage);
        timeline.stop();
        closeModalWindow();
    }
}

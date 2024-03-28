package ru.nsu.yakovleva.application.snakegame;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.application.modalwindow.ModalWindow;
import ru.nsu.yakovleva.logic.game.Game;

public class SnakeGameController {
    private ModalWindow modalWindow;
    private Timeline timeline;
    private Game game;

    @FXML
    private Label score;

    public void initialize(Stage mainStage, Configuration configuration, Timeline timeline, Game game) {
        this.modalWindow = new ModalWindow(mainStage, configuration, timeline);
        this.game = game;
        this.timeline = timeline;
    }

    public void updateScore() {
        score.setText("Score: " + game.getScore());
    }

    @FXML
    public void openModalWindow() {
        timeline.pause();
        modalWindow.open();
    }
}

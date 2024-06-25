package ru.nsu.yakovleva.application.snakegame;

import static ru.nsu.yakovleva.logic.game.GameState.DEFEAT;
import static ru.nsu.yakovleva.logic.game.GameState.VICTORY;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.application.modalwindow.ModalWindow;
import ru.nsu.yakovleva.logic.game.Game;

/**
 * Controller class for the Snake game UI.
 */
public class SnakeGameController {
    private ModalWindow modalWindow;
    private Timeline timeline;
    private Game game;

    @FXML
    private Label score;

    /**
     * Initializes the Snake game controller with the main stage, configuration, timeline, and game.
     *
     * @param mainStage     The main stage of the application.
     * @param configuration The configuration settings for the game.
     * @param timeline      The timeline for the animation.
     * @param game          The game instance.
     */
    public void initialize(Stage mainStage,
                           Configuration configuration, Timeline timeline, Game game) {
        this.modalWindow = new ModalWindow(mainStage, configuration, timeline);
        this.game = game;
        this.timeline = timeline;
    }

    /**
     * Updates the score label in the UI.
     */
    public void updateScore() {
        score.setText("Score: " + game.getScore());
    }

    /**
     * Method for opening the modal window with the state.
     */
    @FXML
    public void openModalWindow() {
        timeline.pause();
        if (game.getGameState() != DEFEAT && game.getGameState() != VICTORY) {
            modalWindow.open("PAUSE");
            return;
        }
        modalWindow.open(game.getGameState().toString());
    }
}

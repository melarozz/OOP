package ru.nsu.yakovleva.application.modalwindow;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.snakegame.SnakeGame;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.application.menu.Menu;

/**
 * Controller class for the modal window UI.
 */
public class ModalWindowController {
    private Stage stage;
    private Configuration configuration;
    private Timeline timeline;

    @FXML
    StackPane modalWindow;
    @FXML
    private Label header;

    /**
     * Initializes the modal window controller with the main stage, configuration, and timeline.
     *
     * @param mainStage     The main stage of the application.
     * @param configuration The configuration settings for the game.
     * @param timeline      The timeline for the animation.
     */
    public void initialize(Stage mainStage, Configuration configuration, Timeline timeline) {
        this.stage = mainStage;
        this.configuration = configuration;
        this.timeline = timeline;
    }

    private void closeModalWindow() {
        Stage stage = (Stage) modalWindow.getScene().getWindow();
        stage.close();
    }

    /**
     * Restarts the game.
     */
    @FXML
    private void restartGame() {
        SnakeGame snakeGame = new SnakeGame(configuration);
        snakeGame.setStage(stage);
        closeModalWindow();
    }

    /**
     * Continues the game.
     */
    @FXML
    private void continueGame() {
        timeline.play();
        closeModalWindow();
    }

    /**
     * Opens the main menu.
     */
    @FXML
    private void openMenu() {
        Menu menu = new Menu(configuration);
        menu.setStage(stage);
        timeline.stop();
        closeModalWindow();
    }

    /**
     * Sets the header text of the modal window.
     *
     * @param text The text to set as the header.
     */
    public void setHeader(String text) {
        header.setText(text);
    }

}

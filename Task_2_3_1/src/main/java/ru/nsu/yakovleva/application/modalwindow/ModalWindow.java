package ru.nsu.yakovleva.application.modalwindow;

import java.io.IOException;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.menu.Configuration;

/**
 * Represents a modal window in the application.
 */
public class ModalWindow {
    private Stage modalWindowStage;
    private ModalWindowController controller;

    /**
     * Constructs a modal window with the given stage, configuration, and timeline.
     *
     * @param stage         The stage of the application.
     * @param configuration The configuration settings for the game.
     * @param timeline      The timeline for the animation.
     */
    public ModalWindow(Stage stage, Configuration configuration, Timeline timeline) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/nsu/yakovleva/fxml/modalWindow.fxml"));
        try {
            Parent root = loader.load();
            controller = loader.getController();
            controller.initialize(stage, configuration, timeline);
            modalWindowStage = new Stage();
            modalWindowStage.setScene(new Scene(root));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Opens the modal window with the given header.
     *
     * @param header The header text of the modal window.
     */
    public void open(String header) {
        controller.setHeader(header);
        modalWindowStage.show();
    }
}

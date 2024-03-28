package ru.nsu.yakovleva.application.modalwindow;

import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.menu.Configuration;

import java.io.IOException;

public class ModalWindow {
    private Stage modalWindowStage;

    public ModalWindow(Stage stage, Configuration configuration, Timeline timeline) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/yakovleva/fxml/modalWindow.fxml"));
        try {
            Parent root = loader.load();
            ModalWindowController controller = loader.getController();
            controller.initialize(stage, configuration, timeline);
            modalWindowStage = new Stage();
            modalWindowStage.setScene(new Scene(root));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void open() {
        modalWindowStage.show();
    }
}


package ru.nsu.yakovleva.application.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    private final Configuration configuration;
    private Scene scene;
    private MenuController controller;

    public Menu(Configuration configuration) {
        this.configuration = configuration;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/yakovleva/fxml/menu.fxml"));
        try {
            Parent root = loader.load();
            scene = new Scene(root);
            controller = loader.getController();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        controller.initialize(stage, configuration);
        stage.setScene(scene);
    }
}

package ru.nsu.yakovleva;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.application.menu.Menu;

public class Application extends javafx.application.Application {
    private final Configuration DEFAULT_CONFIGURATION = new Configuration(40, 20, 20, 10, 10,100);
    private final Image ICON = new Image(String.valueOf(getClass().getResource("/ru/nsu/yakovleva/images/fruit/apple.png")));

    @Override
    public void start(Stage primaryStage) {
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        Menu menu = new Menu(DEFAULT_CONFIGURATION);
        menu.setStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package ru.nsu.yakovleva.application.settings;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.application.menu.Menu;

import java.util.Objects;

public class SettingsController {
    private Stage stage;
    private Configuration configuration;

    @FXML
    private TextField rowsNumber;
    @FXML
    private TextField columnsNumber;

    public void initialize(Stage mainStage, Configuration configuration) {
        this.stage = mainStage;
        this.configuration = configuration;
        rowsNumber.setText(String.valueOf(configuration.rowsNumber()));
        columnsNumber.setText(String.valueOf(configuration.columnsNumber()));
    }

    @FXML
    private void saveConfiguration() {
        int rowsNumber = Objects.equals(this.rowsNumber.getText(), "") ? configuration.rowsNumber() : Integer.parseInt(this.rowsNumber.getText());
        int columnsNumber = Objects.equals(this.rowsNumber.getText(), "") ? configuration.columnsNumber() : Integer.parseInt(this.columnsNumber.getText());
        this.configuration = new Configuration(40, rowsNumber, columnsNumber, 10, 10, 100);
        openMenu();
    }

    @FXML
    private void openMenu() {
        ru.nsu.yakovleva.application.menu.Menu menu = new Menu(configuration);
        menu.setStage(stage);
    }
}

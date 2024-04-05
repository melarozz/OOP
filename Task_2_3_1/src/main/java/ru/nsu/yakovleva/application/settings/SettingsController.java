package ru.nsu.yakovleva.application.settings;

import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.application.menu.Menu;


/**
 * Controller class for the settings menu UI.
 */
public class SettingsController {
    private Stage stage;
    private Configuration configuration;

    @FXML
    private TextField rowsNumber;
    @FXML
    private TextField columnsNumber;
    @FXML
    private TextField maximumScore;
    @FXML
    private Slider fruitsNumber;
    @FXML
    private MenuButton snakeSpeed;

    /**
     * Initializes the settings controller with the main stage and configuration.
     *
     * @param mainStage     The main stage of the application.
     * @param configuration The configuration settings for the game.
     */
    public void initialize(Stage mainStage, Configuration configuration) {
        this.stage = mainStage;
        this.configuration = configuration;
        rowsNumber.setText(String.valueOf(configuration.rowsNumber()));
        columnsNumber.setText(String.valueOf(configuration.columnsNumber()));
        maximumScore.setText(String.valueOf(configuration.maximumScore()));
        fruitsNumber.setValue(configuration.fruitsNumber());
    }

    @FXML
    private void saveConfiguration() {
        int rowsNumber = Objects.equals(this.rowsNumber.getText(), "") ? configuration.rowsNumber() : Integer.parseInt(this.rowsNumber.getText());
        int columnsNumber = Objects.equals(this.rowsNumber.getText(), "") ? configuration.columnsNumber() : Integer.parseInt(this.columnsNumber.getText());
        int maximumScore = Objects.equals(this.rowsNumber.getText(), "") ? configuration.maximumScore() : Integer.parseInt(this.maximumScore.getText());
        int fruitsNumber = (int) this.fruitsNumber.getValue();
        int snakeSpeed = switch (this.snakeSpeed.getText()) {
            case "Slowly" -> 300;
            case "Normal" -> 120;
            case "Fast" -> 100;
            default -> 150;
        };
        this.configuration = new Configuration(40, rowsNumber, columnsNumber, maximumScore, fruitsNumber, snakeSpeed);
        openMenu();
    }

    @FXML
    private void changeSpeed(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        snakeSpeed.setText(menuItem.getText());
    }

    @FXML
    private void openMenu() {
        ru.nsu.yakovleva.application.menu.Menu menu = new Menu(configuration);
        menu.setStage(stage);
    }
}

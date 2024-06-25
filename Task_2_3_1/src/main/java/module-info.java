module ru.nsu.yakovleva {
    requires javafx.controls;
    requires javafx.fxml;

    exports ru.nsu.yakovleva;
    opens ru.nsu.yakovleva to javafx.fxml;
    exports ru.nsu.yakovleva.logic.sprite;
    opens ru.nsu.yakovleva.logic.sprite to javafx.fxml;
    exports ru.nsu.yakovleva.logic.sprite.board;
    opens ru.nsu.yakovleva.logic.sprite.board to javafx.fxml;
    exports ru.nsu.yakovleva.logic.sprite.snake;
    opens ru.nsu.yakovleva.logic.sprite.snake to javafx.fxml;
    exports ru.nsu.yakovleva.logic.sprite.fruit;
    opens ru.nsu.yakovleva.logic.sprite.fruit to javafx.fxml;
    exports ru.nsu.yakovleva.logic.game;
    opens ru.nsu.yakovleva.logic.game to javafx.fxml;
    exports ru.nsu.yakovleva.logic.cell;
    opens ru.nsu.yakovleva.logic.cell to javafx.fxml;
    exports ru.nsu.yakovleva.snakegamefx.sprite;
    opens ru.nsu.yakovleva.snakegamefx.sprite to javafx.fxml;
    exports ru.nsu.yakovleva.snakegamefx.game;
    opens ru.nsu.yakovleva.snakegamefx.game to javafx.fxml;
    exports ru.nsu.yakovleva.application.modalwindow;
    opens ru.nsu.yakovleva.application.modalwindow to javafx.fxml;
    exports ru.nsu.yakovleva.application.menu;
    opens ru.nsu.yakovleva.application.menu to javafx.fxml;
    exports ru.nsu.yakovleva.application.settings;
    opens ru.nsu.yakovleva.application.settings to javafx.fxml;
    exports ru.nsu.yakovleva.application.snakegame;
    opens ru.nsu.yakovleva.application.snakegame to javafx.fxml;
}
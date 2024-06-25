package ru.nsu.yakovleva.application.snakegame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;
import ru.nsu.yakovleva.snakegamefx.game.GameController;
import ru.nsu.yakovleva.snakegamefx.game.GameFx;
import ru.nsu.yakovleva.snakegamefx.sprite.BoardFx;
import ru.nsu.yakovleva.snakegamefx.sprite.FruitFx;
import ru.nsu.yakovleva.snakegamefx.sprite.Skin;
import ru.nsu.yakovleva.snakegamefx.sprite.SnakeFx;

/**
 * Represents the main game logic and UI for the Snake game.
 */
public class SnakeGame {
    private Stage stage;
    private final Configuration configuration;
    private Map<String, Image> images;
    private Board board;
    private Snake snake;
    private List<Fruit> food;
    private GameFx snakeGame;
    private Group frame;
    private GameController gameController;
    private Scene scene;
    private Timeline timeline;

    /**
     * Constructs a SnakeGame instance with the given configuration.
     *
     * @param configuration The configuration settings for the game.
     */
    public SnakeGame(Configuration configuration) {
        this.configuration = configuration;
        setImages();
        setSnakeGame();
    }

    private void setImages() {
        this.images = new HashMap<>();
        images.put("board", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/yakovleva/images/board/board.png"))));
        images.put("head", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/yakovleva/images/snake/green_head.PNG"))));
        images.put("rotated", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/yakovleva/images/snake/green.PNG"))));
        images.put("straight", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/yakovleva/images/snake/green.PNG"))));
        images.put("tail", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/yakovleva/images/snake/green.PNG"))));
        images.put("apple", new Image(String.valueOf(getClass()
                .getResource("/ru/nsu/yakovleva/images/fruit/apple.png"))));
    }

    private void setBoard() {
        double width = configuration.cellSize()
                * configuration.rowsNumber();
        double height = configuration.cellSize()
                * configuration.columnsNumber();
        Skin skin = new Skin(width, height, images.get("board"));
        BoardFx board = new BoardFx(width, height);
        board.setSkin(skin);
        this.board = board;
    }

    private void setSnake() {
        Skin head = new Skin(configuration.cellSize(),
                configuration.cellSize(), images.get("head"));
        Skin rotated = new Skin(configuration.cellSize(),
                configuration.cellSize(), images.get("rotated"));
        Skin straight = new Skin(configuration.cellSize(),
                configuration.cellSize(), images.get("straight"));
        Skin tail = new Skin(configuration.cellSize(),
                configuration.cellSize(), images.get("tail"));
        SnakeFx snake = new SnakeFx(configuration.cellSize(),
                configuration.cellSize());
        snake.setSkins(head, rotated, straight, tail);
        this.snake = snake;
    }

    private void setFood() {
        Skin skin = new Skin(configuration.cellSize(),
                configuration.cellSize(), images.get("apple"));
        List<Fruit> food = new ArrayList<>();
        for (int i = 0; i < configuration.fruitsNumber(); ++i) {
            FruitFx fruit = new FruitFx(configuration.cellSize(),
                    configuration.cellSize());
            fruit.setSkin(skin);
            food.add(fruit);
        }
        this.food = food;
    }

    private void setSnakeGame() {
        setBoard();
        setSnake();
        setFood();
        snakeGame = new GameFx(configuration, board, snake, food);
        snakeGame.start();
    }

    private void setScene() {
        frame = new Group();
        snakeGame.render(frame);
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/nsu/yakovleva/fxml/snakeGame.fxml"));
        try {
            BorderPane root = loader.load();
            SnakeGameController snakeGameController = loader.getController();
            snakeGameController.initialize(stage, configuration,
                    timeline, snakeGame);
            root.setCenter(frame);
            scene = new Scene(root);
            gameController = new GameController(snakeGameController,
                    snakeGame, timeline);
            scene.setOnKeyPressed(gameController::handle);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void setTimeline() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(configuration.snakeSpeed()),
                event -> gameController.run(frame)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Sets the stage for the Snake game.
     *
     * @param stage The stage to set for the game.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        setTimeline();
        setScene();
        stage.setScene(scene);
    }
}

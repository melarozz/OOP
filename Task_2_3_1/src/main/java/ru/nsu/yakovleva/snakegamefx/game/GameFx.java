package ru.nsu.yakovleva.snakegamefx.game;

import static ru.nsu.yakovleva.logic.game.GameState.PLAY;

import java.util.List;
import javafx.scene.Group;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.logic.game.Game;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;


/**
 * Represents the game logic and rendering for a JavaFX environment.
 */
public class GameFx extends Game {
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;

    /**
     * Constructs a GameFX object with the specified configuration, board, snake, and food.
     *
     * @param configuration The game configuration.
     * @param board         The game board.
     * @param snake         The game snake.
     * @param food          The list of food items in the game.
     */
    public GameFx(Configuration configuration, Board board, Snake snake, List<Fruit> food) {
        super(configuration, board, snake, food);
        this.board = board;
        this.snake = snake;
        this.food = food;
    }

    /**
     * Renders the game components onto the specified frame.
     *
     * @param frame The frame to render the game components on.
     */
    public void render(Group frame) {
        if (getGameState() == PLAY) {
            frame.getChildren().clear();
            board.render(frame);
            food.forEach(fruit -> fruit.render(frame));
            snake.render(frame);
        }
    }
}

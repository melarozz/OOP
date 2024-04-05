package ru.nsu.yakovleva.logic.game;

import static ru.nsu.yakovleva.logic.game.GameState.PLAY;
import static ru.nsu.yakovleva.logic.game.GameState.DEFEAT;
import static ru.nsu.yakovleva.logic.game.GameState.VICTORY;

import java.util.List;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.logic.sprite.Sprite;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;
import ru.nsu.yakovleva.logic.sprite.snake.Direction;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;


/**
 * Represents the game logic of the Snake game.
 */
public class Game {
    private final Configuration configuration;
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;
    private GameState gameState;

    /**
     * Constructs a Game instance with the given configuration, board, snake, and food.
     *
     * @param configuration The configuration settings for the game.
     * @param board         The game board.
     * @param snake         The snake sprite.
     * @param food          The list of food sprites.
     */
    public Game(Configuration configuration, Board board, Snake snake, List<Fruit> food) {
        this.configuration = configuration;
        this.board = board;
        this.snake = snake;
        this.food = food;
        this.gameState = PLAY;
    }

    /**
     * Gets the current score of the game.
     *
     * @return The current score.
     */
    public int getScore() {
        return snake.getLength() - 3;
    }

    /**
     * Gets the current state of the game.
     *
     * @return The current state of the game.
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Sets the direction of the snake.
     *
     * @param direction The direction to set for the snake.
     */
    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    private void updateGameState() {
        if (!snake.intersects(board) || snake.intersects(snake)) {
            gameState = DEFEAT;
        } else if (getScore() == configuration.maximumScore()) {
            gameState = VICTORY;
        } else {
            gameState = PLAY;
        }
    }

    private void updateSprite(Sprite sprite) {
        do {
            sprite.update(configuration.rowsNumber(), configuration.columnsNumber());
        } while (snake.intersects(sprite) ||
                food.stream().anyMatch(other -> other != sprite && other.intersects(sprite)));
    }

    private void eatFood() {
        for (Fruit fruit : food) {
            if (snake.intersects(fruit)) {
                snake.grow();
                updateSprite(fruit);
            }
        }
    }

    /**
     * Starts the game by initializing the snake and food positions.
     */
    public void start() {
        snake.start(snake.getLength() * configuration.cellSize(), (configuration.columnsNumber() >> 1) * configuration.cellSize());
        food.forEach(this::updateSprite);
    }

    /**
     * Updates the game state and performs necessary actions like eating food.
     */
    public void update() {
        if (gameState == PLAY) {
            eatFood();
            snake.update(configuration.cellSize(), configuration.cellSize());
            updateGameState();
        }
    }
}

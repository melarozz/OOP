package ru.nsu.yakovleva.logic.game;

import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.logic.sprite.Sprite;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;
import ru.nsu.yakovleva.logic.sprite.snake.Direction;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;

import java.util.List;

import static ru.nsu.yakovleva.logic.game.GameState.*;

public class Game {
    private final Configuration configuration;
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;
    private GameState gameState;

    public Game(Configuration configuration, Board board, Snake snake, List<Fruit> food) {
        this.configuration = configuration;
        this.board = board;
        this.snake = snake;
        this.food = food;
        this.gameState = PLAY;
    }

    public int getScore() {
        return snake.getLength() - 3;
    }

    public GameState getGameState() {
        return gameState;
    }

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

    public void start() {
        snake.start(snake.getLength() * configuration.cellSize(), (configuration.columnsNumber() >> 1) * configuration.cellSize());
        food.forEach(this::updateSprite);
    }

    public void update() {
        if (gameState == PLAY) {
            eatFood();
            snake.update(configuration.cellSize(), configuration.cellSize());
            updateGameState();
        }
    }
}

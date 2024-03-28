package ru.nsu.yakovleva.snakegamefx.game;

import javafx.scene.Group;
import ru.nsu.yakovleva.application.menu.Configuration;
import ru.nsu.yakovleva.logic.game.Game;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;

import java.util.List;

import static ru.nsu.yakovleva.logic.game.GameState.PLAY;

public class GameFX extends Game {
    private final Board board;
    private final Snake snake;
    private final List<Fruit> food;

    public GameFX(Configuration configuration, Board board, Snake snake, List<Fruit> food) {
        super(configuration, board, snake, food);
        this.board = board;
        this.snake = snake;
        this.food = food;
    }

    public void render(Group frame) {
        if (getGameState() == PLAY) {
            frame.getChildren().clear();
            board.render(frame);
            food.forEach(fruit -> fruit.render(frame));
            snake.render(frame);
        }
    }
}

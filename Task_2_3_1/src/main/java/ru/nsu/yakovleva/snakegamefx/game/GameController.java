package ru.nsu.yakovleva.snakegamefx.game;

import static javafx.animation.Animation.Status.PAUSED;
import static javafx.animation.Animation.Status.RUNNING;
import static ru.nsu.yakovleva.logic.game.GameState.DEFEAT;
import static ru.nsu.yakovleva.logic.game.GameState.VICTORY;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.DOWN;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.LEFT;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.RIGHT;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.UP;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.yakovleva.application.snakegame.SnakeGameController;

/**
 * Controls the game flow and user input handling.
 */
public class GameController {
    private final SnakeGameController controller;
    private final GameFx snakeGame;
    private final Timeline timeline;

    /**
     * Constructs a GameController with the specified components.
     *
     * @param controller The SnakeGameController instance.
     * @param snakeGame  The GameFX instance.
     * @param timeline   The Timeline instance.
     */
    public GameController(SnakeGameController controller, GameFx snakeGame, Timeline timeline) {
        this.controller = controller;
        this.snakeGame = snakeGame;
        this.timeline = timeline;
    }

    /**
     * Handles key events for controlling the game.
     *
     * @param event The KeyEvent to handle.
     */
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        if (timeline.getStatus() == PAUSED) {
            timeline.play();
            return;
        }
        switch (code) {
            case RIGHT -> snakeGame.setSnakeDirection(RIGHT);
            case LEFT -> snakeGame.setSnakeDirection(LEFT);
            case UP -> snakeGame.setSnakeDirection(UP);
            case DOWN -> snakeGame.setSnakeDirection(DOWN);
            case ESCAPE -> controller.openModalWindow();
        }
    }

    /**
     * Runs the game loop and updates the game state.
     *
     * @param frame The Group representing the game frame.
     */
    public void run(Group frame) {
        if (timeline.getStatus() == RUNNING && (snakeGame.getGameState() == DEFEAT
                || snakeGame.getGameState() == VICTORY)) {
            timeline.stop();
            controller.openModalWindow();
        }
        snakeGame.update();
        controller.updateScore();
        snakeGame.render(frame);
    }
}

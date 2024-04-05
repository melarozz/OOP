package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.LEFT;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.RIGHT;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.UP;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;
import ru.nsu.yakovleva.snakegamefx.sprite.BoardFX;
import ru.nsu.yakovleva.snakegamefx.sprite.SnakeFX;


/**
 * Test class.
 */
public class SnakeTest {
    private final int SQUARE_SIZE = 40;
    private Snake snake;

    @BeforeEach
    public void initialize() {
        snake = new SnakeFX(SQUARE_SIZE, SQUARE_SIZE);
        snake.start(3 * SQUARE_SIZE, SQUARE_SIZE);
    }

    @Test
    public void getDirection() {
        assertEquals(snake.getDirection(), RIGHT);
    }

    @Test
    public void getLength() {
        assertEquals(snake.getLength(), 3);
    }

    @Test
    public void setDirection() {
        snake.setDirection(LEFT);
        assertEquals(snake.getDirection(), RIGHT);
        snake.setDirection(UP);
        assertEquals(snake.getDirection(), UP);
    }

    @Test
    public void grow() {
        snake.grow();
        assertEquals(snake.getLength(), 4);
    }

    @Test
    public void getBoundary() {
        List<Cell> boundary = snake.getBoundary();
        for (int i = boundary.size(); i > 0 ; --i) {
            Cell cell = new Cell(SQUARE_SIZE, SQUARE_SIZE);
            cell.setPosition(i * SQUARE_SIZE, SQUARE_SIZE);
            assertEquals(boundary.get(boundary.size() - i), cell);
        }
    }

    @Test
    public void intersects() {
        Board board = new BoardFX(SQUARE_SIZE, SQUARE_SIZE);
        board.update(1000, 1000);
        assertFalse(snake.intersects(board));
        assertFalse(snake.intersects(snake));
    }

    @Test
    public void update() {
        snake.update(SQUARE_SIZE, SQUARE_SIZE);
        List<Cell> boundary = snake.getBoundary();
        for (int i = boundary.size(); i > 0 ; --i) {
            Cell cell = new Cell(SQUARE_SIZE, SQUARE_SIZE);
            cell.setPosition((i + 1) * SQUARE_SIZE, SQUARE_SIZE);
            assertEquals(boundary.get(boundary.size() - i), cell);
        }
    }
}
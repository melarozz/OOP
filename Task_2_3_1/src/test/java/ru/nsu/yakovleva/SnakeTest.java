package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.LEFT;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.RIGHT;
import static ru.nsu.yakovleva.logic.sprite.snake.Direction.UP;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.logic.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;
import ru.nsu.yakovleva.snakegamefx.sprite.BoardFx;
import ru.nsu.yakovleva.snakegamefx.sprite.SnakeFx;


/**
 * Test class.
 */
public class SnakeTest {
    private final int squareSize = 40;
    private Snake snake;

    @BeforeEach
    public void initialize() {
        snake = new SnakeFx(squareSize, squareSize);
        snake.start(3 * squareSize, squareSize);
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
        for (int i = boundary.size(); i > 0; --i) {
            Cell cell = new Cell(squareSize, squareSize);
            cell.setPosition(i * squareSize, squareSize);
            assertEquals(boundary.get(boundary.size() - i), cell);
        }
    }

    @Test
    public void intersects() {
        Board board = new BoardFx(squareSize, squareSize);
        board.update(1000, 1000);
        assertFalse(snake.intersects(board));
        assertFalse(snake.intersects(snake));
    }

    @Test
    public void update() {
        snake.update(squareSize, squareSize);
        List<Cell> boundary = snake.getBoundary();
        for (int i = boundary.size(); i > 0; --i) {
            Cell cell = new Cell(squareSize, squareSize);
            cell.setPosition((i + 1) * squareSize, squareSize);
            assertEquals(boundary.get(boundary.size() - i), cell);
        }
    }

    @Test
    public void testSnakeGrowth() {
        int initialLength = snake.getLength();
        snake.grow();
        assertEquals(snake.getLength(), initialLength + 1);
    }

}
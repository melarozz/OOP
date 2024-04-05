package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.snakegamefx.sprite.BoardFX;
import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;
import ru.nsu.yakovleva.snakegamefx.sprite.FruitFX;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;
import ru.nsu.yakovleva.snakegamefx.sprite.SnakeFX;


/**
 * Test class.
 */
public class BoardTest {
    private final int ROWS_NUMBER = 10;
    private final int COLUMNS_NUMBER = ROWS_NUMBER;
    private final int SQUARE_SIZE = 20;
    private Board board;

    @BeforeEach
    public void initialize() {
        board = new BoardFX(ROWS_NUMBER * SQUARE_SIZE, COLUMNS_NUMBER * SQUARE_SIZE);
    }

    @Test
    void getBoundary() {
        Cell actual = board.getBoundary();
        Cell expected = new Cell(ROWS_NUMBER * SQUARE_SIZE, COLUMNS_NUMBER * SQUARE_SIZE);
        assertEquals(expected, actual);
    }

    @Test
    void intersects() {
        Fruit fruit = new FruitFX(SQUARE_SIZE, SQUARE_SIZE);
        fruit.update(ROWS_NUMBER, COLUMNS_NUMBER);
        board.intersects(fruit);
        Snake snake = new SnakeFX(SQUARE_SIZE, SQUARE_SIZE);
        snake.start(snake.getLength() * SQUARE_SIZE, (COLUMNS_NUMBER << 1) * SQUARE_SIZE);
        board.intersects(snake);
    }

    @Test
    void update() {
        board.update(SQUARE_SIZE, SQUARE_SIZE);
        Cell boundary = board.getBoundary();
        assertTrue(boundary.getX() == SQUARE_SIZE && boundary.getY() == SQUARE_SIZE);
    }
}

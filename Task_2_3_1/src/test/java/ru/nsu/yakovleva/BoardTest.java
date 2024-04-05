package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;
import ru.nsu.yakovleva.snakegamefx.sprite.BoardFx;
import ru.nsu.yakovleva.snakegamefx.sprite.FruitFx;
import ru.nsu.yakovleva.snakegamefx.sprite.SnakeFx;


/**
 * Test class.
 */
public class BoardTest {
    private final int rowsNumber = 10;
    private final int columnsNumber = rowsNumber;
    private final int squareSize = 20;
    private Board board;

    @BeforeEach
    public void initialize() {
        board = new BoardFx(rowsNumber * squareSize, columnsNumber * squareSize);
    }

    @Test
    void getBoundary() {
        Cell actual = board.getBoundary();
        Cell expected = new Cell(rowsNumber * squareSize, columnsNumber * squareSize);
        assertEquals(expected, actual);
    }

    @Test
    void intersects() {
        Fruit fruit = new FruitFx(squareSize, squareSize);
        fruit.update(rowsNumber, columnsNumber);
        board.intersects(fruit);
        Snake snake = new SnakeFx(squareSize, squareSize);
        snake.start(snake.getLength() * squareSize, (columnsNumber << 1) * squareSize);
        board.intersects(snake);
    }

    @Test
    void update() {
        board.update(squareSize, squareSize);
        Cell boundary = board.getBoundary();
        assertTrue(boundary.getX() == squareSize && boundary.getY() == squareSize);
    }
}

package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;
import ru.nsu.yakovleva.snakegamefx.sprite.FruitFx;
import ru.nsu.yakovleva.snakegamefx.sprite.SnakeFx;


/**
 * Test class.
 */
public class FruitTest {
    private final int squareSize = 20;
    private FruitFx fruit;

    @BeforeEach
    public void initialize() {
        fruit = new FruitFx(squareSize, squareSize);
    }

    @Test
    public void getBoundary() {
        Cell actual = fruit.getBoundary();
        Cell expected = new Cell(squareSize, squareSize);
        expected.setPosition(0, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void intersects() {
        Fruit fruit  = new FruitFx(squareSize, squareSize);
        assertTrue(this.fruit.intersects(fruit));
        Snake snake = new SnakeFx(squareSize, squareSize);
        snake.start(0, 0);
        assertTrue(this.fruit.intersects(snake));
    }

    @Test
    public void update() {
        int maxX = 10 * squareSize;
        int maxY = 10 * squareSize;
        fruit.update(10, 10);
        Cell boundary = fruit.getBoundary();
        assertTrue(boundary.getX() <= maxX && boundary.getY() <= maxY);
    }
}
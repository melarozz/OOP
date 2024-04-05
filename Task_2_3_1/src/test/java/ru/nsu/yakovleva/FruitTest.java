package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;
import ru.nsu.yakovleva.snakegamefx.sprite.FruitFX;
import ru.nsu.yakovleva.snakegamefx.sprite.SnakeFX;


/**
 * Test class.
 */
public class FruitTest {
    private final int SQUARE_SIZE = 20;
    private FruitFX fruit;

    @BeforeEach
    public void initialize() {
        fruit = new FruitFX(SQUARE_SIZE, SQUARE_SIZE);
    }

    @Test
    public void getBoundary() {
        Cell actual = fruit.getBoundary();
        Cell expected = new Cell(SQUARE_SIZE, SQUARE_SIZE);
        expected.setPosition(0,0);
        assertEquals(expected, actual);
    }

    @Test
    public void intersects() {
        Fruit fruit  = new FruitFX(SQUARE_SIZE, SQUARE_SIZE);
        assertTrue(this.fruit.intersects(fruit));
        Snake snake = new SnakeFX(SQUARE_SIZE, SQUARE_SIZE);
        snake.start(0, 0);
        assertTrue(this.fruit.intersects(snake));
    }

    @Test
    public void update() {
        int maxX = 10 * SQUARE_SIZE;
        int maxY = 10 * SQUARE_SIZE;
        fruit.update(10, 10);
        Cell boundary = fruit.getBoundary();
        assertTrue(boundary.getX() <= maxX && boundary.getY() <= maxY);
    }
}
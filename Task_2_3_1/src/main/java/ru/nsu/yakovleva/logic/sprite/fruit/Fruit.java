package ru.nsu.yakovleva.logic.sprite.fruit;

import java.util.List;
import java.util.Random;
import ru.nsu.yakovleva.logic.sprite.Sprite;
import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;


/**
 * Represents a fruit in the game.
 */
public abstract class Fruit implements Sprite {
    private final Cell fruit;
    private final Random random;

    /**
     * Constructs a Fruit instance with the given width and height.
     *
     * @param width  The width of the fruit.
     * @param height The height of the fruit.
     */
    public Fruit(double width, double height) {
        this.fruit = new Cell(width, height);
        this.random = new Random();
    }

    @Override
    public Cell getBoundary() {
        return fruit;
    }

    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite.getBoundary() instanceof Cell boundary) {
            return fruit != boundary && fruit.intersects(boundary);
        }
        if (sprite instanceof Snake snake) {
            List<Cell> boundary = snake.getBoundary();
            return boundary.stream().anyMatch(cell -> fruit != cell && fruit.intersects(cell));
        }
        return false;
    }

    @Override
    public void update(double rowsNumber, double columnsNumber) {
        double fruitX = random.nextInt((int) rowsNumber - 1);
        double fruitY = random.nextInt((int) columnsNumber - 1);
        fruit.setPosition(fruitX * fruit.getWidth(), fruitY * fruit.getHeight());
    }

    @Override
    public abstract void render(Object object);
}

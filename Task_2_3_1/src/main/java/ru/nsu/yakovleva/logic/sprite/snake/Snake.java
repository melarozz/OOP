package ru.nsu.yakovleva.logic.sprite.snake;

import static ru.nsu.yakovleva.logic.sprite.snake.Direction.RIGHT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ru.nsu.yakovleva.logic.sprite.Sprite;
import ru.nsu.yakovleva.logic.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.board.Board;
import ru.nsu.yakovleva.logic.sprite.fruit.Fruit;


/**
 * Represents the snake in the game.
 */
public abstract class Snake implements Sprite {
    private final int initLength = 3;
    private Direction direction;
    private Cell head;
    private final List<Cell> body;

    /**
     * Constructs a Snake instance with the given width and height.
     *
     * @param width  The width of the snake.
     * @param height The height of the snake.
     */
    public Snake(double width, double height) {
        this.body = Stream.generate(() ->
                new Cell(width, height))
                .limit(initLength)
                .collect(
                        Collectors.toCollection(ArrayList::new));
        this.head = this.body.get(0);
    }

    /**
     * Gets the direction of the snake.
     *
     * @return The direction of the snake.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets the length of the snake.
     *
     * @return The length of the snake.
     */
    public int getLength() {
        return body.size();
    }

    /**
     * Sets the direction of the snake.
     *
     * @param direction The direction to set for the snake.
     */
    public void setDirection(Direction direction) {
        if (this.direction.opposite(direction)) {
            return;
        }
        this.direction = direction;
    }

    /**
     * Initializes the snake at the specified position.
     *
     * @param headPositionX The x-coordinate of the snake's head.
     * @param headPositionY The y-coordinate of the snake's head.
     */
    public void start(double headPositionX, double headPositionY) {
        direction = RIGHT;
        head.setPosition(headPositionX, headPositionY);
        for (int i = 1; i < initLength; ++i) {
            body.get(i).setPosition(body.get(i - 1).getX() - head.getWidth(), headPositionY);
        }
    }

    /**
     * Grows the snake by adding a new cell to its body.
     */
    public void grow() {
        Cell flake = new Cell(head.getWidth(), head.getHeight());
        body.add(flake);
    }

    @Override
    public List<Cell> getBoundary() {
        return Collections.unmodifiableList(body);
    }

    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite == this) {
            return body.stream().anyMatch((cell -> cell != head && cell.intersects(head)));
        }
        if (sprite instanceof Board) {
            return head.intersects((Cell) sprite.getBoundary());
        }
        if (sprite instanceof Fruit) {
            return body.stream().anyMatch((cell -> cell.intersects((Cell) sprite.getBoundary())));
        }
        return false;
    }

    @Override
    public void update(double x, double y) {
        Cell head = new Cell(this.head.getWidth(), this.head.getHeight());
        switch (direction) {
            case RIGHT -> head.setPosition(this.head.getX() + x, this.head.getY());
            case LEFT -> head.setPosition(this.head.getX() - x, this.head.getY());
            case UP -> head.setPosition(this.head.getX(), this.head.getY() - y);
            case DOWN -> head.setPosition(this.head.getX(), this.head.getY() + y);
        }
        this.head = head;
        body.add(0, head);
        body.remove(getLength() - 1);
    }

    @Override
    public abstract void render(Object object);
}

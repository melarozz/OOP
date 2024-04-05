package ru.nsu.yakovleva.logic.sprite.board;

import java.util.List;
import ru.nsu.yakovleva.logic.sprite.Sprite;
import ru.nsu.yakovleva.logic.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;
import ru.nsu.yakovleva.logic.sprite.IntersectionUtils;
/**
 * Represents the game board.
 */
public abstract class Board implements Sprite {
    private final Cell board;

    /**
     * Constructs a Board instance with the given width and height.
     *
     * @param width  The width of the board.
     * @param height The height of the board.
     */
    public Board(double width, double height) {
        board = new Cell(width, height);
    }

    @Override
    public Cell getBoundary() {
        return board;
    }

    @Override
    public boolean intersects(Sprite sprite) {
        return IntersectionUtils.handleIntersection(board, sprite);
    }

    @Override
    public void update(double x, double y) {
        board.setPosition(x, y);
    }

    @Override
    public abstract void render(Object object);
}
package ru.nsu.yakovleva.logic.sprite.board;

import ru.nsu.yakovleva.logic.sprite.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.Sprite;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;

import java.util.List;

public abstract class Board implements Sprite {
    private final Cell board;

    public Board(double width, double height) {
        board = new Cell(width, height);
    }

    @Override
    public Cell getBoundary() {
        return board;
    }

    @Override
    public boolean intersects(Sprite sprite) {
        if (sprite.getBoundary() instanceof Cell cell) {
            return board != cell && board.intersects(cell);
        }
        if (sprite instanceof Snake snake) {
            List<Cell> boundary = snake.getBoundary();
            return boundary.stream().anyMatch(cell -> board != cell && board.intersects(cell));
        }
        return false;
    }

    @Override
    public void update(double x, double y) {
        board.setPosition(x, y);
    }

    @Override
    public abstract void render(Object object);
}

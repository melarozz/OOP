package ru.nsu.yakovleva.logic.sprite;

import java.util.List;
import ru.nsu.yakovleva.logic.cell.Cell;
import ru.nsu.yakovleva.logic.sprite.snake.Snake;

/**
 * Class to handle intersection.
 */
public class IntersectionUtils {

    /**
     * Class that returns if item intersects sprite.
     * @param item Item.
     * @param sprite Sprite.
     * @return Boolean
     */
    public static boolean handleIntersection(Cell item, Sprite sprite) {
        if (sprite.getBoundary() instanceof Cell cell) {
            return item != cell && item.intersects(cell);
        }
        if (sprite instanceof Snake snake) {
            List<Cell> boundary = snake.getBoundary();
            return boundary.stream().anyMatch(cell -> item != cell && item.intersects(cell));
        }
        return false;
    }
}

package ru.nsu.yakovleva.logic.sprite.snake;

/**
 * Enum for snake direction.
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * Returns the opposite direction to the given one.
     * @param direction The given direction.
     * @return The opposite direction.
     */
    public boolean opposite(Direction direction) {
        return ((this == UP && direction == DOWN)
                || (this == DOWN && direction == UP)
                || (this == LEFT && direction == RIGHT)
                || (this == RIGHT && direction == LEFT));
    }
}

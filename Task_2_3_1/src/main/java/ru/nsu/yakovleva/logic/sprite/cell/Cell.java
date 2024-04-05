package ru.nsu.yakovleva.logic.sprite.cell;

/**
 * Represents a cell in the game board.
 */
public class Cell {
    private final double width;
    private final double height;
    private double x;
    private double y;

    /**
     * Constructs a Cell instance with the given width and height.
     *
     * @param width  The width of the cell.
     * @param height The height of the cell.
     */
    public Cell(double width, double height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Gets the width of the cell.
     *
     * @return The width of the cell.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the cell.
     *
     * @return The height of the cell.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the x-coordinate of the cell.
     *
     * @return The x-coordinate of the cell.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the cell.
     *
     * @return The y-coordinate of the cell.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the position of the cell.
     *
     * @param x The x-coordinate to set.
     * @param y The y-coordinate to set.
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if this cell intersects with another cell.
     *
     * @param cell The cell to check intersection with.
     * @return True if the cells intersect, false otherwise.
     */
    public boolean intersects(Cell cell) {
        return cell.x + cell.width > x && cell.y + cell.height > y && cell.x < x + width && cell.y < y + height;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Cell cell) {
            return cell.x == x && cell.y == y && cell.x + cell.width == x + width && cell.y + cell.height == y + height;
        }
        return false;
    }
}

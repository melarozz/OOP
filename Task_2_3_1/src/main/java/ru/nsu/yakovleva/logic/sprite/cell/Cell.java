package ru.nsu.yakovleva.logic.sprite.cell;

/**
 * Represents a cell in the game board.
 */
public class Cell {
    private final double width;
    private final double height;
    private double xCoord;
    private double yCoord;

    /**
     * Constructs a Cell instance with the given width and height.
     *
     * @param width  The width of the cell.
     * @param height The height of the cell.
     */
    public Cell(double width, double height) {
        this.width = width;
        this.height = height;
        this.xCoord = 0;
        this.yCoord = 0;
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
        return xCoord;
    }

    /**
     * Gets the y-coordinate of the cell.
     *
     * @return The y-coordinate of the cell.
     */
    public double getY() {
        return yCoord;
    }

    /**
     * Sets the position of the cell.
     *
     * @param xCoord The x-coordinate to set.
     * @param yCoord The y-coordinate to set.
     */
    public void setPosition(double xCoord, double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Checks if this cell intersects with another cell.
     *
     * @param cell The cell to check intersection with.
     * @return True if the cells intersect, false otherwise.
     */
    public boolean intersects(Cell cell) {
        return cell.xCoord + cell.width > xCoord && cell.yCoord
                + cell.height > yCoord && cell.xCoord < xCoord + width
                && cell.yCoord < yCoord + height;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Cell cell) {
            return cell.xCoord == xCoord
                    && cell.yCoord == yCoord
                    && cell.xCoord + cell.width == xCoord + width
                    && cell.yCoord + cell.height == yCoord + height;
        }
        return false;
    }
}

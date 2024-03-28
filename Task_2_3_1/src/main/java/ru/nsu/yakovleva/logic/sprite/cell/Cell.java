package ru.nsu.yakovleva.logic.sprite.cell;

public class Cell {
    private final double width;
    private final double height;
    private double x;
    private double y;

    public Cell(double width, double height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

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


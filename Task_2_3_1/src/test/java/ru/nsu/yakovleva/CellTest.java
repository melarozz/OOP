package ru.nsu.yakovleva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.yakovleva.logic.cell.Cell;


/**
 * Test class.
 */
public class CellTest {
    private final double width = 10;
    private final double height = 20;
    private final double xCoord = -5;
    private final double yCoord = 3;
    private Cell cell;

    @BeforeEach
    public void initialize() {
        cell = new Cell(width, height);
    }

    @Test
    public void getWidth() {
        cell = new Cell(width, height);
        assertEquals(width, cell.getWidth());
    }

    @Test
    public void getHeight() {
        cell = new Cell(width, height);
        assertEquals(height, cell.getHeight());
    }

    @Test
    public void getX() {
        cell.setPosition(xCoord, yCoord);
        assertEquals(xCoord, cell.getX());
    }

    @Test
    public void getY() {
        cell.setPosition(xCoord, yCoord);
        assertEquals(yCoord, cell.getY());
    }

    @Test
    public void intersects() {
        Cell cell = new Cell(1000, 1000);
        assertTrue(this.cell.intersects(cell));
    }

    @Test
    public void testEquals() {
        Cell cell = new Cell(width, height);
        assertEquals(this.cell, this.cell);
        assertEquals(cell, this.cell);
        cell.setPosition(xCoord, yCoord);
        assertNotEquals(cell, this.cell);
    }
}

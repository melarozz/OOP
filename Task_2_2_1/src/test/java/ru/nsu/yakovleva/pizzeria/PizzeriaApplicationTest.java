package ru.nsu.yakovleva.pizzeria;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

public class PizzeriaApplicationTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void testPizzeriaConfigurationSetup() {
        PizzeriaApplication app = new PizzeriaApplication();
        assertNotNull(app.pizzeriaJson, "Pizzeria configuration should not be null");
    }

    @Test
    public void testPizzeriaInitialization() {
        PizzeriaApplication app = new PizzeriaApplication();
        assertNotNull(app.pizzeria, "Pizzeria instance should not be null");
    }

    @Test
    public void testPizzeriaJson() {
        PizzeriaApplication app = new PizzeriaApplication();
        app.setPizzeriaJson();
        assertNotNull(app.pizzeriaJson);
    }

    @Test
    public void testPizzeria() {
        PizzeriaApplication app = new PizzeriaApplication();
        app.setPizzeriaJson();
        app.setPizzeria();
        assertNotNull(app.pizzeria);
    }

}
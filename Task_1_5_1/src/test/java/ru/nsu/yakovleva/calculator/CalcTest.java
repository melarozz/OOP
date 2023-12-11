package ru.nsu.yakovleva.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Test class.
 */
public class CalcTest {

    @Test
    void testBasicExpression() {
        String expression = "-n sin + - 1 2 1";
        double result = Main.calculate(expression);
        assertEquals(0, result, 0.0001);
    }

    @Test
    void testBasicExpressionDegrees() {
        String expression = "-d sin + -10 10";
        double result = Main.calculate(expression);
        assertEquals(0, result, 0.0001);
    }

    @Test
    void testLog() {
        String expression = "-n log 2 8";
        double result = Main.calculate(expression);
        assertEquals(3, result, 0.0001);
    }

    @Test
    void testAllOperations() {
        String expression = "-n + * sin - cos 0 1 / cos 1 4 log 2 8";
        double result = Main.calculate(expression);
        assertEquals(3, result, 0.0001);
    }

    @Test
    void testExpression() {
        String expression = "-n / * + - + log 2 8 1 6 1 9 3";
        double result = Main.calculate(expression);
        assertEquals(-3, result, 0.0001);
    }

    @Test
    void testAddition() {
        String expression = "-n + 5 3";
        double result = Main.calculate(expression);
        assertEquals(8, result, 0.0001);
    }

    @Test
    void testSubtraction() {
        String expression = "-n - 5 3";
        double result = Main.calculate(expression);
        assertEquals(2, result, 0.0001);
    }

    @Test
    void testMultiplication() {
        String expression = "-n * 5 3";
        double result = Main.calculate(expression);
        assertEquals(15, result, 0.0001);
    }

    @Test
    void testDivision() {
        String expression = "-n / 10 2";
        double result = Main.calculate(expression);
        assertEquals(5, result, 0.0001);
    }

    @Test
    void testSineFunction() {
        String expression = "-n sin 0";
        double result = Main.calculate(expression);
        assertEquals(0, result, 0.0001);
    }

    @Test
    void testSineDegrees0Function() {
        String expression = "-d sin 0";
        double result = Main.calculate(expression);
        assertEquals(0, result, 0.0001);
    }

    @Test
    void testSineDegrees90Function() {
        String expression = "-d sin 90";
        double result = Main.calculate(expression);
        assertEquals(1, result, 0.0001);
    }

    @Test
    void testSineDegrees30Function() {
        String expression = "-d sin 30";
        double result = Main.calculate(expression);
        assertEquals(0.5, result, 0.0001);
    }

    @Test
    void testCosineFunction() {
        String expression = "-n cos 0";
        double result = Main.calculate(expression);
        assertEquals(1, result, 0.0001);
    }

    @Test
    void testCosineDegrees0Function() {
        String expression = "-d cos 0";
        double result = Main.calculate(expression);
        assertEquals(1, result, 0.0001);
    }

    @Test
    void testCosineDegrees90Function() {
        String expression = "-d cos 90";
        double result = Main.calculate(expression);
        assertEquals(0, result, 0.0001);
    }

    @Test
    void testCosineDegrees60Function() {
        String expression = "-d cos 60";
        double result = Main.calculate(expression);
        assertEquals(0.5, result, 0.0001);
    }

    @Test
    void testDivisionByZero() {
        String expression = "-n / 5 0";
        assertThrows(ArithmeticException.class, () -> Main.calculate(expression));
    }

    @Test
    void testEmptyStackException() {
        String expression = "-n log 10"; // Log requires two operands
        assertThrows(NoSuchElementException.class, () -> Main.calculate(expression));
    }

    @Test
    void testLogInvalidBaseException() {
        String expression = "-n log -2 10";
        assertThrows(ArithmeticException.class, () -> Main.calculate(expression));
    }

    @Test
    void testLogInvalidBase2Exception() {
        String expression = "-n log 1 10";
        assertThrows(ArithmeticException.class, () -> Main.calculate(expression));
    }

    @Test
    void testLogInvalidNumberException() {
        String expression = "-n log 2 -10";
        assertThrows(ArithmeticException.class, () -> Main.calculate(expression));
    }

    @Test
    void testLogDegreesException() {
        String expression = "-d log 2 8";
        assertThrows(IllegalArgumentException.class, () -> Main.calculate(expression));
    }

    @Test
    void testLogInvalidFlagException() {
        String expression = "-t log 2 -10";
        assertThrows(IllegalArgumentException.class, () -> Main.calculate(expression));
    }

    @Test
    void testNoFlagException() {
        String expression = "log 2 -10";
        assertThrows(IllegalArgumentException.class, () -> Main.calculate(expression));
    }

    @Test
    void testLettersException() {
        String expression = "+ a b";
        assertThrows(IllegalArgumentException.class, () -> Main.calculate(expression));
    }
}

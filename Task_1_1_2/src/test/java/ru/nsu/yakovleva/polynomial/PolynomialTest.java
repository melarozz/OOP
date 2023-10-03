package ru.nsu.yakovleva.polynomial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PolynomialTest {

    @Test
    public void testEquals() {
        int[] coefficients1 = {3}; // 3
        int[] coefficients2 = {3}; // 3
        Polynomial poly1 = new Polynomial(coefficients1);
        Polynomial poly2 = new Polynomial(coefficients2);
        boolean result = poly1.equals(poly2);
        assertTrue(result);
    }

    @Test
    public void testEqualsFalse() {
        int[] coefficients1 = {3}; // 3
        int[] coefficients2 = {4}; // 4
        Polynomial poly1 = new Polynomial(coefficients1);
        Polynomial poly2 = new Polynomial(coefficients2);
        boolean result = poly1.equals(poly2);
        assertFalse(result);
    }

    @Test
    public void testEqualsTrueLarge() {
        int[] coefficients1 = {3, 4, 5, 6};
        int[] coefficients2 = {3, 4, 5, 6};
        Polynomial poly1 = new Polynomial(coefficients1);
        Polynomial poly2 = new Polynomial(coefficients2);
        boolean result = poly1.equals(poly2);
        assertTrue(result);
    }

    @Test
    public void testGetCoefOne() {
        int[] coefficients = {3}; // 3
        Polynomial poly = new Polynomial(coefficients);
        assertEquals(coefficients, poly.getCoefficients());
    }

    @Test
    public void testGetCoefMany() {
        int[] coefficients = {4, 5, 6, 7, 8, 9};
        Polynomial poly = new Polynomial(coefficients);
        assertEquals(coefficients, poly.getCoefficients());
    }

    @Test
    public void testEvaluateOne() {
        int[] coefficients = {3, 2, 1}; // 3x^2 + 2x + 1
        Polynomial poly = new Polynomial(coefficients);

        assertEquals(6, poly.evaluate(1)); // 3(1)^2 + 2(1) + 1 = 6
    }

    @Test
    public void testEvaluateTwo() {
        int[] coefficients = {3, 2, 1}; // 3x^2 + 2x + 1
        Polynomial poly = new Polynomial(coefficients);

        assertEquals(17, poly.evaluate(2)); // 3*(2)^2 + 2(2) + 1 = 17
    }

    @Test
    public void testEvaluateZero() {
        int[] coefficients = {3, 2, 1}; // 3x^2 + 2x + 1
        Polynomial poly = new Polynomial(coefficients);

        assertEquals(1, poly.evaluate(0));  // 3(0)^2 + 2(0) + 1 = 1
    }

    @Test
    public void testEvaluateLarge() {
        int[] coefficients = {3, 2, 1}; // 3x^2 + 2x + 1
        Polynomial poly = new Polynomial(coefficients);

        assertEquals(1471401, poly.evaluate(700)); // 3*(2)^2 + 2(2) + 1 = 17
    }

    @Test
    public void testAdd() {
        int[] coefficients1 = {1, 2, 3}; // x^2 + 2x + 3
        int[] coefficients2 = {2, 1};    // 2x + 1
        Polynomial poly1 = new Polynomial(coefficients1);
        Polynomial poly2 = new Polynomial(coefficients2);

        Polynomial sum = poly1.add(poly2);
        int[] expectedCoefficients = {3, 3, 3}; // 3x^2 + 4x + 4

        assertArrayEquals(expectedCoefficients, sum.getCoefficients());
    }

    @Test
    void testAddNegative() {
        Polynomial p1 = new Polynomial(new int[] {-3, -2, -9});//-3x^2-2x-9
        Polynomial p2 = new Polynomial(new int[] {4, 3, 2});//4x^2+3x+2
        assertArrayEquals(new int[] {1,1,-7}, p1.add(p2).getCoefficients());
    }

    @Test
    void testAddOne() {
        Polynomial p1 = new Polynomial(new int[] {-3});//-3
        Polynomial p2 = new Polynomial(new int[] {4});//4
        assertArrayEquals(new int[] {1}, p1.add(p2).getCoefficients());
    }

    @Test
    public void testMinus() {
        int[] coefficients1 = {3, 2, 1}; // 3x^2 + 2x + 1
        int[] coefficients2 = {1, 2};    // x^2 + 2x
        Polynomial poly1 = new Polynomial(coefficients1);
        Polynomial poly2 = new Polynomial(coefficients2);

        Polynomial difference = poly1.minus(poly2);
        int[] expectedCoefficients = {2, 0, 1}; // 2x^2 + 1

        assertArrayEquals(expectedCoefficients, difference.getCoefficients());
    }

    @Test
    public void testMultiply() {
        int[] coefficients1 = {2, 1};    // 2x + 1
        int[] coefficients2 = {1, 3};    // x + 3
        Polynomial poly1 = new Polynomial(coefficients1);
        Polynomial poly2 = new Polynomial(coefficients2);

        Polynomial product = poly1.multiply(poly2);
        int[] expectedCoefficients = {2, 7, 3}; // 2x^2 + 7x + 3

        assertArrayEquals(expectedCoefficients, product.getCoefficients());
    }

    @Test
    public void testMultiplyDif() {
        int[] coefficients1 = {2};    // 2
        int[] coefficients2 = {1, 3};    // x + 3
        Polynomial poly1 = new Polynomial(coefficients1);
        Polynomial poly2 = new Polynomial(coefficients2);

        Polynomial product = poly1.multiply(poly2);
        int[] expectedCoefficients = {2, 6};

        assertArrayEquals(expectedCoefficients, product.getCoefficients());
    }

    @Test
    public void testToString() {
        int[] coefficients = {3, -2, 1, 2}; // 3x^3 - 2x^2 + x + 2
        Polynomial poly = new Polynomial(coefficients);

        assertEquals("3x^3 - 2x^2 + 1x + 2", poly.toString());
    }

    @Test
    public void testToStringOne() {
        int[] coefficients = {3};
        Polynomial poly = new Polynomial(coefficients);

        assertEquals("3", poly.toString());
    }

    @Test
    public void testMain() {
        assert(true);
    }

}

package ru.nsu.yakovleva.polynomial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PolynomialTest {

    private Polynomial polynomial1;
    private Polynomial polynomial2;

    @Test
    public void testPlus() {
        int[] coefficients1 = {1,2}; // x+2
        int[] coefficients2 = {3}; // 3
        polynomial1 = new Polynomial(coefficients1);
        polynomial2 = new Polynomial(coefficients2);
        Polynomial result = polynomial1.plus(polynomial2);
        int[] expectedCoefficients = {1,5};
        Polynomial expectedPolynomial = new Polynomial(expectedCoefficients);
        assertEquals(expectedPolynomial.degree, result.degree);
    }

    @Test
    public void testMinus() {
        int[] coefficients1 = {1,2}; // x+2
        int[] coefficients2 = {3}; // 3
        polynomial1 = new Polynomial(coefficients1);
        polynomial2 = new Polynomial(coefficients2);
        Polynomial result = polynomial1.minus(polynomial2);
        int[] expectedCoefficients = {1,-1};
        Polynomial expectedPolynomial = new Polynomial(expectedCoefficients);
        assertEquals(expectedPolynomial.degree, result.degree);
        assertArrayEquals(expectedPolynomial.coefs, result.coefs);
    }

    @Test
    public void testTimes() {
        int[] coefficients1 = {1,2,3}; // x^2+2x+3
        int[] coefficients2 = {3}; // 3
        polynomial1 = new Polynomial(coefficients1);
        polynomial2 = new Polynomial(coefficients2);
        Polynomial result = polynomial1.times(polynomial2);
        int[] expectedCoefficients = {3,6,9};
        Polynomial expectedPolynomial = new Polynomial(expectedCoefficients);
        assertEquals(expectedPolynomial.degree, result.degree);
        assertArrayEquals(expectedPolynomial.coefs, result.coefs);
    }

    @Test
    public void testEval() {
        int[] coefficients1 = {1,2,3}; // x^2+2x+3
        polynomial1 = new Polynomial(coefficients1);
        int actual = polynomial1.evaluate(10);
        int expected = 123;
        assertEquals(expected, actual);
    }


    @Test
    void checkEquality() {
        Polynomial p1 = new Polynomial(new int[] {1, 2, 3, 7});
        Polynomial p2 = new Polynomial(new int[] {1, 2, 3, 7});

        if (p1.equals(p2)) {
            assertTrue(true);
        }
        else {
            assertFalse(false);
        }
    }

    @Test
    void checkEqualityNot() {
        Polynomial p1 = new Polynomial(new int[] {1, 2, 3, 7});
        Polynomial p2 = new Polynomial(new int[] {1, 3});

        if (!p1.equals(p2)) {
            assertTrue(true);
        }
        else {
            assertFalse(false);
        }
    }


}
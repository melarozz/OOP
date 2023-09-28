package ru.nsu.yakovleva.polynomial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

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




}
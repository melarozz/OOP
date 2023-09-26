package ru.nsu.yakovleva.polynomial;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolynomialTest {

    private Polynomial polynomial1;
    private Polynomial polynomial2;

    @BeforeEach
    public void setUp() {
        int[] coefficients1 = {1, 2, 3}; // x^2 + 2x + 3
        int[] coefficients2 = {3, 2, 1}; // 3x^2 + 2x + 1
        polynomial1 = new Polynomial(coefficients1);
        polynomial2 = new Polynomial(coefficients2);
    }

    @Test
    public void testDegree() {
        assertEquals(2, polynomial1.degree());
        assertEquals(2, polynomial2.degree());
    }

    @Test
    public void testGetCoefficient() {
        assertEquals(1, polynomial1.getCoefficient(2));
        assertEquals(2, polynomial1.getCoefficient(1));
        assertEquals(3, polynomial1.getCoefficient(0));

        assertEquals(3, polynomial2.getCoefficient(2));
        assertEquals(2, polynomial2.getCoefficient(1));
        assertEquals(1, polynomial2.getCoefficient(0));
    }

    @Test
    public void testPlus() {
        Polynomial result = polynomial1.plus(polynomial2);
        int[] expectedCoefficients = {4, 4, 4}; // 1+3, 2+2, 3+1
        Polynomial expectedPolynomial = new Polynomial(expectedCoefficients);
        assertEquals(expectedPolynomial.degree(), result.degree());

        for (int i = result.degree(); i >=0; i--) {
            assertEquals(expectedPolynomial.getCoefficient(i), result.getCoefficient(i));
        }
    }

    @Test
    public void testMinus() {
        int[] coefficients1 = {4}; // 4
        int[] coefficients2 = {7}; // 7
        Polynomial polynomial1 = new Polynomial(coefficients1);
        Polynomial polynomial2 = new Polynomial(coefficients2);

        Polynomial result = polynomial1.minus(polynomial2);
        int[] expectedCoefficients = {-3}; // 4-7
        Polynomial expectedPolynomial = new Polynomial(expectedCoefficients);
        assertEquals(expectedPolynomial.degree(), result.degree());

        for (int i = result.degree(); i >= 0 ; i--) {
            assertEquals(expectedPolynomial.getCoefficient(i), result.getCoefficient(i));
        }
    }



    @Test
    public void testTimes() {
        int[] coefficients1 = {1}; // 1
        int[] coefficients2 = {3}; // 3
        Polynomial polynomial1 = new Polynomial(coefficients1);
        Polynomial polynomial2 = new Polynomial(coefficients2);

        Polynomial result = polynomial1.times(polynomial2);
        int[] expectedCoefficients = {3}; // (1*3)
        Polynomial expectedPolynomial = new Polynomial(expectedCoefficients);
        assertEquals(expectedPolynomial.degree(), result.degree());

        for (int i = 0; i <= result.degree(); i++) {
            assertEquals(expectedPolynomial.getCoefficient(i), result.getCoefficient(i));
        }
    }


    @Test
    public void testEvaluateWithLinearPolynomial() {
        int[] coefficients = {2, 3}; // 2 + 3x
        Polynomial polynomial = new Polynomial(coefficients);

        int x = 5;

        int expectedResult = 17;
        int result = polynomial.evaluate(x);

        assertEquals(expectedResult, result);
    }






}
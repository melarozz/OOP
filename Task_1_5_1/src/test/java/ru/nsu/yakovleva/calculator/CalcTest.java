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
        String result = Calculator.calculate(expression);
        assertEquals("0.0", result);
    }

    @Test
    void testBasicExpressionDegrees() {
        String expression = "-d sin + -10 10";
        String result = Calculator.calculate(expression);
        assertEquals("0.0", result);
    }

    @Test
    void testLog() {
        String expression = "-n log 2 8";
        String result = Calculator.calculate(expression);
        assertEquals("3.0", result);
    }

    @Test
    void testAllOperations() {
        String expression = "-n + * sin - cos 0 1 / cos 1 4 log 2 8";
        String result = Calculator.calculate(expression);
        assertEquals("3.0", result);
    }

    @Test
    void testExpression() {
        String expression = "-n / * + - + log 2 8 1 6 1 9 3";
        String result = Calculator.calculate(expression);
        assertEquals("-3.0", result);
    }

    @Test
    void testAddition() {
        String expression = "-n + 5 3";
        String result = Calculator.calculate(expression);
        assertEquals("8.0", result);
    }

    @Test
    void testSubtraction() {
        String expression = "-n - 5 3";
        String result = Calculator.calculate(expression);
        assertEquals("2.0", result);
    }

    @Test
    void testMultiplication() {
        String expression = "-n * 5 3";
        String result = Calculator.calculate(expression);
        assertEquals("15.0", result);
    }

    @Test
    void testDivision() {
        String expression = "-n / 10 2";
        String result = Calculator.calculate(expression);
        assertEquals("5.0", result);
    }

    @Test
    void testSineFunction() {
        String expression = "-n sin 0";
        String result = Calculator.calculate(expression);
        assertEquals("0.0", result);
    }

    @Test
    void testSineDegrees0Function() {
        String expression = "-d sin 0";
        String result = Calculator.calculate(expression);
        assertEquals("0.0", result);
    }

    @Test
    void testSineDegrees90Function() {
        String expression = "-d sin 90";
        String result = Calculator.calculate(expression);
        assertEquals("1.0", result);
    }

    @Test
    void testSineDegrees30Function() {
        String expression = "-d sin 30";
        String result = Calculator.calculate(expression);
        Double result1 = Double.parseDouble(result);
        assertEquals(0.5, result1, 0.000001);
    }

    @Test
    void testCosineFunction() {
        String expression = "-n cos 0";
        String result = Calculator.calculate(expression);
        assertEquals("1.0", result);
    }

    @Test
    void testCosineDegrees0Function() {
        String expression = "-d cos 0";
        String result = Calculator.calculate(expression);
        assertEquals("1.0", result);
    }

    @Test
    void testCosineDegrees90Function() {
        String expression = "-d cos 90";
        String result = Calculator.calculate(expression);
        Double result1 = Double.parseDouble(result);
        assertEquals(0, result1, 0.000001);
    }

    @Test
    void testCosineDegrees60Function() {
        String expression = "-d cos 60";
        String result = Calculator.calculate(expression);
        Double result1 = Double.parseDouble(result);
        assertEquals(0.5, result1, 0.000001);
    }

    @Test
    void testDivisionByZero() {
        String expression = "-n / 5 0";
        assertThrows(ArithmeticException.class, () -> Calculator.calculate(expression));
    }

    @Test
    void testEmptyStackException() {
        String expression = "-n log 10"; // Log requires two operands
        assertThrows(NoSuchElementException.class, () -> Calculator.calculate(expression));
    }

    @Test
    void testLogInvalidBaseException() {
        String expression = "-n log -2 10";
        assertThrows(ArithmeticException.class, () -> Calculator.calculate(expression));
    }

    @Test
    void testLogInvalidBase2Exception() {
        String expression = "-n log 1 10";
        assertThrows(ArithmeticException.class, () -> Calculator.calculate(expression));
    }

    @Test
    void testLogInvalidNumberException() {
        String expression = "-n log 2 -10";
        assertThrows(ArithmeticException.class, () -> Calculator.calculate(expression));
    }

    @Test
    void testLogDegreesException() {
        String expression = "-d log 2 8";
        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate(expression));
    }

    @Test
    void testLogInvalidFlagException() {
        String expression = "-t log 2 -10";
        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate(expression));
    }

    @Test
    void testNoFlagException() {
        String expression = "log 2 -10";
        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate(expression));
    }

    @Test
    void testLettersException() {
        String expression = "+ a b";
        assertThrows(IllegalArgumentException.class, () -> Calculator.calculate(expression));
    }

    @Test
    void testComplexPlus() {
        String expression = "-c + [1,3] [4,3]";
        String result = Calculator.calculate(expression);
        assertEquals("5.0 + 6.0i", result);
    }

    @Test
    void testComplexMinus() {
        String expression = "-c - [1,3] [4,3]";
        String result = Calculator.calculate(expression);
        assertEquals("-3.0 + 0.0i", result);
    }

    @Test
    void testComplexMult() {
        String expression = "-c * [1,3] [4,3]";
        String result = Calculator.calculate(expression);
        assertEquals("-5.0 + 15.0i", result);
    }

    @Test
    void testComplexDiv() {
        String expression = "-c / [1,3] [4,3]";
        String result = Calculator.calculate(expression);
        assertEquals("0.52 + 0.36i", result);
    }

    @Test
    void testComplexSin() {
        String expression = "-c sin [0,0]";
        String result = Calculator.calculate(expression);
        assertEquals("0.0 + 0.0i", result);

        expression = "-c sin [1,0]";
        result = Calculator.calculate(expression);
        assertEquals("0.8414709848078965 + 0.0i", result);
    }

    @Test
    void testComplexCos() {
        String expression = "-c cos [0.5,1]";
        String result = Calculator.calculate(expression);
        assertEquals("1.3541806567045844 - 0.5634214652309818i", result);
    }

    @Test
    void testComplexLog() {
        String expression = "-c log [3,10]";
        String result = Calculator.calculate(expression);
        assertEquals("2.3456739411145717 + 1.2793395323170296i", result);
    }

    @Test
    void testComplexCalculator_AllOperations() {
        // Testing an expression with all operations
        String expression = "-c sin cos log - + * / [2,3] [4,5] [2,3] [4,5] [6,7]";
        //sin(cos(ln((2+3i)/(4+5i)*(2+3i)+(4+5i)-(6+7i))))
        String expectedResult = "0.04358155652462895 - 0.4481211431626154i";
        String actualResult = Calculator.calculate(expression);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testDegreesCalculator_AllOperations() {
        // Testing an expression with all operations
        String expression = "-d sin cos - + * / 20 444 25 43 60";
        //sin(cos(20/444*25+43-60))
        String expectedResult = "0.016786942425275474";
        String actualResult = Calculator.calculate(expression);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testNormalCalculator_AllOperations() {
        // Testing an expression with all operations
        String expression = "-n sin cos log  - + * / 4 2 2 3 1 100";
        //sin(cos(log(4/2*2+3-1, 100)))
        String expectedResult = "-0.7454072896620093";
        String actualResult = Calculator.calculate(expression);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testNonIntegerValues() {
        String expression = "-n / 5.5 2.2";
        String result = Calculator.calculate(expression);
        assertEquals("2.5", result);
    }

}

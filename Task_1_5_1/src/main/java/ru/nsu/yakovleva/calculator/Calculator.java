package ru.nsu.yakovleva.calculator;

/**
 * A simple calculator that evaluates expressions in prefix form.
 */
public class Calculator {

    enum Flag {
        NORMAL, DEGREES, COMPLEX
    }

    static Flag flag = Flag.NORMAL;

    /**
     * Sets the flag for the calculator.
     *
     * @param newFlag The new flag value (-n or -d)
     */
    private static void setFlag(String newFlag) {
        switch (newFlag) {
            case "-n ":
                flag = Flag.NORMAL;
                break;
            case "-d ":
                flag = Flag.DEGREES;
                break;
            case "-c ":
                flag = Flag.COMPLEX;
                break;
            default:
                throw new IllegalArgumentException("Invalid flag");
        }
    }

    /**
     * Extracts the flag from the expression and sets it accordingly.
     *
     * @param expression The expression in prefix form to be evaluated, including the flag
     * @return The expression without the flag
     */
    private static String extractFlag(String expression) {
        String flag = expression.substring(0, 3);

        switch (flag) {
            case "-n ":
                setFlag(flag);
                return expression.substring(3);
            case "-d ":
                setFlag(flag);
                return expression.substring(3);
            case "-c ":
                setFlag(flag);
                return expression.substring(3);
            default:
                throw new IllegalArgumentException("Flag not provided or invalid");
        }
    }

    /**
     * Evaluates a prefix expression and returns the result.
     *
     * @param expressionWithFlag The expression in prefix form with the flag to be evaluated
     * @return The result of the expression evaluation
     * @throws IllegalArgumentException If the expression is invalid
     * @throws ArithmeticException      If there is a division by zero
     */
    public static String calculate(String expressionWithFlag) {
        String expression = extractFlag(expressionWithFlag);

        CalculatorStrategy calculator;

        switch (flag) {
            case NORMAL:
            case DEGREES:
                calculator = new NormalAndDegrees();
                break;
            case COMPLEX:
                calculator = new Complex();
                break;
            default:
                throw new IllegalArgumentException("Unknown flag");
        }

        return calculator.calculate(expression, flag);
    }

}

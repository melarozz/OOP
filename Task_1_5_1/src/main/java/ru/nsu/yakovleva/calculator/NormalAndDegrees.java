package ru.nsu.yakovleva.calculator;

import java.util.Deque;
import java.util.LinkedList;

/**
 * A calculator strategy that performs arithmetic and trigonometric operations on numeric values,
 * including support for degree-based trigonometric functions and logarithmic operations.
 */
public class NormalAndDegrees implements CalculatorStrategy {

    static boolean isDegreeFlag = false;

    /**
     * Calculates the result of an expression based on the provided flag,
     * supporting degree-based trigonometry
     * and logarithmic functions.
     *
     * @param expressionWithFlag The expression to be calculated, including the specific flag.
     * @param flag               The flag indicating the type of operation to perform
     *                          (NORMAL or DEGREES).
     * @return The result of the calculation as a string representation of a numeric value.
     * @throws IllegalArgumentException If the expression is invalid or if
     *                                  an unsupported operator/function is used.
     * @throws ArithmeticException      If there is a division by zero or
     *                                  invalid arguments for logarithmic functions.
     */
    @Override
    public String calculate(String expressionWithFlag, Calculator.Flag flag) {
        isDegreeFlag = (flag == Calculator.Flag.DEGREES);
        String[] tokens = expressionWithFlag.split("\\s+");
        Deque<Double> stack = new LinkedList<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (!isNumeric(token) && !isOperator(token)) {
                throw new IllegalArgumentException("Invalid expression");
            }
            if (isOperator(token)) {
                switch (token) {
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        double operand1 = stack.pop();
                        double operand2 = stack.pop();
                        stack.push(performOperation(token, operand1, operand2));
                        break;
                    case "sin":
                    case "cos":
                        double operand = stack.pop();
                        stack.push(performTrigonometricFunction(token, operand));
                        break;
                    case "log":
                        if (isDegreeFlag) {
                            throw new IllegalArgumentException(
                                    "Logarithm function in degrees not supported");
                        }
                        double base = stack.pop();
                        double number = stack.pop();
                        stack.push(performLogarithmFunction(base, number));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator");
                }
            } else {
                if (isNumeric(token)) {
                    stack.push(Double.parseDouble(token));
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            }
        }

        return stack.pop().toString();
    }

    /**
     * Performs arithmetic operations based on the operator.
     *
     * @param operator The operator (+, -, *, /)
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @return The result of the arithmetic operation
     * @throws IllegalArgumentException If the operator is invalid
     * @throws ArithmeticException      If there is a division by zero
     */
    private static double performOperation(String operator, double operand1, double operand2) {
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> {
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield operand1 / operand2;}
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }

    /**
     * Checks if a given string is an operator.
     *
     * @param str The string to be checked
     * @return True if the string represents an operator, false otherwise
     */
    private static boolean isOperator(String str) {
        return ("+".equals(str)) || ("-".equals(str)) || ("*".equals(str))
                || ("/".equals(str)) || ("sin".equals(str)) || ("cos".equals(str))
                || ("log".equals(str));
    }

    /**
     * Performs trigonometric functions based on the specified function.
     *
     * @param function The trigonometric function (sin, cos, sind, cosd)
     * @param operand  The operand for the trigonometric function
     * @return The result of the trigonometric function
     * @throws IllegalArgumentException If the function is invalid
     */
    private static double performTrigonometricFunction(String function, double operand) {
        return switch (function) {
            case "sin" -> (isDegreeFlag) ? Math.sin(Math.toRadians(operand)) : Math.sin(operand);
            case "cos" -> (isDegreeFlag) ? Math.cos(Math.toRadians(operand)) : Math.cos(operand);
            default -> throw new IllegalArgumentException("Invalid function");
        };
    }

    /**
     * Performs logarithmic functions based on the specified base and number.
     *
     * @param base   The base of the logarithm
     * @param number The number for the logarithm
     * @return The result of the logarithmic function
     * @throws IllegalArgumentException If the arguments are invalid
     */
    private static double performLogarithmFunction(double base, double number) {
        if (number <= 0 || base <= 0 || base == 1) {
            throw new ArithmeticException("Invalid arguments");
        }
        return Math.log(number) / Math.log(base);
    }

    /**
     * Checks if a given string is numeric.
     *
     * @param str The string to be checked
     * @return True if the string represents a numeric value, false otherwise
     */
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
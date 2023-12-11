package ru.nsu.yakovleva.calculator;

import java.lang.Math;
import java.util.Deque;
import java.util.LinkedList;

/**
 * A simple calculator that evaluates expressions in prefix form.
 */
public class Main {

    enum Flag {
        NORMAL, DEGREES
    }

    private static Flag flag = Flag.NORMAL;

    /**
     * Sets the flag for the calculator.
     *
     * @param newFlag The new flag value (-n or -d)
     */
    private static void setFlag(String newFlag) {
        if (newFlag.equals("-n")) {
            flag = Flag.NORMAL;
        } else if (newFlag.equals("-d")) {
            flag = Flag.DEGREES;
        } else {
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
        if (expression.startsWith("-n ")) {
            setFlag("-n");
            return expression.substring(3); // Remove the flag (-n)
        } else if (expression.startsWith("-d ")) {
            setFlag("-d");
            return expression.substring(3); // Remove the flag (-d)
        } else {
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
    public static double calculate(String expressionWithFlag) {
        String expression = extractFlag(expressionWithFlag);
        String[] tokens = expression.split("\\s+");
        Deque<Double> stack = new LinkedList<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            switch (token) {
                case "+", "-", "*", "/", "sin", "cos", "log" -> {
                    if (flag == Flag.DEGREES && (token.equals("log"))) {
                        throw new IllegalArgumentException("Logarithm function in degrees not supported");
                    }
                    double operand1, operand2;
                    switch (token) {
                        case "+", "-", "*", "/" -> {
                            operand1 = stack.pop();
                            operand2 = stack.pop();
                            stack.push(performOperation(token, operand1, operand2));
                        }
                        case "sin", "cos" -> {
                            operand1 = stack.pop();
                            stack.push(performTrigonometricFunction(token, operand1));
                        }
                        case "log" -> {
                            double base = stack.pop();
                            double number = stack.pop();
                            stack.push(performLogarithmFunction(base, number));
                        }
                    }
                }
                default -> {
                    if (isNumeric(token)) {
                        stack.push(Double.parseDouble(token));
                    } else {
                        throw new IllegalArgumentException("Invalid expression");
                    }
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return stack.pop();
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
        switch (operator) {
            case "+" -> {
                return operand1 + operand2;
            }
            case "-" -> {
                return operand1 - operand2;
            }
            case "*" -> {
                return operand1 * operand2;
            }
            case "/" -> {
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            }
            default -> throw new IllegalArgumentException("Invalid operator");
        }
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
        switch (function) {
            case "sin" -> {
                return flag == Flag.DEGREES ? Math.sin(Math.toRadians(operand)) : Math.sin(operand);
            }
            case "cos" -> {
                return flag == Flag.DEGREES ? Math.cos(Math.toRadians(operand)) : Math.cos(operand);
            }
            default -> throw new IllegalArgumentException("Invalid function");
        }
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

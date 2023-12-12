package ru.nsu.yakovleva.calculator;

import java.util.Deque;
import java.util.LinkedList;

/**
 * A calculator strategy for performing operations on complex numbers based on provided expressions.
 */
public class Complex implements CalculatorStrategy {

    /**
     * Calculates the result of a complex expression based on the provided flag.
     *
     * @param expression The expression to be calculated.
     * @param flag       The flag indicating the type of operation to perform.
     * @return The result of the calculation as a string representation of a complex number.
     * @throws IllegalArgumentException If the expression is invalid or if an
     *                                  unsupported operator/function is used.
     */
    @Override
    public String calculate(String expression, Calculator.Flag flag) {
        String[] tokens = expression.split("\\s+");
        Deque<ComplexNumber> stack = new LinkedList<>();
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            boolean isOperator = ("+".equals(token)) || ("-".equals(token))
                    || ("*".equals(token)) || ("/".equals(token))
                    || ("sin".equals(token)) || ("cos".equals(token)) || ("log".equals(token));
            if (isOperator) {
                ComplexNumber operand1;
                ComplexNumber operand2;
                switch (token) {
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        operand1 = stack.pop();
                        operand2 = stack.pop();
                        stack.push(performOperation(token, operand1, operand2));
                        break;
                    case "sin":
                    case "cos":
                    case "log":
                        ComplexNumber operand = stack.pop();
                        stack.push(performComplexFunction(token, operand));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator");
                }
            } else {
                if (isComplexNumber(token)) {
                    ComplexNumber complex = parseComplex(token);
                    stack.push(complex);
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            }
        }

        ComplexNumber result = stack.pop();
        return result.toString();
    }

    /**
     * Checks if a given string represents a complex number.
     *
     * @param str The string to be checked.
     * @return True if the string represents a valid complex number, false otherwise.
     */
    private static boolean isComplexNumber(String str) {
        return str.matches("\\[?(-?\\d+(\\.\\d+)?),(-?\\d+(\\.\\d+)?)\\]?");
    }

    /**
     * Parses a string to create a ComplexNumber object.
     *
     * @param str The string representation of the complex
     *            number in the format "[real,imaginary]".
     * @return The ComplexNumber object parsed from the string.
     */
    private static ComplexNumber parseComplex(String str) {
        // Remove brackets if present
        str = str.replaceAll("[\\[\\]]", "");
        String[] parts = str.split(",");
        double real = Double.parseDouble(parts[0]);
        double imaginary = Double.parseDouble(parts[1]);
        return new ComplexNumber(real, imaginary);
    }


    /**
     * Performs basic arithmetic operations on complex numbers.
     *
     * @param operator The operator indicating the operation to perform.
     * @param operand1 The first complex operand.
     * @param operand2 The second complex operand.
     * @return The result of the arithmetic operation on the complex numbers.
     * @throws IllegalArgumentException If an unsupported operator is provided.
     */
    private ComplexNumber performOperation(String operator, ComplexNumber operand1,
                                           ComplexNumber operand2) {
        switch (operator) {
            case "+":
                return operand1.add(operand2);
            case "-":
                return operand1.subtract(operand2);
            case "*":
                return operand1.multiply(operand2);
            case "/":
                return operand1.divide(operand2);
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    /**
     * Performs trigonometric or logarithmic functions on complex numbers.
     *
     * @param function The function to be performed ('sin', 'cos', or 'log').
     * @param operand  The complex number operand for the function.
     * @return The result of the trigonometric or logarithmic function on the complex number.
     * @throws IllegalArgumentException If an unsupported function is provided.
     */
    private ComplexNumber performComplexFunction(String function, ComplexNumber operand) {
        switch (function) {
            case "sin":
                return operand.sin();
            case "cos":
                return operand.cos();
            case "log":
                return operand.log();
            default:
                throw new IllegalArgumentException("Invalid function");
        }
    }

}

package ru.nsu.yakovleva.calculator;

import java.lang.Math;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A simple calculator that evaluates expressions in prefix form.
 */
public class Main {

    /**
     * Reads an expression in prefix form from the user and calculates the result.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the expression in prefix form:");
        String input = scanner.nextLine();
        scanner.close();

        double result = calculate(input);
        System.out.println("Output: " + result);
    }

    /**
     * Evaluates a prefix expression and returns the result.
     *
     * @param expression The expression in prefix form to be evaluated
     * @return The result of the expression evaluation
     * @throws IllegalArgumentException If the expression is invalid
     * @throws ArithmeticException      If there is a division by zero
     */
    public static double calculate(String expression) {
        String[] tokens = expression.split("\\s+");
        Deque<Double> stack = new LinkedList<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                if (token.equals("+")) {
                    // Addition
                    double operand1 = stack.pop();
                    double operand2 = stack.pop();
                    stack.push(operand1 + operand2);
                } else if (token.equals("-")) {
                    // Subtraction
                    double operand1 = stack.pop();
                    double operand2 = stack.pop();
                    stack.push(operand1 - operand2);
                } else if (token.equals("*")) {
                    // Multiplication
                    double operand1 = stack.pop();
                    double operand2 = stack.pop();
                    stack.push(operand1 * operand2);
                } else if (token.equals("/")) {
                    // Division
                    double operand1 = stack.pop();
                    double operand2 = stack.pop();
                    if (operand2 == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    stack.push(operand1 / operand2);
                } else if (token.equals("sin")) {
                    // Sin function
                    double operand = stack.pop();
                    stack.push(Math.sin(operand));
                } else if (token.equals("cos")) {
                    // Cos function
                    double operand = stack.pop();
                    stack.push(Math.cos(operand));
                } else if (token.equals("sind")) {
                    // Sin function
                    double operand = stack.pop();
                    stack.push(Math.sin(Math.toRadians(operand)));
                } else if (token.equals("cosd")) {
                    // Cos function
                    double operand = stack.pop();
                    stack.push(Math.cos(Math.toRadians(operand)));
                } else if (token.equals("log")) {
                    // Logarithm function
                    double base = stack.pop();
                    double number = stack.pop();
                    if (number <= 0 || base <= 0 || base == 1 ) {
                        throw new ArithmeticException("Invalid arguments");
                    }
                    stack.push(Math.log(number) / Math.log(base));
                } else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return stack.pop();
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

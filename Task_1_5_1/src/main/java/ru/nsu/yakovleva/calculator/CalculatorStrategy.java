package ru.nsu.yakovleva.calculator;

/**
 * An interface that defines a strategy for calculating expressions based on a provided flag.
 */
public interface CalculatorStrategy {

    /**
     * Calculates an expression based on the provided flag.
     *
     * @param expression The expression to be calculated.
     * @param flag       The flag indicating the type of calculation or operation.
     * @return The result of the calculation as a string.
     */
    String calculate(String expression, Calculator.Flag flag);
}

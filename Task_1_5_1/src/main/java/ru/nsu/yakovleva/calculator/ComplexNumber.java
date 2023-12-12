package ru.nsu.yakovleva.calculator;

import java.util.Scanner;

/**
 * Represents a complex number and provides operations for arithmetic and trigonometric functions.
 */
class ComplexNumber {
    double real, imaginary;

    /**
     * Constructor for creating a complex number.
     *
     * @param real      The real part of the complex number.
     * @param imaginary The imaginary part of the complex number.
     */
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Adds two complex numbers.
     *
     * @param other The other complex number to add.
     * @return The sum of the current and other complex numbers.
     */
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    /**
     * Subtracts two complex numbers.
     *
     * @param other The complex number to subtract.
     * @return The result of subtracting the other complex number from the current one.
     */
    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }

    /**
     * Multiplies two complex numbers.
     *
     * @param other The other complex number to multiply with.
     * @return The product of the current and other complex numbers.
     */
    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Divides two complex numbers.
     *
     * @param other The complex number to divide by.
     * @return The result of dividing the current complex number by the other.
     */
    public ComplexNumber divide(ComplexNumber other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double newReal = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double newImaginary = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Calculates the sine of a complex number.
     *
     * @return The sine of the current complex number.
     */
    public ComplexNumber sin() {
        double newReal = Math.sin(this.real) * Math.cosh(this.imaginary);
        double newImaginary = Math.cos(this.real) * Math.sinh(this.imaginary);
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Calculates the cosine of a complex number.
     *
     * @return The cosine of the current complex number.
     */
    public ComplexNumber cos() {
        double newReal = Math.cos(this.real) * Math.cosh(this.imaginary);
        double newImaginary = -Math.sin(this.real) * Math.sinh(this.imaginary);
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Calculates the natural logarithm of a complex number.
     *
     * @return The natural logarithm of the current complex number.
     */
    public ComplexNumber log() {
        double magnitude = Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
        double newReal = Math.log(magnitude);
        double newImaginary = Math.atan2(this.imaginary, this.real);
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Overrides the toString() method to represent the complex number as a string.
     *
     * @return A string representation of the complex number.
     */
    @Override
    public String toString() {
        if (imaginary >= 0) {
            return real + " + " + imaginary + "i";
        } else {
            return real + " - " + (-imaginary) + "i";
        }
    }
}

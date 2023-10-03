package ru.nsu.yakovleva.polynomial;

import java.util.Arrays;

/**
 * Класс полиномов и методов для них.
 */
public class Polynomial {
    private int[] coefficients;

    /**
     * Метод для создания полинома.
     *
     * @param coefficients - коэффициенты полинома
     */
    public Polynomial(int[] coefficients) {
        this.coefficients = coefficients;
    }

    /**
     * Метод для вычисления полинома в заданной точке x.
     *
     * @param x - число для вычисления.
     * @return result - результат вычислений.
     */
    public int evaluate(int x) {
        int result = 0;
        int power = coefficients.length - 1;

        for (int coefficient : coefficients) {
            result += coefficient * (int) Math.pow(x,power);
            power--;
        }

        return result;
    }

    /**
     * Метод для получения коэффиентов полинома.
     *
     * @return coefficients - коэффициенты.
     */
    public int[] getCoefficients() {
        return coefficients;
    }

    /**
     * Метод для сравнения двух полиномов.
     *
     * @param obj - полином.
     * @return true/false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Polynomial other = (Polynomial) obj;
        return Arrays.equals(this.coefficients, other.coefficients);
    }

    /**
     * Метод для обращения полинома в читаемую строку.
     *
     * @return - строка.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int degree = coefficients.length - 1;

        for (int i = 0; i <= degree; i++) {
            int coefficient = coefficients[i];
            if (coefficient != 0) {
                if (!sb.isEmpty()) {
                    if (coefficient > 0) {
                        sb.append(" + ");
                    } else {
                        sb.append(" - ");
                    }
                }

                int absCoefficient = Math.abs(coefficient);
                sb.append(absCoefficient);


                if (i < degree) {
                    sb.append("x");
                    if (i < degree-1) {
                        sb.append("^").append(degree-i);
                    }
                }
            }
        }

        return sb.toString();
    }

    /**
     * Метод для сложения двух полиномов.
     *
     * @param other - второе слагаемое.
     * @return - результат сложения.
     */
    public Polynomial add(Polynomial other) {
        int maxLength = Math.max(coefficients.length, other.coefficients.length);
        int[] newCoefficients = new int[maxLength];

        for (int i = 0; i < maxLength; i++) {
            int coef1 = (i < coefficients.length) ? coefficients[i] : 0;
            int coef2 = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            newCoefficients[i] = coef1 + coef2;
        }

        return new Polynomial(newCoefficients);
    }

    /**
     * Метод для вычитания двух полиномов.
     *
     * @param other - вычитаемое.
     * @return - результат вычитания.
     */
    public Polynomial minus(Polynomial other) {
        int maxLength = Math.max(coefficients.length, other.coefficients.length);
        int[] newCoefficients = new int[maxLength];

        for (int i = 0; i < maxLength; i++) {
            int coef1 = (i < coefficients.length) ? coefficients[i] : 0;
            int coef2 = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            newCoefficients[i] = coef1 - coef2;
        }

        return new Polynomial(newCoefficients);
    }

    /**
     * Метод для умножения двух полиномов.
     *
     * @param other - второе умножаемое.
     * @return - результат умножения.
     */
    public Polynomial multiply(Polynomial other) {
        int[] newCoefficients = new int[coefficients.length + other.coefficients.length - 1];

        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                newCoefficients[i + j] += coefficients[i] * other.coefficients[j];
            }
        }

        return new Polynomial(newCoefficients);
    }

    /**
     * Дефолтный метод.
     *
     * @param args - дефолтные параметры main.
     */
    public static void main(String[] args) {}
}

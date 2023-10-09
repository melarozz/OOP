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
            result += coefficient * (int) Math.pow(x, power);
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
        StringBuilder res = new StringBuilder();

        int deg = this.coefficients.length;

        for (int i = 0; i < this.coefficients.length; i++) {
            if (this.coefficients[i] != 0) {
                if (deg != this.coefficients.length) {
                    if (this.coefficients[i] > 0) {
                        res.append(" + ");
                    } else {
                        res.append(" - ");
                    }
                }

                res.append(Math.abs(this.coefficients[i]));
                deg--;
                if (deg > 1) {
                    res.append("x^");
                    res.append(deg);
                } else if (deg == 1) {
                    res.append("x");
                }
            } else {
                deg--;
            }
        }

        return res.toString();
    }

    /**
     * Метод для сложения двух полиномов.
     *
     * @param other - второе слагаемое.
     * @return - результат сложения.
     */
    public Polynomial add(Polynomial other) {
        int[] resultcoefficients;

        if (this.coefficients.length < other.coefficients.length) {
            resultcoefficients = other.coefficients;
            for (int i = 0; i < this.coefficients.length; i++) {
                resultcoefficients[i] += this.coefficients[i];
            }
        } else {
            resultcoefficients = this.coefficients;
            for (int i = 0; i < other.coefficients.length; i++) {
                resultcoefficients[i] += other.coefficients[i];
            }
        }

        this.coefficients = resultcoefficients;

        return this;
    }

    /**
     * Метод для вычисления производной n ной полинома.
     *
     * @param n - n ная производная.
     * @return - n ная производная.
     */
    public Polynomial derivative(int n) {

        if (n <= 0) {
            return this;
        }

        if (this.coefficients.length <= 1) {
            int[] derivativeCoefficients = new int[1];
            derivativeCoefficients[0] = 0;
            return new Polynomial(derivativeCoefficients);
        }



        int[] derivativeCoefficients = new int[coefficients.length - 1];

        for (int i = 0; i < derivativeCoefficients.length; i++) {
            derivativeCoefficients[i] = coefficients[i] * (coefficients.length - 1 - i);
        }

        Polynomial derivativePolynomial = new Polynomial(derivativeCoefficients);

        return derivativePolynomial.derivative(n - 1);
    }


    /**
     * Метод для вычитания двух полиномов.
     *
     * @param other - вычитаемое.
     * @return - результат вычитания.
     */
    public Polynomial minus(Polynomial other) {
        int[] resultcoefficients;

        if (this.coefficients.length < other.coefficients.length) {
            resultcoefficients = other.coefficients;
            for (int i = 0; i < this.coefficients.length; i++) {
                resultcoefficients[i] -= this.coefficients[i];
            }
            
        } else {
            resultcoefficients = this.coefficients;
            for (int i = 0; i < other.coefficients.length; i++) {
                resultcoefficients[i] -= other.coefficients[i];
            }
        }

        this.coefficients = resultcoefficients;

        return this;
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

}

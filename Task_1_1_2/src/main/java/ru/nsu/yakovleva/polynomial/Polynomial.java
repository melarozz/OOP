package ru.nsu.yakovleva.polynomial;

import java.util.Arrays;

/**
 * Класс полиномов.
 */
public class Polynomial {

    /**
     * Коэффициенты полинома.
     */
    private int[] coef;

    /**
     * Инициализация полинома.
     * @param coef - коэффициенты полинома.
     */
    public Polynomial(int[] coef) {
        if (coef.length == 0 || coef[0] == 0) {
            throw new IllegalArgumentException("a n != 0");
        }
        this.coef = coef;
    }

    /**
     * Сумма двух полиномов.
     * @param other - второй полином
     * @return - возвращает итоговый полином
     */
    public Polynomial plus(Polynomial other) {
        int maxDegree = Math.max(this.degree(), other.degree());
        int[] resultcoef = new int[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            resultcoef[i] = this.getCoefficient(i) + other.getCoefficient(i);
        }

        return new Polynomial(resultcoef);
    }

    /**
     * Разность двух полиномов.
     * @param other - вычитаемое
     * @return - возвращает итоговый полином
     */
    public Polynomial minus(Polynomial other) {
        int maxDegree = Math.max(this.degree(), other.degree());
        int[] resultcoef = new int[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            resultcoef[i] = this.getCoefficient(i) - other.getCoefficient(i);
        }

        return new Polynomial(resultcoef);
    }

    /**
     * Умножение двух полиномов.
     * @param other - второе умножаемое
     * @return - возвращает итоговый полином
     */
    public Polynomial times(Polynomial other) {
        int resultDegree = this.degree() + other.degree();
        int[] resultcoef = new int[resultDegree + 1];

        for (int i = 0; i <= this.degree(); i++) {
            for (int j = 0; j <= other.degree(); j++) {
                resultcoef[i + j] += this.getCoefficient(i) * other.getCoefficient(j);
            }
        }

        return new Polynomial(resultcoef);
    }

    /**
     * Вычисление значения в точке.
     * @param x - точка для вычислений
     * @return - возвращает значение
     */
    public int evaluate(int x) {
        int result = 0;
        for (int i = 0; i <= degree(); i++) {
            result += coef[i] * Math.pow(x, i);
        }
        return result;
    }

    /**
     * Cравнение на равенство с другим многочленом.
     * @param other - другой полином
     * @return - возвращает правду или ложь
     */
    public boolean equals(Polynomial other) {
        return Arrays.equals(this.coef, other.coef);
    }

    /**
     * Степень полинома.
     * @return
     */
    public int degree() {
        return coef.length - 1;
    }

    /**
     * Получает коэффициенты.
     * @param degree - степень полинома
     * @return
     */

    public int getCoefficient(int degree) {
        if (degree < 0 || degree >= coef.length) {
            return 0;
        }
        return coef[(coef.length - 1) - degree];
    }

    public static void main(String[] args) {
    }
}
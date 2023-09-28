package ru.nsu.yakovleva.polynomial;

/**
 * Класс полинома с различными математическими функциями.
 *
 * @author Yakovleva-Valeria
 *
 * @version 2.0
 */
public class Polynomial {
    /**
     * Коэффициенты полинома.
     */
    public int[] coefs;

    /**
     * Степень полинома.
     */
    public int degree;

    /**
     * Конструктор для инициализации полинома с указанными коэффициентами.
     *
     * @param parameters - заданные коэффициенты полинома.
     */
    public Polynomial(int[] parameters) {
        if (parameters.length == 0 ||  parameters[0] == 0) {
            throw new IllegalArgumentException("The leading coefficient 'an' must be non-zero.");
        }
        this.coefs = reverseArray(parameters);
        this.degree = parameters.length;
    }


    /**
     * Метод вычисления значения полинома в указанной точке x.
     *
     * @param x - значение x.
     * @return - int.
     */
    public int evaluate(int x) {
        int res = 0;

        for (int i = 0; i < this.degree; i++) {
            res += (int) (this.coefs[this.degree - i - 1] * Math.pow(x, this.degree-i-1));

        }

        return res;
    }




    /**
     * Строковое представление полинома.
     *
     * @return - String.
     */
    @Override
    public String toString() {
        String res = "";

        int deg = this.degree;

        for (int i = this.degree - 1; i >= 0; i--) {
            if (this.coefs[i] != 0) {
                if (deg != this.degree) {
                    if (this.coefs[i] > 0) {
                        res += " + ";
                    }
                    else {
                        res += " - ";
                    }
                }

                res += (Integer.toString(Math.abs(this.coefs[i])));
                deg--;
                if (deg > 1) {
                    res += "x^";
                    res += (Integer.toString(deg));
                }
                else if (deg == 1) {
                    res += "x";
                }
            }
            else {
                deg--;
            }
        }

        return res;
    }

    /**
     * Метод для сложения двух полиномов.
     *
     * @param other - полином, второе слагаемое.
     * @return - объект Polynomial, первое слагаемое.
     */
    public Polynomial plus(Polynomial other) {
        int[] resultCoefs;

        if (this.degree < other.degree) {
            resultCoefs = other.coefs;
            for (int i = 0; i < this.degree; i++) {
                resultCoefs[i] += this.coefs[i];
            }
            this.degree = other.degree;
        } else {
            resultCoefs = this.coefs;
            for (int i = 0; i < other.degree; i++) {
                resultCoefs[i] += other.coefs[i];
            }
        }

        this.coefs = resultCoefs;

        return this;
    }

    /**
     * Метод для вычитания полиномов.
     *
     * @param other - полином, вычитаемое.
     * @return - объект Polynomial, уменьшаемое.
     */
    public Polynomial minus(Polynomial other) {
        int[] resultCoefs;

        if (this.degree < other.degree) {
            resultCoefs = other.coefs;
            for (int i = 0; i < this.degree; i++) {
                resultCoefs[i] -= this.coefs[i];
            }
            this.degree = other.degree;
        }
        else {
            resultCoefs = this.coefs;
            for (int i = 0; i < other.degree; i++) {
                resultCoefs[i] -= other.coefs[i];
            }
        }

        this.coefs = resultCoefs;

        return this;
    }

    /**
     * Метод для умножения полиномов.
     *
     * @param other - полином, второе умножаемое.
     * @return - объект Polynomial, первое умножаемое.
     */
    public Polynomial times(Polynomial other) {
        int[] resultCoefs = new int[this.degree + other.degree - 1];

        for (int i = 0; i < this.degree; i++) {
            for (int j = 0; j < other.degree; j++) {
                resultCoefs[i + j] += this.coefs[i] * other.coefs[j];
            }
        }

        this.coefs = resultCoefs;
        this.degree = this.degree * other.degree;
        return this;
    }

    /**
     * Метод для разворачивания массива.
     * @param arr - массив, который нужно перевернуть.
     * @return - перевернутый массив.
     */
    private static int[] reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
        return arr;
    }

    /**
     * Стандартная функция.
     *
     * @param args - стандартные параметры.
     */
    public static void main(String[] args) {
    }
}
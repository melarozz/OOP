package yakovleva;

import java.util.Arrays;
import java.util.Scanner;

public class Polynomial {
    private int[] coefficients;

    public Polynomial(int[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial plus(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        int[] result = new int[maxLength];

        for (int i = 0; i < maxLength; i++) {
            int thisCoeff = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            int otherCoeff = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            result[i] = thisCoeff + otherCoeff;
        }

        return new Polynomial(result);
    }

    public Polynomial minus(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        int[] result = new int[maxLength];

        for (int i = 0; i < maxLength; i++) {
            int thisCoeff = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            int otherCoeff = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            result[i] = thisCoeff - otherCoeff;
        }

        return new Polynomial(result);
    }

    public Polynomial times(Polynomial other) {
        int[] result = new int[this.coefficients.length + other.coefficients.length - 1];

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                result[i + j] += this.coefficients[i] * other.coefficients[j];
            }
        }

        return new Polynomial(result);
    }

    public int evaluate(int x) {
        int result = 0;
        int xPower = 1;

        for (int coeff : this.coefficients) {
            result += coeff * xPower;
            xPower *= x;
        }

        return result;
    }

    public Polynomial differentiate(int i) {
        int[] result = this.coefficients.clone();

        while (i > 0) {
            for (int j = 0; j < result.length - 1; j++) {
                result[j] = (j + 1) * result[j + 1];
            }
            result[result.length - 1] = 0;
            i--;
        }

        return new Polynomial(result);
    }

    public boolean equals(Polynomial other) {
        return Arrays.equals(this.coefficients, other.coefficients);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = this.coefficients.length - 1; i >= 0; i--) {
            if (this.coefficients[i] != 0) {
                if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (this.coefficients[i] != 1 || i == 0) {
                    sb.append(this.coefficients[i]);
                }
                if (i > 0) {
                    sb.append("x");
                    if (i > 1) {
                        sb.append("^").append(i);
                    }
                }
            }
        }
        return sb.toString();
    }


}
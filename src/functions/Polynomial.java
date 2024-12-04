package functions;

import java.util.Arrays;

public class Polynomial implements Function {

    double[] coefficients;

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < coefficients.length; i++) {
            s += coefficients[i] + "x^" + i + " + ";
        }
        return s;
    }

    @Override
    public double eval() {
        return 0;
    }

    @Override
    public Function derivative() {
        return null;
    }
}

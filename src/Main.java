import functions.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Main");

        Function sin = new Sin(new Polynomial(new double[] {1, 2}));
        Function cos = new Cos(new Polynomial(new double[] {3, 4}));

        Function add = new Sub(sin, cos);

        System.out.println(add);
        System.out.println(add.derivative());
    }

}
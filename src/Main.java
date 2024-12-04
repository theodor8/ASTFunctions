
import functions.*;


public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world :)");

        EvalVisitor ev = new EvalVisitor();
        DerivativeVisitor dv = new DerivativeVisitor();

        Function f1 = new Sin(new Mul(new Con(3), new Var()));
        System.out.println(f1.accept(dv).accept(ev));

    }

}
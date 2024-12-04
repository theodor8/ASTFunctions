package functions;

public class Cos extends UnaryFunction {

    public Cos(Function x) {
        super(x);
    }

    public String toString() {
        return "Cos(" + f.toString() + ")";
    }

    @Override
    public double eval() {
        return Math.cos(f.eval());
    }

    @Override
    public Function derivative() {
        return new Sin(f);
    }
}

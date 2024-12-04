package functions;

public class Sin extends UnaryFunction {

    public Sin(Function f) {
        super(f);
    }

    public String toString() {
        return "Sin(" + f.toString() + ")";
    }

    @Override
    public double eval() {
        return Math.sin(f.eval());
    }

    @Override
    public Function derivative() {
        return new Cos(f);
    }
}

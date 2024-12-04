package functions;

public class Add extends BinaryFunction {

    public Add(Function f1, Function f2) {
        super(f1, f2);
    }

    public String toString() {
        return "(" + f1.toString() + " + " + f2.toString() + ")";
    }

    @Override
    public double eval() {
        return f1.eval() + f2.eval();
    }

    @Override
    public Function derivative() {
        return new Add(f1.derivative(), f2.derivative());
    }
}

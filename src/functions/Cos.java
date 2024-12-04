package functions;

public class Cos extends Unary {

    public Cos(Function arg) {
        super(arg);
    }

    public String toString() {
        return "cos(" + arg.toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }
}

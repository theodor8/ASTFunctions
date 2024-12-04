package functions;

public class Mul extends Binary {

    public Mul(Function lhs, Function rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toString() {
        return "(" + lhs.toString() + " * " + rhs.toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}

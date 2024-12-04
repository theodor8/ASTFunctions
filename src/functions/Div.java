package functions;

public class Div extends Binary {

    public Div(Function lhs, Function rhs) {
        super(lhs, rhs);
    }

    public String toString() {
        return "(" + lhs.toString() + " / " + rhs.toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}

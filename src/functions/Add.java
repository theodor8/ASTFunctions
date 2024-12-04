package functions;

public class Add extends Binary {

    public Add(Function lhs, Function rhs) {
        super(lhs, rhs);
    }

    public String toString() {
        return "(" + lhs.toString() + " + " + rhs.toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}

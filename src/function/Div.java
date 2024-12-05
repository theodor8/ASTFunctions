package function;

import function.visitor.Visitor;

public class Div extends Binary {

    public Div(Function lhs, Function rhs) {
        super(lhs, rhs);
    }

    public String toString() {
        String lhsString = lhs.toString();
        String rhsString = rhs.toString();
        if (lhs instanceof Add || lhs instanceof Sub) {
            lhsString = "(" + lhsString + ")";
        }
        if (rhs instanceof Binary) {
            rhsString = "(" + rhsString + ")";
        }
        return lhsString + " / " + rhsString;
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}

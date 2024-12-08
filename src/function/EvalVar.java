package function;

import function.visitor.Visitor;

public class EvalVar extends Binary {

    public EvalVar(Function func, Function var) {
        super(func, var);
    }

    @Override
    public String toString() {
        String lhsString = getLhs().toString();
        String rhsString = getRhs().toString();
        if (getLhs() instanceof Binary) {
            lhsString = "(" + lhsString + ")";
        }
        if (getRhs() instanceof Binary) {
            rhsString = "(" + rhsString + ")";
        }
        return lhsString + " < " + rhsString;
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}

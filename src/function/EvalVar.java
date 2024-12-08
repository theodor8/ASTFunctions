package function;

import function.visitor.Visitor;

public class EvalVar extends Binary {

    public EvalVar(Function func, Function var) {
        super(func, var);
    }

    @Override
    public String toString() {
        return getLhs().toString() + " < " + getRhs().toString();
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}

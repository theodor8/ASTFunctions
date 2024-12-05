package function;

import function.visitor.Visitor;

public class Sub extends Binary {

    public Sub(Function f1, Function f2) {
        super(f1, f2);
    }

    public String toString() {
        return lhs.toString() + " - " + rhs.toString();
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }
}

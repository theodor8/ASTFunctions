package function;

import function.visitor.Visitor;

public class Add extends Binary {

    public Add(Function lhs, Function rhs) {
        super(lhs, rhs);
    }

    public String toString() {
        return getLhs().toString() + " + " + getRhs().toString();
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}

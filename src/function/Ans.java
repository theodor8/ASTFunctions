package function;

import function.visitor.Visitor;

public class Ans extends Function {

    @Override
    public String toString() {
        return "a";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ans;
    }

}

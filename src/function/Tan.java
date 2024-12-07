package function;

import function.visitor.Visitor;

public class Tan extends Unary {

    public Tan(Function f) {
        super(f);
    }

    @Override
    public String toString() {
        return "tan(" + this.getArg().toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}

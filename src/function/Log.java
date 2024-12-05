package function;

import function.visitor.Visitor;

public class Log extends Unary {

    public Log(Function arg) {
        super(arg);
    }

    @Override
    public String toString() {
        return "log(" + arg.toString() + ")";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

}

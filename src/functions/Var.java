package functions;

public class Var extends Function {

    @Override
    public String toString() {
        return "x";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Var;
    }

}

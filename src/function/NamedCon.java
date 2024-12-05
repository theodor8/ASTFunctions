package function;

import function.visitor.Visitor;

public class NamedCon extends Con {
    private String name;

    public NamedCon(String name, double value) {
        super(value);
        this.name = name;
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}

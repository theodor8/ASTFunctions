package function;

import function.visitor.Visitor;

public class Con extends Function {

    private double value;

    public Con(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Con c) {
            return equals(c);
        }
        return false;
    }

    public boolean equals(Con obj) {
        return this.value == obj.value;
    }


}

package functions;

public abstract class Unary extends Function {
    protected Function arg;

    public Unary(Function arg) {
        this.arg = arg;
    }

    public Function getArg() {
        return arg;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Unary u) {
            return equals(u);
        }
        return false;
    }

    public boolean equals(Unary obj) {
        return obj.getClass().equals(this.getClass()) && this.arg.equals(obj.arg);
    }

}

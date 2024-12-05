package function;

public abstract class Binary extends Function {
    protected Function lhs;
    protected Function rhs;

    public Binary(Function lhs, Function rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Function getLhs() {
        return lhs;
    }

    public Function getRhs() {
        return rhs;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Binary b) {
            return equals(b);
        }
        return false;
    }

    public boolean equals(Binary obj) {
        return obj.getClass().equals(this.getClass()) && this.lhs.equals(obj.lhs) && this.rhs.equals(obj.rhs);
    }

}

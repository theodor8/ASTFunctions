package functions;

public abstract class UnaryFunction implements Function {
    protected Function f;

    public UnaryFunction(Function f) {
        this.f = f;
    }

}

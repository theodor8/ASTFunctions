package functions;

public abstract class BinaryFunction implements Function {
    Function f1;
    Function f2;

    public BinaryFunction(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

}

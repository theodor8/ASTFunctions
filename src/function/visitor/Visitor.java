package function.visitor;

import function.*;

public interface Visitor {
    public Function visit(Add f);
    public Function visit(Sub f);
    public Function visit(Mul f);
    public Function visit(Div f);
    public Function visit(Sin f);
    public Function visit(Cos f);
    public Function visit(Tan f);
    public Function visit(Var f);
    public Function visit(Con f);
    public Function visit(NamedCon f);
    public Function visit(Neg f);
    public Function visit(Log f);
    public Function visit(Exp f);
    public Function visit(Derivative f);
    public Function visit(Integral f);
    public Function visit(Pow f);
    public Function visit(Ans f);
    public Function visit(EvalVar f);
}

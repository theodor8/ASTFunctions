package function.visitor;

import function.*;

public abstract class Visitor {
    public Function visit(Add f) {
        return new Add(f.getLhs().accept(this), f.getRhs().accept(this));
    }
    public Function visit(Sub f) {
        return new Sub(f.getLhs().accept(this), f.getRhs().accept(this));
    }
    public Function visit(Mul f) {
        return new Mul(f.getLhs().accept(this), f.getRhs().accept(this));
    }
    public Function visit(Div f) {
        return new Div(f.getLhs().accept(this), f.getRhs().accept(this));
    }
    public Function visit(Sin f) {
        return new Sin(f.getArg().accept(this));
    }
    public Function visit(Cos f) {
        return new Cos(f.getArg().accept(this));
    }
    public Function visit(Var f) {
        return new Var();
    }
    public Function visit(Con f) {
        return new Con(f.getValue());
    }
    public Function visit(NamedCon f) {
        return new NamedCon(f.getName(), f.getValue());
    }   
    public Function visit(Neg f) {
        return new Neg(f.getArg().accept(this));
    }
    public Function visit(Log f) {
        return new Log(f.getArg().accept(this));
    }
    public Function visit(Exp f) {
        return new Exp(f.getArg().accept(this));
    }
}

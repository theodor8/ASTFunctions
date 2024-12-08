package function.visitor;

import function.*;

public class DerivativeVisitor implements Visitor {


    @Override
    public Function visit(Sin f) {
        return new Mul(new Cos(f.getArg()), f.getArg().accept(this));
    }

    @Override
    public Function visit(Cos f) {
        return new Mul(new Neg(new Sin(f.getArg())), f.getArg().accept(this));
    }

    @Override
    public Function visit(Var f) {
        return new Con(1);
    }

    @Override
    public Function visit(Mul f) {
        return new Add(new Mul(f.getLhs().accept(this), f.getRhs()), new Mul(f.getLhs(), f.getRhs().accept(this)));
    }

    @Override
    public Function visit(Div f) {
        return new Div(new Sub(new Mul(f.getLhs().accept(this), f.getRhs()), new Mul(f.getLhs(), f.getRhs().accept(this))), new Mul(f.getRhs(), f.getRhs()));
    }

    @Override
    public Function visit(Con f) {
        return new Con(0);
    }

    @Override
    public Function visit(NamedCon f) {
        return new Con(0);
    }

    @Override
    public Function visit(Neg f) {
        return new Neg(f.getArg().accept(this));
    }

    @Override
    public Function visit(Exp f) {
        return new Mul(new Exp(f.getArg()), f.getArg().accept(this));
    }

    @Override
    public Function visit(Log f) {
        return new Div(f.getArg().accept(this), f.getArg());
    }

    @Override
    public Function visit(Pow f) {
        // TODO: derivative of pow
        throw new UnsupportedOperationException("Derivative of pow not implemented yet");
    }

    @Override
    public Function visit(Add f) {
        return new Add(f.getLhs().accept(this), f.getRhs().accept(this));
    }

    @Override
    public Function visit(Sub f) {
        return new Sub(f.getLhs().accept(this), f.getRhs().accept(this));
    }

    @Override
    public Function visit(Derivative f) {
        return new Derivative(f.getArg().accept(this));
    }

    @Override
    public Function visit(Tan f) {
        return new Div(f.getArg().accept(this), new Mul(new Cos(f.getArg()), new Cos(f.getArg())));
    }

    @Override
    public Function visit(Ans f) {
        return new Derivative(new Ans());
    }

    @Override
    public Function visit(EvalVar f) {
        return new Derivative(new EvalVar(f.getLhs(), f.getRhs()));
    }

}

package function.visitor;

import function.*;


public class IntegralVisitor implements Visitor {

    @Override
    public Function visit(Add f) {
        return new Add(f.getLhs().accept(this), f.getRhs().accept(this));
    }

    @Override
    public Function visit(Sub f) {
        return new Sub(f.getLhs().accept(this), f.getRhs().accept(this));
    }

    @Override
    public Function visit(Mul f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Function visit(Div f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Function visit(Sin f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Function visit(Cos f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Function visit(Tan f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Function visit(Var f) {
        return new Div(new Mul(new Var(), new Var()), new Con(2));
    }

    @Override
    public Function visit(Con f) {
        return new Mul(new Con(f.getValue()), new Var());
    }

    @Override
    public Function visit(NamedCon f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Function visit(Neg f) {
        return new Neg(f.getArg().accept(this));
    }

    @Override
    public Function visit(Log f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Function visit(Exp f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Function visit(Derivative f) {
        return f.getArg();
    }

    @Override
    public Function visit(Pow f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Function visit(Integral f) {
        return new Integral(f.getArg().accept(this));
    }

}

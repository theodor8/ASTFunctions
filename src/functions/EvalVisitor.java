package functions;

public class EvalVisitor extends Visitor {

    @Override
    public Function visit(Add f) {
        Function lhs = f.getLhs().accept(this);
        Function rhs = f.getRhs().accept(this);
        if (lhs instanceof Con cl) {
            if (cl.getValue() == 0) {
                return rhs;
            }
        }
        if (rhs instanceof Con cr) {
            if (cr.getValue() == 0) {
                return lhs;
            }
        }
        if (lhs instanceof Con cl && rhs instanceof Con cr) {
            return new Con(cl.getValue() + cr.getValue());
        }
        return new Add(lhs, rhs);
    }

    @Override
    public Function visit(Sub f) {
        Function lhs = f.getLhs().accept(this);
        Function rhs = f.getRhs().accept(this);
        if (lhs instanceof Con cl) {
            if (cl.getValue() == 0) {
                return new Neg(rhs);
            }
        }
        if (rhs instanceof Con cr) {
            if (cr.getValue() == 0) {
                return lhs;
            }
        }
        if (lhs instanceof Con cl && rhs instanceof Con cr) {
            return new Con(cl.getValue() - cr.getValue());
        }
        return new Sub(lhs, rhs);
    }

    @Override
    public Function visit(Div f) {
        Function lhs = f.getLhs().accept(this);
        Function rhs = f.getRhs().accept(this);
        if (lhs instanceof Con cl && rhs instanceof Con cr) {
            return new Con(cl.getValue() / cr.getValue());
        }
        return new Div(lhs, rhs);
    }

    @Override
    public Function visit(Mul f) {
        Function lhs = f.getLhs().accept(this);
        Function rhs = f.getRhs().accept(this);
        if (lhs instanceof Con cl) {
            if (cl.getValue() == 0) {
                return new Con(0);
            }
            if (cl.getValue() == 1) {
                return rhs;
            }
            if (cl.getValue() == -1) {
                return new Neg(rhs);
            }
        }
        if (rhs instanceof Con cr) {
            if (cr.getValue() == 0) {
                return new Con(0);
            }
            if (cr.getValue() == 1) {
                return lhs;
            }
            if (cr.getValue() == -1) {
                return new Neg(lhs);
            }
        }
        if (lhs instanceof Con cl && rhs instanceof Con cr) {
            return new Con(cl.getValue() * cr.getValue());
        }
        return new Mul(lhs, rhs);
    }

    @Override
    public Function visit(Neg f) {
        Function arg = f.getArg().accept(this);

        if (arg instanceof Neg n) {
            return n.getArg();
        }
        if (arg instanceof Con c) {
            return new Con(-c.getValue());
        }

        return new Neg(arg); 

    }

}

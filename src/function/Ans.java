package function;

import function.visitor.Visitor;

public class Ans extends Function {

    private Integer id;

    // latest id
    public Ans() {
    }

    // specific id
    public Ans(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        if (id != null) {
            return "a" + id;
        }
        return "a";
    }

    @Override
    public Function accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ans;
    }

}

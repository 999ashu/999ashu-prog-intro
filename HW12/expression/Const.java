package expression;

import java.util.Objects;

public class Const implements AnyExpression {
    private final int constant;

    public Const(int constant) {
        this.constant = constant;
    }

    public int evaluate(int x) {
        return constant;
    }

    public int evaluate(int x, int y, int z) {
        return constant;
    }

    public String toString() {
        return ("" + constant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return constant == aConst.constant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(constant);
    }
}

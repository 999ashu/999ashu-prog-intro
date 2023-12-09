package expression;

import java.util.Objects;

public class Inversion implements AnyExpression {
    AnyExpression arg;

    public Inversion(AnyExpression arg) {
        this.arg = arg;
    }

    @Override
    public int evaluate(int x) {
        return -arg.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -arg.evaluate(x, y, z);
    }

    @Override
    public int compute(int v1, int v2) {
        return 0;
    }

    @Override
    public String toString() {
        return ("-(" + arg + ")");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inversion inversion = (Inversion) o;
        return Objects.equals(arg, inversion.arg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arg);
    }
}

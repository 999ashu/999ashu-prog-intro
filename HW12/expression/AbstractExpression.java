package expression;

import java.util.Objects;

public abstract class AbstractExpression implements AnyExpression {
    protected final AnyExpression v1;
    protected final AnyExpression v2;
    protected final String operation;

    public AbstractExpression(AnyExpression v1, AnyExpression v2, String operation) {
        this.v1 = v1;
        this.v2 = v2;
        this.operation = operation;
    }

    @Override
    public int evaluate(int x) {
        return compute(v1.evaluate(x), v2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return compute(v1.evaluate(x, y, z), v2.evaluate(x, y, z));
    }

    public String toString() {
        return ("(" + v1.toString() + " " + operation + " " + v2.toString() + ")");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractExpression that = (AbstractExpression) o;
        return Objects.equals(operation, that.operation) && Objects.equals(v1, that.v1) && Objects.equals(v2, that.v2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2, operation);
    }
}

package expression;

import java.util.List;
import java.util.Objects;

public abstract class AbstractUnaryOperation implements CustomExpression {
    private final CustomExpression value;

    public AbstractUnaryOperation(CustomExpression value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return compute(value.evaluate(x));
    }

    protected abstract int compute(int value);

    @Override
    public int evaluate(int x, int y, int z) {
        return compute(value.evaluate(x, y, z));
    }

    protected abstract String getOperation();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringBuilder(sb);
        return sb.toString();
    }

    public void toStringBuilder(StringBuilder sb) {
        sb.append(getOperation()).append("(");
        value.toStringBuilder(sb);
        sb.append(")");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUnaryOperation that = (AbstractUnaryOperation) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, getOperation());
    }
}
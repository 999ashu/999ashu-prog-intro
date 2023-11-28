package expression;

import java.util.Objects;

public abstract class AbstractExpression implements Expression {
    protected final Expression v1;
    protected final Expression v2;
    protected final String operation;

    public AbstractExpression(Expression v1, Expression v2, String operation) {
        this.v1 = v1;
        this.v2 = v2;
        this.operation = operation;
    }

    public int evaluate(int x) {
        return switch (operation) {
            case "+" -> (v1.evaluate(x) + v2.evaluate(x));
            case "-" -> (v1.evaluate(x) - v2.evaluate(x));
            case "*" -> (v1.evaluate(x) * v2.evaluate(x));
            case "/" -> (v1.evaluate(x) / v2.evaluate(x));
            default -> 0;
        };
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

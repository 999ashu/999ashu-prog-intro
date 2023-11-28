package expression;

public abstract class AbstractExpression implements Expression {
    protected final Expression v1;
    protected final Expression v2;
    protected final String operation;

    public AbstractExpression(Expression v1, Expression v2, String operation) {
        this.v1 = v1;
        this.v2 = v2;
        this.operation = operation;
    }

    public String toString() {
        return ("(" + v1.toString() + operation + v2.toString() + ")");
    }

    public boolean equals(AbstractExpression expression) {
        return toString().equals(expression.toString());
    }
}

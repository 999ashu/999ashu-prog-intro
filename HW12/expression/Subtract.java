package expression;

public class Subtract extends AbstractExpression {
    public Subtract(Expression v1, Expression v2) {
        super(v1, v2, "-");
    }
}

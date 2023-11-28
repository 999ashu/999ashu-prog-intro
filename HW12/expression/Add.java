package expression;

public class Add extends AbstractExpression {
    public Add(Expression v1, Expression v2) {
        super(v1, v2, "+");
    }
}

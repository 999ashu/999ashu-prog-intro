package expression;

public class Divide extends AbstractExpression {
    public Divide(Expression v1, Expression v2) {
        super(v1, v2, " / ");
    }

    @Override
    public int evaluate(int x) {
        return (v1.evaluate(x) / v2.evaluate(x));
    }
}

package expression;

public class Multiply extends AbstractExpression {
    public Multiply(AnyExpression v1, AnyExpression v2) {
        super(v1, v2);
    }

    @Override
    protected String getOperation() {
        return "*";
    }

    public int compute(int v1, int v2) {
        return v1 * v2;
    }
}

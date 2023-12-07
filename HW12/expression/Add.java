package expression;

public class Add extends AbstractExpression {
    public Add(AnyExpression v1, AnyExpression v2) {
        super(v1, v2);
    }

    public int compute(int v1, int v2) {
        return v1 + v2;
    }

    @Override
    protected String getOperation() {
        return "+";
    }
}

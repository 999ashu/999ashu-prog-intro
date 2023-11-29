package expression;

public class Divide extends AbstractExpression {
    public Divide(AnyExpression v1, AnyExpression v2) {
        super(v1, v2, "/");
    }

    public int compute(int v1, int v2) {
        return v1 / v2;
    }
}

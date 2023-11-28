package expression;

public class Divide extends AbstractExpression {
    public Divide(AnyExpression v1, AnyExpression v2) {
        super(v1, v2, "/");
    }
}

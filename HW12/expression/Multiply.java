package expression;

public class Multiply extends AbstractExpression {
    public Multiply(AnyExpression v1, AnyExpression v2) {
        super(v1, v2, "*");
    }
}

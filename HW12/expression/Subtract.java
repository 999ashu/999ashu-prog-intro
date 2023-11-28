package expression;

public class Subtract extends AbstractExpression {
    public Subtract(AnyExpression v1, AnyExpression v2) {
        super(v1, v2, "-");
    }
}

package expression;

public class Add extends AbstractExpression {
    public Add(AnyExpression v1, AnyExpression v2) {
        super(v1, v2, "+");
    }
}

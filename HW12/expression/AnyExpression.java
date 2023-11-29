package expression;

public interface AnyExpression extends Expression, TripleExpression {
    int compute(int v1, int v2);
}

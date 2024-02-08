package expression;

public interface CustomExpression extends Expression, TripleExpression {
    void toStringBuilder(StringBuilder sb);
}

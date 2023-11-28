package expression;

public class Variable extends AbstractOperand {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean equals(Expression expression) {
        return name.equals(expression.toString());
    }
}

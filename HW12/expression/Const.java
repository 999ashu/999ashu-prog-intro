package expression;

public class Const extends AbstractOperand {
    private final int constant;

    public Const(int constant) {
        this.constant = constant;
    }

    @Override
    public int evaluate(int x) {
        return constant;
    }

    @Override
    public String toString() {
        return ("" + constant);
    }

    public boolean equals(Expression expression) {
        return ("" + constant).equals(expression.toString());
    }
}

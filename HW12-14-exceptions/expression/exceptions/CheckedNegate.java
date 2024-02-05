package expression.exceptions;

import expression.CustomExpression;

public class CheckedNegate extends AbstractUnaryOperation {
    public CheckedNegate(CustomExpression value) {
        super(value);
    }

    @Override
    protected int compute(int value) {
        return -value;
    }

    @Override
    protected String getOperation() {
        return "-";
    }
}
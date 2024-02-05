package expression.exceptions;

import expression.CustomExpression;

public class ExpressionParser implements TripleParser {
    public CustomExpression parse(String expression) {
        return new Parser(new StringSource(expression)).parseExpression();
    }

    private static final class Parser extends BaseParser {
        private Parser(CharSource source) {
            super(source);
        }

        private CustomExpression parseExpression() {
            CustomExpression argument = parseTerm();
            while (true) {
                if (test('+') || test('-')) {
                    argument = makeExpression(String.valueOf(take()), argument, parseTerm());
                } else {
                    return argument;
                }
            }
        }

        private CustomExpression parseTerm() {
            CustomExpression argument = parseAtom();
            while (true) {
                if (test('*') || test('/')) {
                    argument = makeExpression(String.valueOf(take()), argument, parseAtom());
                } else {
                    return argument;
                }
            }
        }

        private CustomExpression parseAtom() {
            if (take('(')) {
                CustomExpression argument = parseExpression();
                expect(')');
                return argument;
            } else if (between('x', 'z')) {
                return new Variable(String.valueOf(take()));
            } else if (take('-')) {
                if (between('0', '9')) {
                    return new Const(getNumber(true));
                } else {
                    return new CheckedNegate(parseAtom());
                }
            } else {
                return new Const(getNumber(false));
            }
        }

        private int getNumber(boolean negative) {
            StringBuilder number = negative ? new StringBuilder().append('-') : new StringBuilder();
            do {
                number.append(take());
            } while (between('0', '9'));
            return Integer.parseInt(number.toString());
        }

        private CustomExpression makeExpression(String operator, CustomExpression argument1, CustomExpression argument2) {
            return switch (operator) {
                case "+" -> new CheckedAdd(argument1, argument2);
                case "-" -> new CheckedSubtract(argument1, argument2);
                case "*" -> new CheckedMultiply(argument1, argument2);
                case "/" -> new CheckedDivide(argument1, argument2);
                default -> throw error("Unknown operator: " + operator);
            };
        }
    }
}

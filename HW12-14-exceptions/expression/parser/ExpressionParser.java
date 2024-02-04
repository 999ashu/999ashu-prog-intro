package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {
    public TripleExpression parse(String expression) {
        System.err.println(expression);
        return new Parser(new StringSource(expression)).parseExpression();
    }

    private static final class Parser extends BaseParser {
        private Parser(CharSource source) {
            super(source);
        }

        private CustomExpression parseExpression() {
            return parseE();
        }

        private CustomExpression parseE() {
            skipWhitespace();
            CustomExpression argument = parseT();
            skipWhitespace();
            if (test('+') || test('-')) {
                return makeExpression(String.valueOf(take()), argument, parseE());
            }
            return argument;
        }

        private CustomExpression parseT() {
            skipWhitespace();
            CustomExpression argument = parseF();
            skipWhitespace();
            if (test('*') || test('/')) {
                return makeExpression(String.valueOf(take()), argument, parseT());
            }
            return argument;
        }

        private CustomExpression parseF() {
            if (take('(')) {
                CustomExpression argument = parseE();
                expect(')');
                return argument;
            } else if (between('x', 'z')) {
                return new Variable(String.valueOf(take()));
            } else if (between('0', '9')) {
                return new Const(getNumber(false));
            } else if (take('-')) {
                return new Const(getNumber(true));
            } else {
                return new Negate(parseF());
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
                case "+" -> new Add(argument1, argument2);
                case "-" -> new Subtract(argument1, argument2);
                case "*" -> new Multiply(argument1, argument2);
                case "/" -> new Divide(argument1, argument2);
                default -> throw error("Unknown operator: " + operator);
            };
        }
    }
}

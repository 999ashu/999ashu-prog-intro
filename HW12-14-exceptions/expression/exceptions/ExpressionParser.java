package expression.exceptions;

import expression.CustomExpression;
import expression.exceptions.parsingExceptions.*;

public class ExpressionParser implements TripleParser {
    public CustomExpression parse(String expression) {
        return new Parser(new StringSource(expression)).checkParsing();
    }

    private static final class Parser extends BaseParser {
        int arguments = 1;

        private Parser(CharSource source) {
            super(source);
        }

        private CustomExpression checkParsing() {
            CustomExpression result = parseExpression();
            if (eof()) {
                return result;
            }
            if (take(')')) {
                throw new IncorrectBracketSequenceException("opening");
            }
            throw new InvalidExpStructureException();
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
                if (!test(')')) {
                    throw new IncorrectBracketSequenceException("closing");
                } else {
                    take();
                }
                arguments++;
                return argument;
            } else if (between('x', 'z')) {
                arguments++;
                return new Variable(String.valueOf(take()));
            } else if (take('-')) {
                if (between('0', '9')) {
                    arguments++;
                    return new Const(getNumber(true));
                } else {
                    return new CheckedNegate(parseAtom());
                }
            } else if (between('0', '9')) {
                arguments++;
                return new Const(getNumber(false));
            } else if (eof() || take('*') || take('/') ||
                    take('+') || take('-')) {
                if (arguments == 1 && eof()) {
                    throw new InvalidArgumentException("any");
                } else {
                    throw new InvalidArgumentException(String.valueOf(arguments));
                }
            } else if (test(')')) {
                throw new EmptyParenthesisException();
            } else {
                throw new UnexpectedSymbolException(String.valueOf(take()));
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
                default -> throw new UnexpectedSymbolException(operator);
            };
        }
    }
}
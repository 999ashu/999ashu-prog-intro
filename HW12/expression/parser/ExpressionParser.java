package expression.parser;

import expression.*;

import java.util.*;

public class ExpressionParser implements TripleParser {
    Map<Character, Integer> priority = new HashMap<>(Map.of(
            '~', 1,
            '*', 2,
            '/', 2,
            '-', 3,
            '+', 3,
            '(', 4
    ));

    @Override
    public AnyExpression parse(String expression) {
        Stack<Character> operators = new Stack<>();
        LinkedList<AnyExpression> exp = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            while (i < expression.length() - 1 && Character.isWhitespace(ch)) {
                ch = expression.charAt(++i);
            }
            if (ch == '-' && Character.isDigit(expression.charAt(i + 1))) {
                String num = getInt(expression, i, 1);
                exp.push(new Const((Integer.parseInt(num))));
                i += num.length() - 1;
            } else if (Character.isDigit(ch)) {
                String num = getInt(expression, i, 0);
                exp.push(new Const(Integer.parseInt(num)));
                i += num.length() - 1;
            } else if (ch == 'x' || ch == 'y' || ch == 'z') {
                exp.push(new Variable("" + ch));
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    exp.push(getOperation(operators.pop(), exp));
                }
                operators.pop();
            } else if (priority.containsKey(ch)) {
                if (ch == '-' && (exp.isEmpty() || i > 1 && !operators.isEmpty() && operators.peek() == '-')) {
                    ch = '~';
                }
                while (!operators.isEmpty() && priority.get(ch) > priority.get(operators.peek())) {
                    exp.push(getOperation(operators.pop(), exp));
                }
                operators.push(ch);
            }
        }
        while (!operators.isEmpty()) {
            exp.push(getOperation(operators.pop(), exp));
        }
        return exp.pop();
    }

    private AnyExpression getOperation(char op, Stack<AnyExpression> exp) {
        AnyExpression arg = exp.pop();
        return switch (op) {
            case '*' -> new Multiply(exp.pop(), arg);
            case '/' -> new Divide(exp.pop(), arg);
            case '+' -> new Add(exp.pop(), arg);
            case '-' -> new Subtract(exp.pop(), arg);
            default -> new Inversion(arg);
        };
    }

    private String getInt(String str, int start, int negative) {
        for (int i = start + negative; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return str.substring(start, i);
            }
        }
        return str.substring(start);
    }
}

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
        Stack<String> rpn = getRPN(expression);
        System.err.println(expression + " " + rpn);
        AnyExpression ans = toExpression(rpn);
        System.err.println(ans);
        return ans;
    }

    public Stack<String> getRPN(String expression) {
        Stack<Character> operators = new Stack<>();
        Stack<String> rpn = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            while (i < expression.length() - 1 && Character.isWhitespace(ch)) {
                ch = expression.charAt(++i);
            }
            if (ch == '-' && Character.isDigit(expression.charAt(i + 1))) {
                rpn.push(getInt(expression, i, 1));
                i += rpn.peek().length() - 1;
            } else if (Character.isDigit(ch)) {
                rpn.push(getInt(expression, i, 0));
                i += rpn.peek().length() - 1;
            } else if (ch == 'x' || ch == 'y' || ch == 'z') {
                rpn.push("" + ch);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    rpn.push("" + operators.pop());
                }
                operators.pop();
            } else if (priority.containsKey(ch)) {
                if (ch == '-' && (rpn.isEmpty() || i > 1 && !operators.isEmpty() && operators.peek() == '-')) {
                    ch = '~';
                }
                while (!operators.isEmpty() && priority.get(ch) > priority.get(operators.peek())) {
                    rpn.push("" + operators.pop());
                }
                operators.push(ch);
            }
        }
        while (!operators.isEmpty()) {
            rpn.push("" + operators.pop());
        }
        return rpn;
    }

    private AnyExpression toExpression(Stack<String> rpn) {
        while (!rpn.isEmpty()) {
            String element = rpn.pop();
            char ch = element.charAt(0);
            if (Character.isDigit(ch) || (element.length() > 1 && ch == '-' && Character.isDigit(element.charAt(1)))) {
                return new Const(Integer.parseInt(element));
            } else if (ch == 'x' || ch == 'y' || ch == 'z') {
                return new Variable(element);
            } else if (ch == '~') {
                return new Inversion(toExpression(rpn));
            } else if (ch == '*') {
                AnyExpression arg2 = toExpression(rpn);
                return new Multiply(toExpression(rpn), arg2);
            } else if (ch == '/') {
                AnyExpression arg2 = toExpression(rpn);
                return new Divide(toExpression(rpn), arg2);
            } else if (ch == '+') {
                AnyExpression arg2 = toExpression(rpn);
                return new Add(toExpression(rpn), arg2);
            } else if (ch == '-') {
                AnyExpression arg2 = toExpression(rpn);
                return new Subtract(toExpression(rpn), arg2);
            }
        }
        return new Variable("");
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

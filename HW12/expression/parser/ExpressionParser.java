package expression.parser;

import expression.*;

import java.util.*;

public class ExpressionParser implements TripleParser {
    Map<Character, Integer> priority = new HashMap<>(Map.of(
            '~', 1,
            '*', 2,
            '/', 2,
            '+', 3,
            '-', 3,
            '(', 4
    ));
    String arg1, arg2;

    @Override
    public AnyExpression parse(String expression) {
        Stack<String> rpn = getRPN(expression);

        AnyExpression ans = toExpression(rpn, rpn.pop());
        System.err.println(ans);
        return ans;
    }

    public Stack<String> getRPN(String expression) {
        Stack<Character> operators = new Stack<>();
        Stack<String> rpn = new Stack<>();
        int j = 0;
        while (Character.isWhitespace(expression.charAt(j))) {
            j++;
        }
        expression = expression.substring(j);
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                rpn.push(getInt(expression, i));
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
                if (ch == '-' && (i == 0 || i > 1 && !operators.isEmpty() && operators.peek() == '-')) {
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

    private AnyExpression toExpression(Stack<String> rpn, String obj) {
        if (obj.equals("x") || obj.equals("y") || obj.equals("z")) {
            return new Variable(obj);
        } else {
            switch (obj) {
                case "~" -> {
                    return new Const(-Integer.parseInt(rpn.pop()));
                }
                case "+" -> {
                    getArgs(rpn);
                    return new Add(toExpression(rpn, arg1), toExpression(rpn, arg2));
                }
                case "-" -> {
                    getArgs(rpn);
                    return new Subtract(toExpression(rpn, arg1), toExpression(rpn, arg2));
                }
                case "*" -> {
                    getArgs(rpn);
                    return new Multiply(toExpression(rpn, arg1), toExpression(rpn, arg2));
                }
                case "/" -> {
                    getArgs(rpn);
                    return new Divide(toExpression(rpn, arg1), toExpression(rpn, arg2));
                }
                default -> {
                    return new Const(Integer.parseInt(obj));
                }
            }
        }
    }

    private void getArgs(Stack<String> stack) {
        arg2 = stack.pop();
        if (arg2.equals("~")) {
            arg2 = "-" + stack.pop();
        }
        arg1 = stack.pop();
        if (arg1.equals("~")) {
            arg1 = "-" + stack.pop();
        }
    }

    private String getInt(String str, int start) {
        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return str.substring(start, i);
            }
        }
        return str.substring(start);
    }
}

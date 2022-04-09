package ch07;

import containers.LinkedStack;
import containers.Stack;

import java.util.StringTokenizer;

public class Postfix {
    public static int evalPostfix(String s) {
        StringTokenizer st = new StringTokenizer(s);

        return evalExpressionStart(st);
    }

    private static int evalExpressionStart(StringTokenizer st) {
        if ( !st.hasMoreTokens() ) {
            throw new IllegalStateException("Missing argument");
        } else {
            String token = st.nextToken();

            try {
                return evalExpression1(Integer.parseInt(token), st);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Malformed postfix expression: " + token);
            }
        }
    }

    private static int evalExpression1(int op1, StringTokenizer st) {
        if ( !st.hasMoreTokens() ) {
            return op1;
        } else {
            String token = st.nextToken();

            try {
                return evalExpression1(evalExpression2(op1, Integer.parseInt(token), st), st);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Malformed postfix expression: " + token);
            }
        }
    }

    private static int evalExpression2(int op1, int op2, StringTokenizer st) {
        if ( !st.hasMoreTokens() ) {
            throw new IllegalStateException("Missing argument");
        } else {
            String token = st.nextToken();

            if ( Evaluator.isOperator(token) ) {
                return Evaluator.evaluate(token, op1, op2);
            } else {
                try {
                    return evalExpression2(op1, evalExpression2(op2, Integer.parseInt(token), st), st);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Malformed postfix expression: " + token);
                }
            }
        }
    }

    public static int stackEvalPostfix(String s) {
        StringTokenizer st = new StringTokenizer(s); // Legacy class???
        Stack<Integer> stack = new LinkedStack<>();

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (Evaluator.isOperator(token)) {
                if (stack.isEmpty()) {
                    throw new IllegalStateException("Missing argument");
                } else {
                    int right = stack.pop();

                    if (stack.isEmpty()) {
                        throw new IllegalStateException("Missing argument");
                    } else {
                        int left = stack.pop();
                        stack.push(Evaluator.evaluate(token, left, right));
                    }
                }
            } else {
                try {
                    stack.push(Integer.parseInt(token));
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Invalid number: " + token);
                }
            }
        }

        if (stack.isEmpty()) {
            throw new IllegalStateException("Missing expression");
        } else {
            int result = stack.pop();

            if (!stack.isEmpty()) {
                throw new IllegalStateException("Too many arguments");
            } else {
                return result;
            }
        }
    }
}


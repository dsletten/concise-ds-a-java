package ch07;

import containers.LinkedStack;
import containers.Stack;

import java.util.StringTokenizer;

public class Prefix {
    private static final String MARKER = "v";

    public static int evalPrefix(String s) {
        StringTokenizer st = new StringTokenizer(s); // Legacy class???

        return evaluateExpression(st);
    }

    private static int evaluateExpression(StringTokenizer st) {
        if ( !st.hasMoreElements() ) {
            throw new IllegalStateException("Missing argument");
        } else {
            String token = st.nextToken();

            if ( Evaluator.isOperator(token) ) {
                return Evaluator.evaluate(token, evaluateExpression(st), evaluateExpression(st));
            } else {
                try {
                    return Integer.parseInt(token);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Invalid number: " + token);
                }
            }
        }
    }

    public static int stackEvalPrefix(String s) {
        StringTokenizer st = new StringTokenizer(s); // Legacy class???

        if ( !st.hasMoreTokens() ) {
            throw new IllegalStateException("Missing expression");
        } else {
            Stack<String> operatorStack = new LinkedStack<>();
            Stack<Integer> operandStack = new LinkedStack<>();

            while ( st.hasMoreTokens() ) {
                String token = st.nextToken();

                if ( Evaluator.isOperator(token) ) {
                    operatorStack.push(token);
                } else {
                    try {
                        process(Integer.parseInt(token), operatorStack, operandStack);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Invalid number: " + token);
                    }
                }
            }

            if ( operatorStack.isEmpty() ) {
                throw new IllegalStateException("Missing argument");
            } else if ( !isMarked(operatorStack) ) {
                throw new IllegalStateException("Illegal state");
            } else {
                int result = operandStack.pop();
                operatorStack.pop();

                if ( !operandStack.isEmpty() ) {
                    throw new IllegalStateException("Too many arguments");
                } else if ( !operatorStack.isEmpty() ) {
                    throw new IllegalStateException("Missing argument");
                } else {
                    return result;
                }
            }
        }
    }

    private static void process(Integer operand, Stack<String> operatorStack, Stack<Integer> operandStack) {
        if ( !isMarked(operatorStack) ) {
            processLeftOperand(operand, operatorStack, operandStack);
        } else {
            processRightOperand(operand, operatorStack, operandStack);
        }
    }

    private static void processLeftOperand(Integer op, Stack<String> operatorStack, Stack<Integer> operandStack) {
        mark(operatorStack);
        operandStack.push(op);
    }

    private static void processRightOperand(Integer op, Stack<String> operatorStack, Stack<Integer> operandStack) {
        operatorStack.pop();

        if ( operatorStack.isEmpty() ) {
            throw new IllegalStateException("Missing operator");
        } else {
            process(Evaluator.evaluate(operatorStack.pop(), operandStack.pop(), op), operatorStack, operandStack);
        }
    }

    private static void mark(Stack<String> stack) {
        stack.push(MARKER);
    }

    private static boolean isMarked(Stack<String> stack) {
        return !stack.isEmpty()  &&  stack.peek() == MARKER;
    }
}

package ch07;

import containers.LinkedStack;
import containers.Stack;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Infix {
    public static List<String> tokenize(String s) {
        List<String> tokens = new ArrayList<>();
//        PushbackReader stream = new PushbackReader(new StringReader(s));
//        String token;
//
//        while ((token = readToken(stream)) != null) {
//            tokens.add(token);
//        }

        Pattern p = Pattern.compile("(\\-?\\d+\\.?\\d*|[-+*\\/%()])"); // Numbers are assumed to be integers elsewhere!
        Matcher m = p.matcher(s);

        while ( m.find() ) {
            tokens.add(m.group(0));
        }

        return tokens;
    }

//    private static void skipWhitespace(PushbackReader stream) {
//        int n;
//
//        try {
//            while ((n = stream.read()) > 0) {
//                if (n != ' ') {
//                    stream.unread(n);
//                    return;
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Exception scanning for whitespace: " + e.getMessage());
//        }
//    }
//
//    private static String readToken(PushbackReader stream) {
//        skipWhitespace(stream);
//
//        try {
//            int n = stream.read();
//
//            if ( n < 0 ) {
//                return null;
//            } else {
//                switch ( n ) {
//                    case '(':
//                        return "(";
//                    case ')':
//                        return ")";
//                    default:
//                        stream.unread(n);
//                        stream.
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Exception reading token: " + e.getMessage());
//        }
//    }

    //
    //    Sedgewick Algorithms 4e pg. 129
    //    Must be fully parenthesized.
    //
    public static int stackEvalInfix(String s) {
        List<String> tokens = tokenize(s);
        Stack<String> operatorStack = new LinkedStack<>();
        Stack<Integer> operandStack = new LinkedStack<>();

        for (String token : tokens) {
            switch ( token ) {
                case "(":
                    break;
                case ")":
                    String op = operatorStack.pop();
                    Integer op2 = operandStack.pop();
                    Integer op1 = operandStack.pop();
                    operandStack.push(Evaluator.evaluate(op, op1, op2));
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                    operatorStack.push(token);
                    break;
                default:
                    try {
                        operandStack.push(Integer.parseInt(token));
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Invalid number: " + token);
                    }
            }
        }

        return operandStack.pop();
    }

    //
    //    Art of Java - recursive descent parser. Relaxes requirement for parenthesization.
    //    Precedence hard-wired into structure of recursive function calls.
    //
    public static int evalInfix(String s) {
        List<String> tokens = tokenize(s);

        int result = evalExpression(tokens);

        if ( !tokens.isEmpty() ) {
            throw new IllegalStateException("Malformed expression. Remaining tokens: " + tokens);
        } else {
            return result;
        }
    }

    private static int evalExpression(List<String> tokens) {
        if ( tokens.isEmpty() ) {
            throw new IllegalStateException("Missing expression");
        } else {
            String token = tokens.get(0);
            tokens.remove(0);

            return evalTerm(token, tokens);
        }
    }

    private static int evalTerm(String token, List<String> tokens) {
        int op1 = evalFactor(token, tokens);

        if ( tokens.isEmpty() ) {
            return op1;
        } else {
            String operator = tokens.get(0);
            tokens.remove(0);

            return evalAdditive(op1, operator, tokens);
        }
    }

    private static int evalAdditive(int op1, String operator, List<String> tokens) {
        switch ( operator ) {
            case "+":
            case "-":
                if (tokens.isEmpty()) {
                    throw new IllegalStateException("Missing argument to " + operator);
                } else {
                    String token = tokens.get(0);
                    tokens.remove(0);

                    int op2 = evalFactor(token, tokens);

                    if (tokens.isEmpty()) {
                        return Evaluator.evaluate(operator, op1, op2);
                    } else {
                        String next = tokens.get(0);
                        tokens.remove(0);

                        return evalAdditive(Evaluator.evaluate(operator, op1, op2), next, tokens);
                    }
                }
            default:
                tokens.add(0, operator);
                return op1;
        }
    }

    private static int evalFactor(String token, List<String> tokens) {
        int op1 = evalParenthesized(token, tokens);

        if ( tokens.isEmpty() ) {
            return op1;
        } else {
            String operator = tokens.get(0);
            tokens.remove(0);

            return evalMultiplicative(op1, operator, tokens);
        }
    }

    private static int evalMultiplicative(int op1, String operator, List<String> tokens) {
        switch ( operator ) {
            case "*":
            case "/":
            case "%":
                if (tokens.isEmpty()) {
                    throw new IllegalStateException("Missing argument to " + operator);
                } else {
                    String token = tokens.get(0);
                    tokens.remove(0);

                    int op2 = evalParenthesized(token, tokens);

                    if (tokens.isEmpty()) {
                        return Evaluator.evaluate(operator, op1, op2);
                    } else {
                        String next = tokens.get(0);
                        tokens.remove(0);

                        return evalMultiplicative(Evaluator.evaluate(operator, op1, op2), next, tokens);
                    }
                }
            default:
                tokens.add(0, operator);
                return op1;
        }
    }

    private static int evalParenthesized(String token, List<String> tokens) {
        switch ( token ) {
            case "(":
                int result = evalExpression(tokens);

                if ( tokens.isEmpty()  ||  !tokens.get(0).equals(")") ) {
                    throw new IllegalStateException("Missing delimiter");
                } else {
                    tokens.remove(0);
                    return result;
                }
            default:
                return evalAtom(token);
        }
    }

    private static int evalAtom(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Malformed atom: " + token);
        }
    }

    public static void main(String[] args) {
        System.out.println(stackEvalInfix("(((2 * 3) + 5) % 4)"));
        System.out.println(evalInfix("(2 * 3 + 5) % 4"));
    }

}

package ch07;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public class Evaluator {
    private static Map<String, IntBinaryOperator> operatorMap = new HashMap<>();

    static {
        operatorMap.put("+", (m, n) -> m + n);
        operatorMap.put("-", (m, n) -> m - n);
        operatorMap.put("*", (m, n) -> m * n);
        operatorMap.put("/", (m, n) -> m / n);
        operatorMap.put("%", (m, n) -> m % n);
    }

    public static int evaluate(String operator, int op1, int op2) {
        IntBinaryOperator op = operatorMap.get(operator);

        if ( op == null ) {
            throw new IllegalArgumentException("Unrecognized operator: " + operator);
        } else {
            System.out.println(String.format("Evaluating: %d %s %d", op1, operator, op2));
            return op.applyAsInt(op1, op2);
        }
    }

    public static boolean isOperator(String token) {
        return operatorMap.containsKey(token);
    }
}

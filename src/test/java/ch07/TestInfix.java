package ch07;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class TestInfix {
    @ParameterizedTest
    @CsvSource({
            "9, 9",
            "(9), 9",
            "     9    , 9",
            "(10 / 5), 2",
            "10 / 5, 2",
            "(10 / -5), -2",
            "10 / -5, -2",
            "(9 * 8), 72",
            "9 * 8, 72",
            "2 + 3, 5",
            "3 * -6, -18",
            "(4 + 5) * 9, 81",
            "((2 + 8) * (7 % 3)), 10",
            "(2 + 8) * (7 % 3), 10",
            "(((2 * 3) + 5) % 4), 3",
            "(2 * 3 + 5) % 4, 3",
            "(((2 * 5) % (6 / 4)) + (2 * 3)), 6",
            "(2 * 5 % (6 / 4)) + 2 * 3, 6",
            "(((1 + 2) + 3)), 6",
            "99 - 7 * 13, 8"
//            "2 3 -, -1",
//            "3 2 -, 1",
//            "2 3 +, 5",
//            "3 -6 *, -18",
//            "4 5 + 9 *, 81",
//            "2 8 + 7 3 % *, 10",
//            "2 3 * 5 + 4 %, 3",
//            "2 5 * 6 4 / % 2 3 * +, 6", // 7 in Lisp due to proper arithmetic!
//            "1 2 3 4 + + +, 10",
//            "1 2 + 3 + 4 +, 10",
//            "99 7 13 * -, 8"
    })
    public void testEvalInfix(String expression, int value) {
        assertThat(Infix.evalInfix(expression)).isEqualTo(value);
    }
}

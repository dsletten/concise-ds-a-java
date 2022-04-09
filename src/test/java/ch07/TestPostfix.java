package ch07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPostfix {
    @ParameterizedTest
    @CsvSource({
            "9, 9",
            "     9    , 9",
            "2 3 -, -1",
            "3 2 -, 1",
            "2 3 +, 5",
            "3 -6 *, -18",
            "4 5 + 9 *, 81",
            "2 8 + 7 3 % *, 10",
            "2 3 * 5 + 4 %, 3",
            "2 5 * 6 4 / % 2 3 * +, 6", // 7 in Lisp due to proper arithmetic!
            "1 2 3 4 + + +, 10",
            "1 2 + 3 + 4 +, 10",
            "99 7 13 * -, 8"
    })
    public void testEvalPostfix(String expression, int value) {
        assertThat(Postfix.evalPostfix(expression)).isEqualTo(value);
    }

    @Test
    public void testEquivalentEvalPostfix() {
        List<String> expressions = List.of("2 3 +", "1 1 + 3 +", "8 8 / 1 + 3 +", "4 2 * 8 / 1 + 3 +", "2 2 1 + +", "2 2 8 8 / + +", "2 2 8 4 2 * / + +");
        List<Integer> values = expressions.stream().map(s -> Postfix.evalPostfix(s)).collect(Collectors.toList());
        for (int i : values) {
            assertThat(i == values.get(0));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "9, 9",
            "     9    , 9",
            "2 3 -, -1",
            "3 2 -, 1",
            "2 3 +, 5",
            "3 -6 *, -18",
            "4 5 + 9 *, 81",
            "2 8 + 7 3 % *, 10",
            "2 3 * 5 + 4 %, 3",
            "2 5 * 6 4 / % 2 3 * +, 6", // 7 in Lisp due to proper arithmetic!
            "1 2 3 4 + + +, 10",
            "1 2 + 3 + 4 +, 10",
            "99 7 13 * -, 8"
    })
    public void testStackEvalPostfix(String expression, int value) {
        assertThat(Postfix.stackEvalPostfix(expression)).isEqualTo(value);
    }

    @Test
    public void testEquivalentStackEvalPostfix() {
        List<String> expressions = List.of("2 3 +", "1 1 + 3 +", "8 8 / 1 + 3 +", "4 2 * 8 / 1 + 3 +", "2 2 1 + +", "2 2 8 8 / + +", "2 2 8 4 2 * / + +");
        List<Integer> values = expressions.stream().map(s -> Postfix.stackEvalPostfix(s)).collect(Collectors.toList());
        for (int i : values) {
            assertThat(i == values.get(0));
        }
    }
}

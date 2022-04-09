package ch07;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPrefix {
    @ParameterizedTest
    @CsvSource({
            "9, 9",
            "     9    , 9",
            "+ 2 3, 5",
            "* 3 -6, -18",
            "* + 4 5 9, 81",
            "* + 2 8 % 7 3, 10",
            "% + * 2 3 5 4, 3",
            "+ % * 2 5 / 6 4 * 2 3, 6", // 7 in Lisp due to proper arithmetic!
            "+ 1 + 2 + 3 4, 10",
            "+ + + 1 2 3 4, 10",
            "- 99 * 7 13, 8"
    })
    public void testEvalPrefix(String expression, int value) {
        assertThat(Prefix.evalPrefix(expression)).isEqualTo(value);
    }

    @ParameterizedTest
    @CsvSource({
            "9, 9",
            "     9    , 9",
            "+ 2 3, 5",
            "* 3 -6, -18",
            "* + 4 5 9, 81",
            "* + 2 8 % 7 3, 10",
            "% + * 2 3 5 4, 3",
            "+ % * 2 5 / 6 4 * 2 3, 6", // 7 in Lisp due to proper arithmetic!
            "+ 1 + 2 + 3 4, 10",
            "+ + + 1 2 3 4, 10",
            "- 99 * 7 13, 8"
    })
    public void testStackEvalPrefix(String expression, int value) {
        assertThat(Prefix.stackEvalPrefix(expression)).isEqualTo(value);
    }
}

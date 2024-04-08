package ch02;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestCounter {
    public static void testConstructor(Function<Integer, Counter> f) {
        assertThat(f.apply(8).index()).isZero();

        {
            int n = 10;
            assertThat(f.apply(n).modulus()).isEqualTo(n);
        }

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> f.apply(0));
    }

    public static void testAdvance(Function<Integer, Counter> f) {
        assertThat(f.apply(10).advance().index()).isEqualTo(1);

        {
            int n = 10;
            assertThat(f.apply(n).advance(n).index()).isZero();
        }

        {
            int n = 10;
            assertThat(f.apply(n).advance(-2).index()).isEqualTo(n - 2);
        }
    }

    public static void testSet(Function<Integer, Counter> f) {
        assertThat(f.apply(10).advance().set(0).index()).isZero();
        assertThat(f.apply(10).advance(2).set(0).index()).isZero();

        {
            int n = 10;
            assertThat(f.apply(n).set(-4).index()).isEqualTo(n - 4);
        }

        {
            int n = 10;
            int m = 6;
            assertThat(f.apply(n).advance().set(m).index()).isEqualTo(AbstractCounter.mod(m, n));
        }

        {
            int n = 10;
            int m = 16;
            assertThat(f.apply(n).advance().set(m).index()).isEqualTo(AbstractCounter.mod(m, n));
        }
    }

    public static void testReset(Function<Integer, Counter> f) {
        assertThat(f.apply(10).advance().reset().index()).isZero();

        {
            int n = 10;
            assertThat(f.apply(n).set(n - 1).reset().index()).isZero();
        }
    }

    public static void testRollover(Function<Integer, Counter> f) {
        int n = 10;
        Counter c = f.apply(n);

        for (int i = 0; i < n; i++) {
            c = c.advance();
        }

        assertThat(c.index()).isZero();
    }
}

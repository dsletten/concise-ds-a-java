package containers;

import java.util.EmptyStackException;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestStack {
    public static void testConstructor(Supplier<Stack<Integer>> f) {
        Stack<Integer> stack = f.get();
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.size()).isZero();
        assertThatExceptionOfType(EmptyStackException.class).isThrownBy(stack::peek);
        assertThatExceptionOfType(EmptyStackException.class).isThrownBy(stack::pop);
    }

    public static void testIsEmpty(Supplier<Stack<Integer>> f) {
        Stack<Integer> stack = f.get();
        assertThat(stack.isEmpty()).isTrue();
        stack.push(0);
        assertThat(stack.isEmpty()).isFalse();
        stack.pop();
        assertThat(stack.isEmpty()).isTrue();
    }

    public static void testSize(Supplier<Stack<Integer>> f) {
        Stack<Integer> stack = f.get();
        assertThat(stack.size()).isZero();

        for (int i = 1; i < 1000; i++) {
            stack.push(i);
            assertThat(stack.size()).isEqualTo(i);
        }
    }

    public static void testClear(Supplier<Stack<Integer>> f) {
        Stack<Integer> stack = f.get();
        fillStack(stack, 1000);
        assertThat(stack.isEmpty()).isFalse();
        stack.clear();
        assertThat(stack.isEmpty()).isTrue();
    }

    public static void testPop(Supplier<Stack<Integer>> f) {
        Stack<Integer> stack = f.get();
        fillStack(stack, 1000);

        for (int i = stack.size(); i > 0; i--) {
            assertThat(stack.pop()).isEqualTo(i);
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    public static void testPeek(Supplier<Stack<Integer>> f) {
        Stack<Integer> stack = f.get();
        fillStack(stack, 1000);

        for (int i = stack.size(); i > 0; i--) {
            assertThat(stack.peek()).isEqualTo(i);
            stack.pop();
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    public static void testWave(Supplier<Stack<Integer>> f) {
        Stack<Integer> stack = f.get();

        fillStack(stack, 5000);
        assertThat(stack.size()).isEqualTo(5000);

        for (int i = 0; i < 3000; i++) {
            stack.pop();
        }
        assertThat(stack.size()).isEqualTo(2000);

        fillStack(stack, 5000);
        assertThat(stack.size()).isEqualTo(7000);

        for (int i = 0; i < 3000; i++) {
            stack.pop();
        }
        assertThat(stack.size()).isEqualTo(4000);

        fillStack(stack, 5000);
        assertThat(stack.size()).isEqualTo(9000);

        for (int i = 0; i < 3000; i++) {
            stack.pop();
        }
        assertThat(stack.size()).isEqualTo(6000);

        fillStack(stack, 4000);
        assertThat(stack.size()).isEqualTo(10000);

        for (int i = 0; i < 10000; i++) {
            stack.pop();
        }
        assertThat(stack.isEmpty()).isTrue();
    }

    private static void fillStack(Stack<Integer> stack, int count) {
        for (int i = 1; i <= count; i++) {
            stack.push(i);
        }
    }
}

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
        testSize(f, 1000);
    }

    public static void testSize(Supplier<Stack<Integer>> f, int count) {
        Stack<Integer> stack = f.get();
        assertThat(stack.size()).isZero();

        for (int i = 1; i <= count; i++) {
            stack.push(i);
            assertThat(stack.size()).isEqualTo(i);
        }

        for (int i = count-1; i >= 0; i--) {
            stack.pop();
            assertThat(stack.size()).isEqualTo(i);
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    public static void testClear(Supplier<Stack<Integer>> f) {
        testClear(f, 1000);
    }

    public static void testClear(Supplier<Stack<Integer>> f, int count) {
        Stack<Integer> stack = f.get().fill(count, n -> n);
        assertThat(stack.isEmpty()).isFalse();

        stack.clear();
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.size()).isZero();
    }

    public static void testToArray(Supplier<Stack<Integer>> f) {
        testToArray(f, 1000);
    }

    public static void testToArray(Supplier<Stack<Integer>> f, int count) {
        Stack<Integer> stack = f.get().fill(count, n -> n);
        Integer[] expected = new Integer[count];
        for (int i = 0; i < count; i++) {
            expected[i] = count - i;
        }

        Integer[] elements = stack.toArray(new Integer[]{});

        for (int i = 0; i < count; i++) {
            assertThat(elements[i]).isEqualTo(expected[i]);
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    public static void testPush(Supplier<Stack<Integer>> f) {
        testPush(f, 1000);
    }

    public static void testPush(Supplier<Stack<Integer>> f, int count) {
        Stack<Integer> stack = f.get();

        for (int i = 1; i <= count; i++) {
            stack.push(i);
            assertThat(stack.peek()).isEqualTo(i);
        }
    }
            
    public static void testPeekPop(Supplier<Stack<Integer>> f) {
        testPeekPop(f, 1000);
    }

    public static void testPeekPop(Supplier<Stack<Integer>> f, int count) {
        Stack<Integer> stack = f.get().fill(count, n -> n);

        for (int i = 1; i <= count; i++) {
            int top = stack.peek();
            int popped = stack.pop();
            assertThat(top).isEqualTo(popped);
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    //    testTime...    

    public static void testWave(Supplier<Stack<Integer>> f) {
        Stack<Integer> stack = f.get();

        stack.fill(5000, n -> n);
        assertThat(stack.size()).isEqualTo(5000);

        for (int i = 0; i < 3000; i++) {
            stack.pop();
        }
        assertThat(stack.size()).isEqualTo(2000);

        stack.fill(5000, n -> n);
        assertThat(stack.size()).isEqualTo(7000);

        for (int i = 0; i < 3000; i++) {
            stack.pop();
        }
        assertThat(stack.size()).isEqualTo(4000);

        stack.fill(5000, n -> n);
        assertThat(stack.size()).isEqualTo(9000);

        for (int i = 0; i < 3000; i++) {
            stack.pop();
        }
        assertThat(stack.size()).isEqualTo(6000);

        stack.fill(4000, n -> n);
        assertThat(stack.size()).isEqualTo(10000);

        for (int i = 0; i < 10000; i++) {
            stack.pop();
        }
        assertThat(stack.isEmpty()).isTrue();
    }
}

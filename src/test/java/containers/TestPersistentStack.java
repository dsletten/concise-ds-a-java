package containers;

import java.util.EmptyStackException;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestPersistentStack {
    public static void testConstructor(Supplier<PersistentStack<Integer>> f) {
        PersistentStack<Integer> stack = f.get();

        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.size()).isZero();
        assertThatExceptionOfType(EmptyStackException.class).isThrownBy(stack::peek);
        assertThatExceptionOfType(EmptyStackException.class).isThrownBy(stack::pop);
    }

    public static void testIsEmpty(Supplier<PersistentStack<String>> f) {
        PersistentStack<String> stack = f.get();
        assertThat(stack.isEmpty()).isTrue();

        stack = stack.push("Word Up!");
        assertThat(stack.isEmpty()).isFalse();

        stack = stack.pop();
        assertThat(stack.isEmpty()).isTrue();
    }

    public static void testSize(Supplier<PersistentStack<Integer>> f) {
        testSize(f, 1000);
    }

    public static void testSize(Supplier<PersistentStack<Integer>> f, int count) {
        PersistentStack<Integer> stack = f.get();
        assertThat(stack.size()).isZero();

        for (int i = 1; i <= count; i++) {
            stack = stack.push(i);
            assertThat(stack.size()).isEqualTo(i);
        }

        for (int i = count-1; i >= 0; i--) {
            stack = stack.pop();
            assertThat(stack.size()).isEqualTo(i);
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    public static void testClear(Supplier<PersistentStack<Integer>> f) {
        testClear(f, 1000);
    }
    
    public static void testClear(Supplier<PersistentStack<Integer>> f, int count) {
        PersistentStack<Integer> stack = f.get().fill(count, n -> n);
        assertThat(stack.isEmpty()).isFalse();

        stack = stack.clear();
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.size()).isZero();
    }

    public static void testToArray(Supplier<PersistentStack<Integer>> f) {
        testToArray(f, 1000);
    }

    public static void testToArray(Supplier<PersistentStack<Integer>> f, int count) {
        PersistentStack<Integer> stack = f.get().fill(count, n -> n);
        Integer[] expected = new Integer[count];
        for (int i = 0; i < count; i++) {
            expected[i] = count - i;
        }

        Integer[] elements = stack.toArray(new Integer[]{});

        for (int i = 0; i < count; i++) {
            assertThat(elements[i]).isEqualTo(expected[i]);
        }
    }

    public static void testPush(Supplier<PersistentStack<Integer>> f) {
        testPush(f, 1000);
    }

    public static void testPush(Supplier<PersistentStack<Integer>> f, int count) {
        PersistentStack<Integer> stack = f.get();

        for (int i = 1; i <= count; i++) {
            stack = stack.push(i);
            assertThat(stack.peek()).isEqualTo(i);
        }
    }

    public static void testPeekPop(Supplier<PersistentStack<Integer>> f) {
        testPeekPop(f, 1000);
    }
    
    public static void testPeekPop(Supplier<PersistentStack<Integer>> f, int count) {
        PersistentStack<Integer> stack = f.get().fill(count, n -> n);

        for (int i = count; i >= 1; i--) {
            assertThat(stack.peek()).isEqualTo(i);
            stack = stack.pop();
        }

        assertThat(stack.isEmpty()).isTrue();
    }
}

package containers;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestPersistentStack {
    @Test
    public void testConstructor() {
        PersistentStack<Integer> stack = new PersistentLinkedStack<>();
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.size()).isZero();
        assertThatExceptionOfType(EmptyStackException.class).isThrownBy(stack::peek);
        assertThatExceptionOfType(EmptyStackException.class).isThrownBy(stack::pop);
    }

    @Test
    public void testIsEmpty() {
        PersistentStack<String> stack = new PersistentLinkedStack<>();
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.push("Word Up!").isEmpty()).isFalse();
        assertThat(stack.push("Word Up!").pop().isEmpty()).isTrue();
    }

    @Test
    public void testSize() {
        PersistentStack<Integer> stack = new PersistentLinkedStack<>();
        assertThat(stack.size()).isZero();

        for (int i = 1; i <= 1000; i++) {
            stack = stack.push(i);
            assertThat(stack.size()).isEqualTo(i);
        }
    }

    @Test
    public void testClear() {
        PersistentStack<Integer> stack = fillStack(new PersistentLinkedStack<>(), 1000);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.clear().isEmpty()).isTrue();
    }

    //
    //    testPop() and testPeek() are identical since Java does not support multiple values.
    //    pop() can only return the new stack, not the value being popped too.
    //
    @Test
    public void testPop() {
        PersistentStack<Integer> stack = fillStack(new PersistentLinkedStack<>(), 1000);

        for (int i = stack.size(); i > 0; i--) {
            assertThat(stack.peek()).isEqualTo(i);
            stack = stack.pop();
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    public void testPeek() {
        PersistentStack<Integer> stack = fillStack(new PersistentLinkedStack<>(), 1000);

        for (int i = stack.size(); i > 0; i--) {
            assertThat(stack.peek()).isEqualTo(i);
            stack = stack.pop();
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    private PersistentStack<Integer> fillStack(PersistentStack<Integer> stack, int count) {
        for (int i = 1; i < count; i++) {
            stack = stack.push(i);
        }

        return stack;
    }
}

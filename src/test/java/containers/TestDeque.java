package containers;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestDeque {
    public static void testConstructor(Supplier<Deque<Integer>> f) {
        Deque<Integer> deque = f.get();
        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size()).isZero();
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(deque::rear);
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(deque::dequeueRear);
    }

    public static void testIsEmpty(Supplier<Deque<Integer>> f) {
        Deque<Integer> deque = f.get();
        assertThat(deque.isEmpty()).isTrue();

        deque.enqueueFront(0);
        assertThat(deque.isEmpty()).isFalse();

        deque.dequeueRear();
        assertThat(deque.isEmpty()).isTrue();
    }

    public static void testSize(Supplier<Deque<Integer>> f) {
        testSize(f, 1000);
    }

    public static void testSize(Supplier<Deque<Integer>> f, int count) {
        Deque<Integer> deque = f.get();
        assertThat(deque.size()).isZero();

        for (int i = 1; i <= count; i++) {
            deque.enqueueFront(i);
            assertThat(deque.size()).isEqualTo(i);
        }

        for (int i = count - 1; i >= 0; i--) {
            deque.dequeueRear();
            assertThat(deque.size()).isEqualTo(i);
        }

        assertThat(deque.isEmpty()).isTrue();
    }

    public static void testEnqueueFront(Supplier<Deque<Integer>> f) {
        testEnqueueFront(f, 1000);
    }

    public static void testEnqueueFront(Supplier<Deque<Integer>> f, int count) {
        Deque<Integer> deque = f.get();

        for (int i = 1; i <= count; i++) {
            deque.enqueueFront(i);
            assertThat(deque.dequeueRear()).isEqualTo(i);
        }
    }

    public static void testRearDequeueRear(Supplier<Deque<Integer>> f) {
        testRearDequeueRear(f, 1000);
    }
    
    public static void testRearDequeueRear(Supplier<Deque<Integer>> f, int count) {
        Deque<Integer> deque = f.get().fill(count, n -> n);

        for (int i = 1; i <= count; i++) {
            int rear = deque.rear();
            int dequeued = deque.dequeueRear();
            assertThat(rear).isEqualTo(dequeued);
        }

        assertThat(deque.isEmpty()).isTrue();
    }
}

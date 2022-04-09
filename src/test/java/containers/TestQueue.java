package containers;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestQueue {
    public static void testConstructor(Supplier<Queue<Integer>> f) {
        Queue<Integer> queue = f.get();
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.size()).isZero();
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(queue::front);
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(queue::dequeue);
    }

    public static void testIsEmpty(Supplier<Queue<Integer>> f) {
        Queue<Integer> queue = f.get();
        assertThat(queue.isEmpty()).isTrue();
        queue.enqueue(0);
        assertThat(queue.isEmpty()).isFalse();
        queue.dequeue();
        assertThat(queue.isEmpty()).isTrue();
    }

    public static void testSize(Supplier<Queue<Integer>> f) {
        Queue<Integer> queue = f.get();
        assertThat(queue.size()).isZero();

        for (int i = 1; i < 1000; i++) {
            queue.enqueue(i);
            assertThat(queue.size()).isEqualTo(i);
        }
    }

    public static void testClear(Supplier<Queue<Integer>> f) {
        Queue<Integer> queue = f.get();
        fillQueue(queue, 1000);
        assertThat(queue.isEmpty()).isFalse();
        queue.clear();
        assertThat(queue.isEmpty()).isTrue();
    }

    public static void testDequeue(Supplier<Queue<Integer>> f) {
        Queue<Integer> queue = f.get();
        fillQueue(queue, 1000);

        int count = queue.size();
        for (int i = 1; i <= count; i++) {
            assertThat(queue.dequeue()).isEqualTo(i);
        }

        assertThat(queue.isEmpty()).isTrue();
    }

    public static void testFront(Supplier<Queue<Integer>> f) {
        Queue<Integer> queue = f.get();
        fillQueue(queue, 1000);

        int count = queue.size();
        for (int i = 1; i <= count; i++) {
            assertThat(queue.front()).isEqualTo(i);
            queue.dequeue();
        }

        assertThat(queue.isEmpty()).isTrue();
    }

    public static void testWave(Supplier<Queue<Integer>> f) {
        Queue<Integer> queue = f.get();

        fillQueue(queue, 5000);
        assertThat(queue.size()).isEqualTo(5000);

        for (int i = 0; i < 3000; i++) {
            queue.dequeue();
        }
        assertThat(queue.size()).isEqualTo(2000);

        fillQueue(queue, 5000);
        assertThat(queue.size()).isEqualTo(7000);

        for (int i = 0; i < 3000; i++) {
            queue.dequeue();
        }
        assertThat(queue.size()).isEqualTo(4000);

        fillQueue(queue, 5000);
        assertThat(queue.size()).isEqualTo(9000);

        for (int i = 0; i < 3000; i++) {
            queue.dequeue();
        }
        assertThat(queue.size()).isEqualTo(6000);

        fillQueue(queue, 4000);
        assertThat(queue.size()).isEqualTo(10000);

        for (int i = 0; i < 10000; i++) {
            queue.dequeue();
        }
        assertThat(queue.isEmpty()).isTrue();
    }

    private static void fillQueue(Queue<Integer> queue, int count) {
        for (int i = 1; i <= count; i++) {
            queue.enqueue(i);
        }
    }
}

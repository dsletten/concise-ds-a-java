package containers;

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
        testSize(f, 1000);
    }

    public static void testSize(Supplier<Queue<Integer>> f, int count) {
        Queue<Integer> queue = f.get();
        assertThat(queue.size()).isZero();

        for (int i = 1; i <= count; i++) {
            queue.enqueue(i);
            assertThat(queue.size()).isEqualTo(i);
        }

        for (int i = count - 1; i >= 0; i--) {
            queue.dequeue();
            assertThat(queue.size()).isEqualTo(i);
        }

        assertThat(queue.isEmpty()).isTrue();
    }

    public static void testClear(Supplier<Queue<Integer>> f) {
        testClear(f, 1000);
    }
    
    public static void testClear(Supplier<Queue<Integer>> f, int count) {
        Queue<Integer> queue = f.get().fill(count, n -> n);
        assertThat(queue.isEmpty()).isFalse();

        queue.clear();
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.size()).isZero();

        queue.fill(count, n -> n);
        assertThat(queue.isEmpty()).isFalse();
    }

    public static void testToArray(Supplier<Queue<Integer>> f) {
        testToArray(f, 1000);
    }

    public static void testToArray(Supplier<Queue<Integer>> f, int count) {
        Queue<Integer> queue = f.get().fill(count, n -> n);
        Integer[] expected = new Integer[count];
        for (int i = 0; i < count; i++) {
            expected[i] = i + 1;
        }

        Integer[] elements = queue.toArray(new Integer[]{});

        for (int i = 0; i < count; i++) {
            assertThat(elements[i]).isEqualTo(expected[i]);
        }

        assertThat(queue.isEmpty()).isTrue();
    }

    public static void testEnqueue(Supplier<Queue<Integer>> f) {
        testEnqueue(f, 1000);
    }

    public static void testEnqueue(Supplier<Queue<Integer>> f, int count) {
        Queue<Integer> queue = f.get();

        for (int i = 1; i <= count; i++) {
            queue.enqueue(i);
            assertThat(queue.dequeue()).isEqualTo(i);
        }
    }
            
    public static void testFrontDequeue(Supplier<Queue<Integer>> f) {
        testFrontDequeue(f, 1000);
    }
    
    public static void testFrontDequeue(Supplier<Queue<Integer>> f, int count) {
        Queue<Integer> queue = f.get().fill(count, n -> n);

        for (int i = 1; i <= count; i++) {
            int front = queue.front();
            int dequeued = queue.dequeue();
            assertThat(front).isEqualTo(dequeued);
        }

        assertThat(queue.isEmpty()).isTrue();
    }

    //    testTime...    

    public static void testWave(Supplier<Queue<Integer>> f) {
        Queue<Integer> queue = f.get();

        queue.fill(5000, n -> n);
        assertThat(queue.size()).isEqualTo(5000);

        for (int i = 0; i < 3000; i++) {
            queue.dequeue();
        }
        assertThat(queue.size()).isEqualTo(2000);

        queue.fill(5000, n -> n);
        assertThat(queue.size()).isEqualTo(7000);

        for (int i = 0; i < 3000; i++) {
            queue.dequeue();
        }
        assertThat(queue.size()).isEqualTo(4000);

        queue.fill(5000, n -> n);
        assertThat(queue.size()).isEqualTo(9000);

        for (int i = 0; i < 3000; i++) {
            queue.dequeue();
        }
        assertThat(queue.size()).isEqualTo(6000);

        queue.fill(4000, n -> n);
        assertThat(queue.size()).isEqualTo(10000);

        for (int i = 0; i < 10000; i++) {
            queue.dequeue();
        }
        assertThat(queue.isEmpty()).isTrue();
    }
}

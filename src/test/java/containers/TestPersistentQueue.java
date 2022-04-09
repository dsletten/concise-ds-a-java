package containers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestPersistentQueue {
    @Test
    public void testConstructor() {
        PersistentQueue<Integer> queue = new PersistentLinkedQueue<>();
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.size()).isZero();
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(queue::front);
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(queue::dequeue);
    }

    @Test
    public void testIsEmpty() {
        PersistentQueue<String> queue = new PersistentLinkedQueue<>();
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.enqueue("Word Up!").isEmpty()).isFalse();
        assertThat(queue.enqueue("Word Up!").dequeue().isEmpty()).isTrue();
    }

    @Test
    public void testSize() {
        PersistentQueue<Integer> queue = new PersistentLinkedQueue<>();
        assertThat(queue.size()).isZero();

        for (int i = 1; i <= 1000; i++) {
            queue = queue.enqueue(i);
            assertThat(queue.size()).isEqualTo(i);
        }
    }

    @Test
    public void testClear() {
        PersistentQueue<Integer> queue = fillqueue(new PersistentLinkedQueue<>(), 1000);
        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.clear().isEmpty()).isTrue();
    }

    //
    //    testDequeue() and testFront() are identical since Java does not support multiple values.
    //    dequeue() can only return the new queue, not the value being dequeued too.
    //
    @Test
    public void testDequeue() {
        PersistentQueue<Integer> queue = fillqueue(new PersistentLinkedQueue<>(), 1000);

        int limit = queue.size();
        for (int i = 1; i <= limit; i++) {
            assertThat(queue.front()).isEqualTo(i);
            queue = queue.dequeue();
        }

        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    public void testFront() {
        PersistentQueue<Integer> queue = fillqueue(new PersistentLinkedQueue<>(), 1000);

        int limit = queue.size();
        for (int i = 1; i <= limit; i++) {
            assertThat(queue.front()).isEqualTo(i);
            queue = queue.dequeue();
        }

        assertThat(queue.isEmpty()).isTrue();
    }

    private PersistentQueue<Integer> fillqueue(PersistentQueue<Integer> queue, int count) {
        for (int i = 1; i <= count; i++) {
            queue = queue.enqueue(i);
        }

        return queue;
    }
}

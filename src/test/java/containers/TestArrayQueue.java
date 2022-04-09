package containers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestArrayQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(ArrayQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(ArrayQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(ArrayQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(ArrayQueue::new);
    }

    @Test
    public void testDequeue() {
        TestQueue.testDequeue(ArrayQueue::new);
    }

    @Test
    public void testFront() {
        TestQueue.testFront(ArrayQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(ArrayQueue::new);
    }
}

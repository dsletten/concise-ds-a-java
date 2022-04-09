package containers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestArrayListQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(ArrayListQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(ArrayListQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(ArrayListQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(ArrayListQueue::new);
    }

    @Test
    public void testDequeue() {
        TestQueue.testDequeue(ArrayListQueue::new);
    }

    @Test
    public void testFront() {
        TestQueue.testFront(ArrayListQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(ArrayListQueue::new);
    }
}

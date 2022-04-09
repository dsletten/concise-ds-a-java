package containers;

import org.junit.jupiter.api.Test;

public class TestCircularQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(CircularQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(CircularQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(CircularQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(CircularQueue::new);
    }

    @Test
    public void testDequeue() {
        TestQueue.testDequeue(CircularQueue::new);
    }

    @Test
    public void testFront() {
        TestQueue.testFront(CircularQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(CircularQueue::new);
    }
}

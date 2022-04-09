package containers;

import org.junit.jupiter.api.Test;

public class TestLinkedQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(LinkedQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(LinkedQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(LinkedQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(LinkedQueue::new);
    }

    @Test
    public void testDequeue() {
        TestQueue.testDequeue(LinkedQueue::new);
    }

    @Test
    public void testFront() {
        TestQueue.testFront(LinkedQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(LinkedQueue::new);
    }
}

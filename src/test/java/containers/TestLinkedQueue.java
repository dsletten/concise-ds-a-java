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
    public void testToArray() {
        TestQueue.testToArray(LinkedQueue::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(LinkedQueue::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(LinkedQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(LinkedQueue::new);
    }
}

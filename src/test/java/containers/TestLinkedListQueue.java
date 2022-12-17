package containers;

import org.junit.jupiter.api.Test;

public class TestLinkedListQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(LinkedListQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(LinkedListQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(LinkedListQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(LinkedListQueue::new);
    }

    @Test
    public void testToArray() {
        TestQueue.testToArray(LinkedListQueue::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(LinkedListQueue::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(LinkedListQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(LinkedListQueue::new);
    }
}

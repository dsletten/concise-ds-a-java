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
    public void testDequeue() {
        TestQueue.testDequeue(LinkedListQueue::new);
    }

    @Test
    public void testFront() {
        TestQueue.testFront(LinkedListQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(LinkedListQueue::new);
    }
}

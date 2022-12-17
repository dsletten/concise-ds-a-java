package containers;

import org.junit.jupiter.api.Test;

public class TestHashQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(HashQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(HashQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(HashQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(HashQueue::new);
    }

    @Test
    public void testToArray() {
        TestQueue.testToArray(HashQueue::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(HashQueue::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(HashQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(HashQueue::new);
    }
}

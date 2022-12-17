package containers;

import org.junit.jupiter.api.Test;

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
    public void testToArray() {
        TestQueue.testToArray(ArrayListQueue::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(ArrayListQueue::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(ArrayListQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(ArrayListQueue::new);
    }
}

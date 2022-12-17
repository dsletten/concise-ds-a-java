package containers;

import org.junit.jupiter.api.Test;

public class TestRecyclingQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(RecyclingQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(RecyclingQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(RecyclingQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(RecyclingQueue::new);
    }

    @Test
    public void testToArray() {
        TestQueue.testToArray(RecyclingQueue::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(RecyclingQueue::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(RecyclingQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(RecyclingQueue::new);
    }
}

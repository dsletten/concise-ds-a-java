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
    public void testDequeue() {
        TestQueue.testDequeue(RecyclingQueue::new);
    }

    @Test
    public void testFront() {
        TestQueue.testFront(RecyclingQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(RecyclingQueue::new);
    }
}

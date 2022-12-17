package containers;

import org.junit.jupiter.api.Test;

public class TestArrayListJQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(ArrayListJQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(ArrayListJQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(ArrayListJQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(ArrayListJQueue::new);
    }

    @Test
    public void testToArray() {
        TestQueue.testToArray(ArrayListJQueue::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(ArrayListJQueue::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(ArrayListJQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(ArrayListJQueue::new);
    }
}

package containers;

import org.junit.jupiter.api.Test;

public class TestDllQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(DllQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(DllQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(DllQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(DllQueue::new);
    }

    @Test
    public void testToArray() {
        TestQueue.testToArray(DllQueue::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(DllQueue::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(DllQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(DllQueue::new);
    }
}

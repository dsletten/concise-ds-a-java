package containers;

import org.junit.jupiter.api.Test;

public class TestLinkedRingBuffer {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(LinkedRingBuffer::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(LinkedRingBuffer::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(LinkedRingBuffer::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(LinkedRingBuffer::new);
    }

    @Test
    public void testToArray() {
        TestQueue.testToArray(LinkedRingBuffer::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(LinkedRingBuffer::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(LinkedRingBuffer::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(LinkedRingBuffer::new);
    }
}

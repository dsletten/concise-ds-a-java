package containers;

import org.junit.jupiter.api.Test;

public class TestRingBuffer {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(RingBuffer::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(RingBuffer::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(RingBuffer::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(RingBuffer::new);
    }

    @Test
    public void testDequeue() {
        TestQueue.testDequeue(RingBuffer::new);
    }

    @Test
    public void testFront() {
        TestQueue.testFront(RingBuffer::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(RingBuffer::new);
    }
}

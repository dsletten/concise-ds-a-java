package containers.alt.api.impl;

import org.junit.jupiter.api.Test;

public class TestArrayRingBuffer {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(ArrayRingBuffer::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(ArrayRingBuffer::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(ArrayRingBuffer::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(ArrayRingBuffer::new);
    }

    @Test
    public void testToArray() {
        TestQueue.testToArray(ArrayRingBuffer::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(ArrayRingBuffer::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(ArrayRingBuffer::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(ArrayRingBuffer::new);
    }
}

package containers.alt.api.impl;

import org.junit.jupiter.api.Test;

public class TestArrayRingBufferDeque {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(ArrayRingBufferDeque::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(ArrayRingBufferDeque::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(ArrayRingBufferDeque::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(ArrayRingBufferDeque::new);
    }

    @Test
    public void testToArray() {
        TestQueue.testToArray(ArrayRingBufferDeque::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(ArrayRingBufferDeque::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(ArrayRingBufferDeque::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(ArrayRingBufferDeque::new);
    }

    @Test
    public void testDequeConstructor() {
        TestDeque.testConstructor(ArrayRingBufferDeque::new);
    }

    @Test
    public void testDequeIsEmpty() {
        TestDeque.testIsEmpty(ArrayRingBufferDeque::new);
    }

    @Test
    public void testDequeSize() {
        TestDeque.testSize(ArrayRingBufferDeque::new);
    }

    @Test
    public void testEnqueueFront() {
        TestDeque.testEnqueueFront(ArrayRingBufferDeque::new);
    }

    @Test
    public void testRearDequeueRear() {
        TestDeque.testRearDequeueRear(ArrayRingBufferDeque::new);
    }
}

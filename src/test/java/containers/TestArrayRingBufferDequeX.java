package containers;

import org.junit.jupiter.api.Test;

public class TestArrayRingBufferDequeX {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testToArray() {
        TestQueue.testToArray(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testEnqueue() {
        TestQueue.testEnqueue(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testFrontDequeue() {
        TestQueue.testFrontDequeue(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testDequeConstructor() {
        TestDeque.testConstructor(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testDequeIsEmpty() {
        TestDeque.testIsEmpty(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testDequeSize() {
        TestDeque.testSize(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testEnqueueFront() {
        TestDeque.testEnqueueFront(ArrayRingBufferDequeX::new);
    }

    @Test
    public void testRearDequeueRear() {
        TestDeque.testRearDequeueRear(ArrayRingBufferDequeX::new);
    }
}

package containers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TestHashQueue {
    @Test
    public void testConstructor() {
        TestQueue.testConstructor(HashQueue::new);
    }

    @Test
    public void testIsEmpty() {
        TestQueue.testIsEmpty(HashQueue::new);
    }

    @Test
    public void testSize() {
        TestQueue.testSize(HashQueue::new);
    }

    @Test
    public void testClear() {
        TestQueue.testClear(HashQueue::new);
    }

    @Test
    public void testDequeue() {
        TestQueue.testDequeue(HashQueue::new);
    }

    @Test
    public void testFront() {
        TestQueue.testFront(HashQueue::new);
    }

    @Test
    public void testWave() {
        TestQueue.testWave(HashQueue::new);
    }
}

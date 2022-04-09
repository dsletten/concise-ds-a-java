package containers;

import org.junit.jupiter.api.Test;

public class TestArrayStack {
    @Test
    public void testConstructor() {
        TestStack.testConstructor(ArrayStack::new);
    }

    @Test
    public void testIsEmpty() {
        TestStack.testIsEmpty(ArrayStack::new);
    }

    @Test
    public void testSize() {
        TestStack.testSize(ArrayStack::new);
    }

    @Test
    public void testClear() {
        TestStack.testClear(ArrayStack::new);
    }

    @Test
    public void testPop() {
        TestStack.testPop(ArrayStack::new);
    }

    @Test
    public void testPeek() {
        TestStack.testPeek(ArrayStack::new);
    }

    @Test
    public void testWave() {
        TestStack.testWave(ArrayStack::new);
    }
}

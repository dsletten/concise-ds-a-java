package containers;

import org.junit.jupiter.api.Test;

public class TestLinkedStack {
    @Test
    public void testConstructor() {
        TestStack.testConstructor(LinkedStack::new);
    }

    @Test
    public void testIsEmpty() {
        TestStack.testIsEmpty(LinkedStack::new);
    }

    @Test
    public void testSize() {
        TestStack.testSize(LinkedStack::new);

    }

    @Test
    public void testClear() {
        TestStack.testClear(LinkedStack::new);
    }

    @Test
    public void testPop() {
        TestStack.testPop(LinkedStack::new);
    }

    @Test
    public void testPeek() {
        TestStack.testPeek(LinkedStack::new);
    }

    @Test
    public void testWave() {
        TestStack.testWave(LinkedStack::new);
    }
}

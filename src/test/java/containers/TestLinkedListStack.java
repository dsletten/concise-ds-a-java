package containers;

import org.junit.jupiter.api.Test;

public class TestLinkedListStack {
    @Test
    public void testConstructor() {
        TestStack.testConstructor(LinkedListStack::new);
    }

    @Test
    public void testIsEmpty() {
        TestStack.testIsEmpty(LinkedListStack::new);
    }

    @Test
    public void testSize() {
        TestStack.testSize(LinkedListStack::new);

    }

    @Test
    public void testClear() {
        TestStack.testClear(LinkedListStack::new);
    }

    @Test
    public void testPop() {
        TestStack.testPop(LinkedListStack::new);
    }

    @Test
    public void testPeek() {
        TestStack.testPeek(LinkedListStack::new);
    }

    @Test
    public void testWave() {
        TestStack.testWave(LinkedListStack::new);
    }
}

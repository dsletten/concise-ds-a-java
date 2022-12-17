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
    public void testToArray() {
        TestStack.testToArray(LinkedListStack::new);
    }

    @Test
    public void testPush() {
        TestStack.testPush(LinkedListStack::new);
    }

    @Test
    public void testPeekPop() {
        TestStack.testPeekPop(LinkedListStack::new);
    }

    @Test
    public void testWave() {
        TestStack.testWave(LinkedListStack::new);
    }
}

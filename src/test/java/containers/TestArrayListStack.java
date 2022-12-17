package containers;

import org.junit.jupiter.api.Test;

public class TestArrayListStack {
    @Test
    public void testConstructor() {
        TestStack.testConstructor(ArrayListStack::new);
    }

    @Test
    public void testIsEmpty() {
        TestStack.testIsEmpty(ArrayListStack::new);
    }

    @Test
    public void testSize() {
        TestStack.testSize(ArrayListStack::new);

    }

    @Test
    public void testClear() {
        TestStack.testClear(ArrayListStack::new);
    }

    @Test
    public void testToArray() {
        TestStack.testToArray(ArrayListStack::new);
    }

    @Test
    public void testPush() {
        TestStack.testPush(ArrayListStack::new);
    }

    @Test
    public void testPeekPop() {
        TestStack.testPeekPop(ArrayListStack::new);
    }

    @Test
    public void testWave() {
        TestStack.testWave(ArrayListStack::new);
    }
}

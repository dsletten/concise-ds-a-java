package containers;

import org.junit.jupiter.api.Test;

public class TestHashStack {
    @Test
    public void testConstructor() {
        TestStack.testConstructor(HashStack::new);
    }

    @Test
    public void testIsEmpty() {
        TestStack.testIsEmpty(HashStack::new);
    }

    @Test
    public void testSize() {
        TestStack.testSize(HashStack::new);
    }

    @Test
    public void testClear() {
        TestStack.testClear(HashStack::new);
    }

    @Test
    public void testToArray() {
        TestStack.testToArray(HashStack::new);
    }

    @Test
    public void testPush() {
        TestStack.testPush(HashStack::new);
    }

    @Test
    public void testPeekPop() {
        TestStack.testPeekPop(HashStack::new);
    }

    @Test
    public void testWave() {
        TestStack.testWave(HashStack::new);
    }
}

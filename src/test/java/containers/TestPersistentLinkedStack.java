package containers;

import org.junit.jupiter.api.Test;

public class TestPersistentLinkedStack {
    @Test
    public void testConstructor() {
        TestPersistentStack.testConstructor(PersistentLinkedStack::new);
    }

    @Test
    public void testIsEmpty() {
        TestPersistentStack.testIsEmpty(PersistentLinkedStack::new);
    }

    @Test
    public void testSize() {
        TestPersistentStack.testSize(PersistentLinkedStack::new);

    }

    @Test
    public void testClear() {
        TestPersistentStack.testClear(PersistentLinkedStack::new);
    }

    @Test
    public void testToArray() {
        TestPersistentStack.testToArray(PersistentLinkedStack::new);
    }

    @Test
    public void testPush() {
        TestPersistentStack.testPush(PersistentLinkedStack::new);
    }

    @Test
    public void testPeekPop() {
        TestPersistentStack.testPeekPop(PersistentLinkedStack::new);
    }
}

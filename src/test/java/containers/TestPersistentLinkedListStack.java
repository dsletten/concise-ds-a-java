package containers;

import org.junit.jupiter.api.Test;

public class TestPersistentLinkedListStack {
    @Test
    public void testConstructor() {
        TestPersistentStack.testConstructor(PersistentLinkedListStack::new);
    }

    @Test
    public void testIsEmpty() {
        TestPersistentStack.testIsEmpty(PersistentLinkedListStack::new);
    }

    @Test
    public void testSize() {
        TestPersistentStack.testSize(PersistentLinkedListStack::new);

    }

    @Test
    public void testClear() {
        TestPersistentStack.testClear(PersistentLinkedListStack::new);
    }

    @Test
    public void testToArray() {
        TestPersistentStack.testToArray(PersistentLinkedListStack::new);
    }

    @Test
    public void testPush() {
        TestPersistentStack.testPush(PersistentLinkedListStack::new);
    }

    @Test
    public void testPeekPop() {
        TestPersistentStack.testPeekPop(PersistentLinkedListStack::new);
    }
}

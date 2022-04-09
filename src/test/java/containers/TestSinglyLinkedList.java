package containers;

import org.junit.jupiter.api.Test;

public class TestSinglyLinkedList {
    @Test
    public void testConstructor() {
        TestList.testConstructor(SinglyLinkedList::new);
    }

    @Test
    public void testIsEmpty() {
        TestList.testIsEmpty(SinglyLinkedList::new);
    }

    @Test
    public void testSize() {
        TestList.testSize(SinglyLinkedList::new);
    }

    @Test
    public void testClear() {
        TestList.testClear(SinglyLinkedList::new);
    }

    @Test
    public void testContains() {
        TestList.testContains(SinglyLinkedList::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(SinglyLinkedList<Character>::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(SinglyLinkedList<String>::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new SinglyLinkedList<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(SinglyLinkedList<String>::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(SinglyLinkedList<Integer>::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(SinglyLinkedList<Integer>::new);
    }
}

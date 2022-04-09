package containers;

import org.junit.jupiter.api.Test;

public class TestDoublyLinkedList {
    @Test
    public void testConstructor() {
        TestList.testConstructor(DoublyLinkedList::new);
    }

    @Test
    public void testIsEmpty() {
        TestList.testIsEmpty(DoublyLinkedList::new);
    }

    @Test
    public void testSize() {
        TestList.testSize(DoublyLinkedList::new);
    }

    @Test
    public void testClear() {
        TestList.testClear(DoublyLinkedList::new);
    }

    @Test
    public void testContains() {
        TestList.testContains(DoublyLinkedList::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(DoublyLinkedList<Character>::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(DoublyLinkedList<String>::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new DoublyLinkedList<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(DoublyLinkedList<Integer>::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(DoublyLinkedList<Integer>::new);
    }
}

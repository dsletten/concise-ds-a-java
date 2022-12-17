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
    public void testContainsPredicate() {
        TestList.testContainsPredicate(DoublyLinkedList::new);
    }

    @Test
    public void testEquals() {
        TestList.testEquals(DoublyLinkedList::new);
    }

    @Test
    public void testEqualsPredicate() {
        TestList.testEqualsPredicate(DoublyLinkedList::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(DoublyLinkedList::new);
    }

    @Test
    public void testAdd() {
        TestList.testAdd(DoublyLinkedList::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(DoublyLinkedList::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new DoublyLinkedList<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(DoublyLinkedList::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(DoublyLinkedList::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(DoublyLinkedList::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(DoublyLinkedList::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(DoublyLinkedList::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(DoublyLinkedList::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(DoublyLinkedList::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(DoublyLinkedList::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(DoublyLinkedList::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(DoublyLinkedList::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(DoublyLinkedList::new);
    }
}

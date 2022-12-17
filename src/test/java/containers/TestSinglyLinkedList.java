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
    public void testContainsPredicate() {
        TestList.testContainsPredicate(SinglyLinkedList::new);
    }

    @Test
    public void testEquals() {
        TestList.testEquals(SinglyLinkedList::new);
    }

    @Test
    public void testEqualsPredicate() {
        TestList.testEqualsPredicate(SinglyLinkedList::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(SinglyLinkedList::new);
    }

    @Test
    public void testAdd() {
        TestList.testAdd(SinglyLinkedList::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(SinglyLinkedList::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new SinglyLinkedList<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(SinglyLinkedList::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(SinglyLinkedList::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(SinglyLinkedList::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(SinglyLinkedList::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(SinglyLinkedList::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(SinglyLinkedList::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(SinglyLinkedList::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(SinglyLinkedList::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(SinglyLinkedList::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(SinglyLinkedList::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(SinglyLinkedList::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(SinglyLinkedList::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(SinglyLinkedList::new);
    }
}

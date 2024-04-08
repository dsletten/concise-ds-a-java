package containers;

import org.junit.jupiter.api.Test;

public class TestSinglyLinkedListX {
    @Test
    public void testConstructor() {
        TestList.testConstructor(SinglyLinkedListX::new);
    }

    @Test
    public void testIsEmpty() {
        TestList.testIsEmpty(SinglyLinkedListX::new);
    }

    @Test
    public void testSize() {
        TestList.testSize(SinglyLinkedListX::new);
    }

    @Test
    public void testClear() {
        TestList.testClear(SinglyLinkedListX::new);
    }

    @Test
    public void testContains() {
        TestList.testContains(SinglyLinkedListX::new);
    }

    @Test
    public void testContainsPredicate() {
        TestList.testContainsPredicate(SinglyLinkedListX::new);
    }

    @Test
    public void testEquals() {
        TestList.testEquals(SinglyLinkedListX::new);
    }

    @Test
    public void testEqualsPredicate() {
        TestList.testEqualsPredicate(SinglyLinkedListX::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(SinglyLinkedListX::new);
    }

    @Test
    public void testAdd() {
        TestList.testAdd(SinglyLinkedListX::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(SinglyLinkedListX::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new SinglyLinkedListX<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(SinglyLinkedListX::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(SinglyLinkedListX::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(SinglyLinkedListX::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(SinglyLinkedListX::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(SinglyLinkedListX::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(SinglyLinkedListX::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(SinglyLinkedListX::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(SinglyLinkedListX::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(SinglyLinkedListX::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(SinglyLinkedListX::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(SinglyLinkedListX::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(SinglyLinkedListX::new);
    }

    @Test
    public void testReverse() {
        TestList.testReverse(SinglyLinkedListX::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(SinglyLinkedListX::new);
    }
}

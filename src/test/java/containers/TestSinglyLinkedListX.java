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
    public void testEach() {
        TestList.testEach(SinglyLinkedListX<Character>::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(SinglyLinkedListX<String>::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new SinglyLinkedListX<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(SinglyLinkedListX<String>::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(SinglyLinkedListX<Integer>::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(SinglyLinkedListX<Integer>::new);
    }
}

package containers;

import org.junit.jupiter.api.Test;

public class TestHashList {
    @Test
    public void testConstructor() {
        TestList.testConstructor(HashList::new);
    }

    @Test
    public void testIsEmpty() {
        TestList.testIsEmpty(HashList::new);
    }

    @Test
    public void testSize() {
        TestList.testSize(HashList::new);
    }

    @Test
    public void testClear() {
        TestList.testClear(HashList::new);
    }

    @Test
    public void testContains() {
        TestList.testContains(HashList::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(HashList<Character>::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(HashList<String>::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new HashList<Integer>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(HashList<Integer>::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(HashList<Integer>::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(HashList<Integer>::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(HashList<Integer>::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(HashList<Integer>::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(HashList<Integer>::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(HashList<Integer>::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(HashList<Integer>::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(HashList<String>::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(HashList<Integer>::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(HashList<Integer>::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(HashList<Integer>::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(HashList<Integer>::new);
    }
}

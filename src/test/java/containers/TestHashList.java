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
    public void testContainsPredicate() {
        TestList.testContainsPredicate(HashList::new);
    }

    @Test
    public void testEquals() {
        TestList.testEquals(HashList::new);
    }

    @Test
    public void testEqualsPredicate() {
        TestList.testEqualsPredicate(HashList::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(HashList::new);
    }

    @Test
    public void testAdd() {
        TestList.testAdd(HashList::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(HashList::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new HashList<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(HashList::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(HashList::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(HashList::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(HashList::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(HashList::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(HashList::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(HashList::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(HashList::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(HashList::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(HashList::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(HashList::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(HashList::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(HashList::new);
    }
}

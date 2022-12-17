package containers;

import org.junit.jupiter.api.Test;

public class TestArrayList {
    @Test
    public void testConstructor() {
        TestList.testConstructor(ArrayList::new);
    }

    @Test
    public void testIsEmpty() {
        TestList.testIsEmpty(ArrayList::new);
    }

    @Test
    public void testSize() {
        TestList.testSize(ArrayList::new);
    }

    @Test
    public void testClear() {
        TestList.testClear(ArrayList::new);
    }

    @Test
    public void testContains() {
        TestList.testContains(ArrayList::new);
    }

    @Test
    public void testContainsPredicate() {
        TestList.testContainsPredicate(ArrayList::new);
    }

    @Test
    public void testEquals() {
        TestList.testEquals(ArrayList::new);
    }

    @Test
    public void testEqualsPredicate() {
        TestList.testEqualsPredicate(ArrayList::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(ArrayList::new);
    }

    @Test
    public void testAdd() {
        TestList.testAdd(ArrayList::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(ArrayList::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new ArrayList<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(ArrayList::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(ArrayList::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(ArrayList::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(ArrayList::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(ArrayList::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(ArrayList::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(ArrayList::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(ArrayList::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(ArrayList::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(ArrayList::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(ArrayList::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(ArrayList::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(ArrayList::new);
    }
}

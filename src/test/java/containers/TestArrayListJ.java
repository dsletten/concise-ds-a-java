package containers;

import org.junit.jupiter.api.Test;

public class TestArrayListJ {
    @Test
    public void testConstructor() {
        TestList.testConstructor(ArrayListJ::new);
    }

    @Test
    public void testIsEmpty() {
        TestList.testIsEmpty(ArrayListJ::new);
    }

    @Test
    public void testSize() {
        TestList.testSize(ArrayListJ::new);
    }

    @Test
    public void testClear() {
        TestList.testClear(ArrayListJ::new);
    }

    @Test
    public void testContains() {
        TestList.testContains(ArrayListJ::new);
    }

    @Test
    public void testContainsPredicate() {
        TestList.testContainsPredicate(ArrayListJ::new);
    }

    @Test
    public void testEquals() {
        TestList.testEquals(ArrayListJ::new);
    }

    @Test
    public void testEqualsPredicate() {
        TestList.testEqualsPredicate(ArrayListJ::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(ArrayListJ::new);
    }

    @Test
    public void testAdd() {
        TestList.testAdd(ArrayListJ::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(ArrayListJ::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new ArrayListJ<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(ArrayListJ::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(ArrayListJ::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(ArrayListJ::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(ArrayListJ::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(ArrayListJ::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(ArrayListJ::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(ArrayListJ::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(ArrayListJ::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(ArrayListJ::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(ArrayListJ::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(ArrayListJ::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(ArrayListJ::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(ArrayListJ::new);
    }
}

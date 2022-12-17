package containers;

import org.junit.jupiter.api.Test;

public class TestArrayListX {
    @Test
    public void testConstructor() {
        TestList.testConstructor(ArrayListX::new);
    }

    @Test
    public void testIsEmpty() {
        TestList.testIsEmpty(ArrayListX::new);
    }

    @Test
    public void testSize() {
        TestList.testSize(ArrayListX::new);
    }

    @Test
    public void testClear() {
        TestList.testClear(ArrayListX::new);
    }

    @Test
    public void testContains() {
        TestList.testContains(ArrayListX::new);
    }

    @Test
    public void testContainsPredicate() {
        TestList.testContainsPredicate(ArrayListX::new);
    }

    @Test
    public void testEquals() {
        TestList.testEquals(ArrayListX::new);
    }

    @Test
    public void testEqualsPredicate() {
        TestList.testEqualsPredicate(ArrayListX::new);
    }

    @Test
    public void testEach() {
        TestList.testEach(ArrayListX::new);
    }

    @Test
    public void testAdd() {
        TestList.testAdd(ArrayListX::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(ArrayListX::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new ArrayListX<>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(ArrayListX::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(ArrayListX::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(ArrayListX::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(ArrayListX::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(ArrayListX::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(ArrayListX::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(ArrayListX::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(ArrayListX::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(ArrayListX::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(ArrayListX::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(ArrayListX::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(ArrayListX::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(ArrayListX::new);
    }
}

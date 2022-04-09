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
    public void testEach() {
        TestList.testEach(ArrayList<Character>::new);
    }

    @Test
    public void testInsertFillNull() {
        TestList.testInsertFillNull(ArrayList<String>::new);
    }

    @Test
    public void testInsertFillZero() {
        TestList.testInsertFillZero(() -> new ArrayList<Integer>(0));
    }

    @Test
    public void testInsertNegativeIndex() {
        TestList.testInsertNegativeIndex(ArrayList<Integer>::new);
    }

    @Test
    public void testInsertEnd() {
        TestList.testInsertEnd(ArrayList<Integer>::new);
    }

    @Test
    public void testDelete() {
        TestList.testDelete(ArrayList<Integer>::new);
    }

    @Test
    public void testDeleteNegativeIndex() {
        TestList.testDeleteNegativeIndex(ArrayList<Integer>::new);
    }

    @Test
    public void testGet() {
        TestList.testGet(ArrayList<Integer>::new);
    }

    @Test
    public void testGetNegativeIndex() {
        TestList.testGetNegativeIndex(ArrayList<Integer>::new);
    }

    @Test
    public void testSet() {
        TestList.testSet(ArrayList<Integer>::new);
    }

    @Test
    public void testSetNegativeIndex() {
        TestList.testSetNegativeIndex(ArrayList<Integer>::new);
    }

    @Test
    public void testSetOutOfBounds() {
        TestList.testSetOutOfBounds(ArrayList<String>::new);
    }

    @Test
    public void testIndex() {
        TestList.testIndex(ArrayList<Integer>::new);
    }

    @Test
    public void testSlice() {
        TestList.testSlice(ArrayList<Integer>::new);
    }

    @Test
    public void testSliceCornerCases() {
        TestList.testSliceCornerCases(ArrayList<Integer>::new);
    }

    @Test
    public void testTime() {
        TestList.testTime(ArrayList<Integer>::new);
    }
}

package containers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHashStack {
    @Test
    public void testConstructor() {
        TestStack.testConstructor(HashStack::new);
    }

    @Test
    public void testIsEmpty() {
        TestStack.testIsEmpty(HashStack::new);
    }

    @Test
    public void testSize() {
        TestStack.testSize(HashStack::new);
    }

    @Test
    public void testClear() {
        TestStack.testClear(HashStack::new);
    }

    @Test
    public void testPop() {
        TestStack.testPop(HashStack::new);
    }

    @Test
    public void testPeek() {
        TestStack.testPeek(HashStack::new);
    }

    @Test
    public void testWave() {
        TestStack.testWave(HashStack::new);
    }
}

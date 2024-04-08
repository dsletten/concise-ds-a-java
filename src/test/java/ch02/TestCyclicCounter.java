package ch02;

import org.junit.jupiter.api.Test;

public class TestCyclicCounter {
    @Test
    public void testConstructor() {
        TestCounter.testConstructor(n -> new CyclicCounter(n));
    }

    @Test
    public void testAdvance() {
        TestCounter.testAdvance(n -> new CyclicCounter(n));
    }

    @Test
    public void testSet() {
        TestCounter.testSet(n -> new CyclicCounter(n));
    }

    @Test
    public void testReset() {
        TestCounter.testReset(n -> new CyclicCounter(n));
    }

    @Test
    public void testRollover() {
        TestCounter.testRollover(n -> new CyclicCounter(n));
    }
}

package ch02;

import org.junit.jupiter.api.Test;

public class TestPersistentCyclicCounter {
    @Test
    public void testConstructor() {
        TestCounter.testConstructor(n -> PersistentCyclicCounter.makePersistentCyclicCounter(n));
    }

    @Test
    public void testAdvance() {
        TestCounter.testAdvance(n -> PersistentCyclicCounter.makePersistentCyclicCounter(n));
    }

    @Test
    public void testSet() {
        TestCounter.testSet(n -> PersistentCyclicCounter.makePersistentCyclicCounter(n));
    }

    @Test
    public void testReset() {
        TestCounter.testReset(n -> PersistentCyclicCounter.makePersistentCyclicCounter(n));
    }

    @Test
    public void testRollover() {
        TestCounter.testRollover(n -> PersistentCyclicCounter.makePersistentCyclicCounter(n));
    }
}

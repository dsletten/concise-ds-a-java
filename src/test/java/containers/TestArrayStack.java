package containers;

import org.junit.jupiter.api.Test;
//import org.openjdk.jmh.annotations.Benchmark;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;
//import org.openjdk.jmh.infra.Blackhole;
public class TestArrayStack {
    @Test
    public void testConstructor() {
        TestStack.testConstructor(ArrayStack::new);
    }

//    @Benchmark
//    public void measureName(Blackhole bh) {
//
//    }

    @Test
    public void testIsEmpty() {
        TestStack.testIsEmpty(ArrayStack::new);
    }

    @Test
    public void testSize() {
        TestStack.testSize(ArrayStack::new);
    }

    @Test
    public void testClear() {
        TestStack.testClear(ArrayStack::new);
    }

    @Test
    public void testToArray() {
        TestStack.testToArray(ArrayStack::new);
    }

    @Test
    public void testPush() {
        TestStack.testPush(ArrayStack::new);
    }

    @Test
    public void testPeekPop() {
        TestStack.testPeekPop(ArrayStack::new);
    }

    @Test
    public void testWave() {
        TestStack.testWave(ArrayStack::new);
    }
}

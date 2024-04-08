package ch02;

//
//    This interface is weird due to the nature of Java's static typing.
//    For a persistent counter, methods such as `advance()` would have to
//    return a new Counter rather than modifying the receiver. This places
//    constraints on using the same interface for mutable Counters, which
//    might otherwise return the new index. The consistent signature of
//    `advance()` simply necessitates an additional call to `index()` to
//    retrieve that value if needed.
//
public interface Counter {
    int index();

    int modulus();

    default Counter advance() {
        return advance(1);
    }

    Counter advance(int n);

    Counter set(int n);

    default Counter reset() {
        return set(0);
    }
}

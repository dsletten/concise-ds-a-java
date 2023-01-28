package containers.alt.api;

import java.util.function.Function;

public interface Container<E> {
    void clear();
    boolean isEmpty();
    int size();
    Container<E> fill(int count, Function<Integer, E> generator);
    <T> T[] toArray(T[] a);
}

package containers;

import java.util.function.Function;

public interface Container<E> {
    int size();

    boolean isEmpty();

    /*
     *    Implementations must release references to elements to allow GC.
     */
    void clear();

//    public void fill(int count) {
//        fill(count, n -> n);
//    }

    Container<E> fill(int count, Function<Integer, E> generator);

//    public abstract E[] elements();

    <T> T[] toArray(T[] a);
}

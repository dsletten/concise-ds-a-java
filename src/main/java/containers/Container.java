package containers;

import java.util.function.Function;

public abstract class Container<E> {
    public abstract int size();

    public abstract boolean isEmpty();

    /*
     *    Implementations must release references to elements to allow GC.
     */
    public abstract void clear();

//    public void fill(int count) {
//        fill(count, n -> n);
//    }

    abstract Container<E> fill(int count, Function<Integer, E> generator);

//    public abstract E[] elements();

    public abstract <T> T[] toArray(T[] a);
}

package containers;

public abstract class Container<E> {
    public abstract int size();

    public abstract boolean isEmpty();

    /*
     *    Implementations must release references to elements to allow GC.
     */
    public abstract void clear();
}

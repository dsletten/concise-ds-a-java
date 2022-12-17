package containers;

import java.util.function.Function;

public abstract class PersistentContainer<E> {
    public abstract int size();

    public abstract boolean isEmpty();

    public abstract PersistentContainer<E> clear();

    abstract PersistentContainer<E> fill(int count, Function<Integer, E> generator);

    public abstract <T> T[] toArray(T[] a);
}

package containers;

public abstract class PersistentContainer<E> {
    public abstract int size();

    public abstract boolean isEmpty();

    public abstract PersistentContainer<E> clear();
}

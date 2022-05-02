package containers;

public interface MutableCursor<E> extends Cursor<E> {
    int modificationCount();
}

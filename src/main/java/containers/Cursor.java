package containers;

public interface Cursor<E> {
    boolean isDone();

    E current();

    void advance();
}

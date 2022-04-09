package containers;

public abstract class PersistentIterator<E> {
    public abstract boolean isDone();

    public final E current() {
        if ( isDone() ) {
            throw new IllegalStateException("Iteration already finished");
        } else {
            return doCurrent();
        }
    }

    protected abstract E doCurrent();

    public abstract PersistentIterator<E> next();
}

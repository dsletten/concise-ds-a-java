package containers;

public abstract class PersistentQueue<E> extends PersistentDispenser<E> {
    public abstract PersistentQueue<E> enqueue(E elt);

    public final PersistentQueue<E> dequeue() {
        if ( isEmpty() ) {
            throw new IllegalStateException();
        } else {
            return doDequeue();
        }
    }

    protected abstract PersistentQueue<E> doDequeue();

    public final E front() {
        if ( isEmpty() ) {
            throw new IllegalStateException(); // No EmptyQueueException!!
        } else {
            return doFront();
        }
    }

    protected abstract E doFront();
}

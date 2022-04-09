package containers;

public abstract class Queue<E> extends Dispenser<E> {
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        while ( !isEmpty() ) {
            dequeue();
        }
    }

    public abstract void enqueue(E elt);

    public final E dequeue() {
        if ( isEmpty() ) {
            throw new IllegalStateException();
        } else {
            return doDequeue();
        }
    }

    protected abstract E doDequeue();

    public final E front() {
        if ( isEmpty() ) {
            throw new IllegalStateException(); // No EmptyQueueException!!
        } else {
            return doFront();
        }
    }

    protected abstract E doFront();
}

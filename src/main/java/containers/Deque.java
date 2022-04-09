package containers;

public abstract class Deque<E> extends Queue<E> {
    public abstract void enqueueFront(E elt);

    public final E dequeueRear() {
        if ( isEmpty() ) {
            throw new IllegalStateException();
        } else {
            return doDequeueRear();
        }
    }

    protected abstract E doDequeueRear();

    public final E rear() {
        if ( isEmpty() ) {
            throw new IllegalStateException(); // No EmptyQueueException!!
        } else {
            return doRear();
        }
    }

    protected abstract E doRear();
}

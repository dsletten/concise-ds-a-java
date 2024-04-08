package containers;

import java.util.function.Function;

public interface Deque<E> extends Queue<E> {
    void enqueueFront(E elt);

    default E dequeueRear() {
        if ( isEmpty() ) {
            throw new IllegalStateException();
        } else {
            return doDequeueRear();
        }
    }

    E doDequeueRear();

    default E rear() {
        if ( isEmpty() ) {
            throw new IllegalStateException(); // No EmptyQueueException!!
        } else {
            return doRear();
        }
    }

    E doRear();

    @Override
    default Deque<E> fill(int count, Function<Integer, E> generator) {
        return (Deque<E>) Queue.super.fill(count, generator);
    }
}

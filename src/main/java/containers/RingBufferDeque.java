package containers;

public interface RingBufferDeque<E> extends Deque<E>, RingBuffer<E> {
    @Override
    default void enqueueFront(E elt) {
        if ( isFull() ) {
            resize();
        }

        doEnqueue(elt);
    }

    void doEnqueueFront(E elt);
}
package containers;

public interface RingBuffer<E> extends Queue<E> {
    @Override
    default void enqueue(E elt) {
        if ( isFull() ) {
            resize();
        }

        doEnqueue(elt);
    }

    void doEnqueue(E elt);

    boolean isFull();
    default void resize() {
        if (isFull()) {
            doResize();
        } else {
            throw new IllegalStateException("resize() called without full store."); // Test?
        }
    }

    void doResize();
}
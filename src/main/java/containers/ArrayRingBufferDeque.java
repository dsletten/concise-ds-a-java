package containers;

public class ArrayRingBufferDeque<E> extends ArrayRingBuffer<E> implements RingBufferDeque<E> {

    @Override
    public void doEnqueueFront(E elt) {
        retractFront();
        setElement(0, elt);
        incrementCount();
    }

    @Override
    public E doDequeueRear() {
        E discard = rear();
        setElement(size() - 1, null);
        decrementCount();

        return discard;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E doRear() {
        return getElement(size() - 1);
    }
}

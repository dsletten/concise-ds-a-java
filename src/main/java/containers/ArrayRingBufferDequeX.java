package containers;

//
//     - Design exercise in composition
//     - Must violate encapsulation of contained ArrayRingBuffer????
//
public class ArrayRingBufferDequeX<E> implements RingBufferDeque<E> {
    private final ArrayRingBuffer<E> ringBuffer = new ArrayRingBuffer<>();

    @Override
    public int size() {
        return ringBuffer.size();
    }

    @Override
    public boolean isFull() {
        return ringBuffer.isFull();
    }

    @Override
    public void doResize() {
        ringBuffer.resize();
    }

    @Override
    public void doEnqueue(E elt) {
        ringBuffer.enqueue(elt);
    }

    @Override
    public E doDequeue() {
        return ringBuffer.dequeue();
    }

    @Override
    public void doEnqueueFront(E elt) {
        ringBuffer.retractFront();
        ringBuffer.setElement(0, elt);
        ringBuffer.incrementCount();
    }

    @Override
    public E doDequeueRear() {
        E discard = rear();
        ringBuffer.setElement(size() - 1, null);
        ringBuffer.decrementCount();

        return discard;
    }

    @Override
    public E doFront() {
        return ringBuffer.front();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E doRear() {
        return (E) ringBuffer.getElement(size() - 1);
    }
}

package containers;

//
//     - Design exercise in composition
//     - Must violate encapsulation of contained ArrayRingBuffer????
//
public class ArrayRingBufferDequeX<E> extends Deque<E> {
    private final ArrayRingBuffer<E> ringBuffer = new ArrayRingBuffer<>();

    @Override
    public int size() {
        return ringBuffer.size();
    }

    @Override
    public void enqueue(E elt) {
        ringBuffer.enqueue(elt);
    }

    @Override
    protected E doDequeue() {
        return ringBuffer.dequeue();
    }

    @Override
    public void enqueueFront(E elt) {
        if ( ringBuffer.count == ringBuffer.store.length) {
            ringBuffer.resize();
        }

        ringBuffer.front = ringBuffer.offset(-1);
        ringBuffer.store[ringBuffer.offset(0)] = elt;
        ringBuffer.count++;
    }

    @Override
    protected E doDequeueRear() {
        E discard = rear();
        ringBuffer.store[ringBuffer.offset(ringBuffer.count - 1)] = null;
        ringBuffer.count--;

        return discard;
    }

    @Override
    protected E doFront() {
        return ringBuffer.front();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected E doRear() {
        return (E) ringBuffer.store[ringBuffer.offset(ringBuffer.count-1)];
    }
}

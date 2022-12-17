package containers;

//
//    This is not really tied to RingBuffer!!
//    Code duplication due to single inheritance.
//
public class ArrayRingBufferDeque<E> extends Deque<E> {
    private static final int CAPACITY = 20;

    private Object[] store = new Object[CAPACITY];
    private int front = 0;
    private int count = 0;

    @Override
    public int size() {
        return count;
    }

    private int offset(int i) {
        return Utilities.mod(i + front, store.length);
    }

    @Override
    public void enqueue(E elt) {
        if (count == store.length) {
            resize();
        }

        store[offset(count)] = elt;
        count++;
    }

    @Override
    protected E doDequeue() {
        E discard = front();
        store[front] = null;
        front = offset(1);
        count--;

        return discard;
    }

    @Override
    public void enqueueFront(E elt) {
        if (count == store.length) {
            resize();
        }

        front = offset(-1);
        store[offset(0)] = elt;
        count++;
    }

    @Override
    protected E doDequeueRear() {
        E discard = rear();
        store[offset(count - 1)] = null;
        count--;

        return discard;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected E doFront() {
        return (E) store[front];
    }

    @Override
    @SuppressWarnings("unchecked")
    protected E doRear() {
        return (E) store[offset(count-1)];
    }

    void resize() {
        if (count == store.length) {
            Object[] newStore = new Object[store.length * 2];
            for (int i = 0; i < count; i++) {
                newStore[i] = store[offset(i)];
            }

            store = newStore;
            front = 0;
        } else {
            throw new IllegalStateException("resize() called without full store."); // Test?
        }
    }
}

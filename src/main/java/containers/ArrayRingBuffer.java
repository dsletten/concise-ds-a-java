package containers;

/*
 *    This is the traditional `ring buffer`.
 */
public class ArrayRingBuffer<E> implements RingBuffer<E> {
    private static final int CAPACITY = 20;

    private Object[] store = new Object[CAPACITY];
    private int front = 0;
    private int count = 0;

    protected E getElement(int i) {
        return (E) store[offset(i)];
    }
    protected void setElement(int i, E obj) {
        store[offset(i)] = obj;
    }

    protected void advanceFront() {
        front = offset(1);
    }

    protected void retractFront() {
        front = offset(-1);
    }
    protected void incrementCount() {
        count++;
    }

    protected void decrementCount() {
        count--;
    }

    @Override
    public int size() {
        return count;
    }

    private int offset(int i) {
        return Utilities.mod(i + front, store.length);
    }

    @Override
    public void doEnqueue(E elt) {
        store[offset(count)] = elt;
        incrementCount();
    }

    @Override
    public E doDequeue() {
        E discard = front();
        store[front] = null;
        advanceFront();
        decrementCount();

        return discard;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E doFront() {
        return (E) store[front];
    }


    @Override
    public boolean isFull() {
        return count == store.length;
    }

    @Override
    public void doResize() {
        Object[] newStore = new Object[store.length * 2];
        for (int i = 0; i < count; i++) {
            newStore[i] = store[offset(i)];
        }

        store = newStore;
        front = 0;
    }
}

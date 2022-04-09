package containers;

/*
 *    This is the traditional `ring buffer`.
 */
public class ArrayQueue<E> extends Queue<E> {
    private static final int CAPACITY = 20;
    @SuppressWarnings("unchecked")
    private E[] store = (E[]) new Object[CAPACITY];
    private int front = 0;
    private int count = 0;

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    //    This is not good enough. Must release the references to elements. Use superclass method.
//    @Override
//    public void clear() {
//        front = 0;
//        count = 0;
//    }

    @Override
    public void enqueue(E elt) {
        if ( count == store.length ) {
            resize();
        }
        store[(front + count) % store.length] = elt;
        count++;
    }

    @Override
    protected E doDequeue() {
        E discard = front();
        store[front] = null;
        front = (front + 1) % store.length;
        count--;

        return discard;
    }

    @Override
    protected E doFront() {
        return store[front];
    }

    private void resize() {
        @SuppressWarnings("unchecked")
        E newStore[] = (E[]) new Object[store.length * 2];
        for (int i = 0; i < count; i++) {
            newStore[i] = store[(front + i) % store.length];
        }

        store = newStore;
        front = 0;
    }
}

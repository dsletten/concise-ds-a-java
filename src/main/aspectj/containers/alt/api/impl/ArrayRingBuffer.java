package containers.alt.api.impl;

import containers.Utilities;
import containers.alt.api.RingBuffer;

/*
 *    This is the traditional `ring buffer`.
 */
public class ArrayRingBuffer<E> implements RingBuffer<E> {
    private static final int CAPACITY = 20;

    //    These are exposed for the sake of ArrayRingBufferDequeX!
    protected Object[] store = new Object[CAPACITY];
    protected int front = 0;
    protected int count = 0;

    @Override
    public int size() {
        return count;
    }

    protected int offset(int i) {
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
    public E dequeue() {
        E discard = front();
        store[front] = null;
        front = offset(1);
        count--;

        return discard;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E front() {
        return (E) store[front];
    }

    public void resize() {
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

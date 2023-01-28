package containers.alt.api.impl;

import containers.Utilities;
import containers.alt.api.Deque;
import containers.alt.api.RingBuffer;

public class ArrayRingBufferDeque<E> implements RingBuffer<E>, Deque<E> {
    private final ArrayRingBuffer<E> arrayRingBufferDelegate = new ArrayRingBuffer<>();

    @Override
    public int size() {
        return arrayRingBufferDelegate.size();
    }

    private int offset(int i) {
        return Utilities.mod(i + arrayRingBufferDelegate.front, arrayRingBufferDelegate.store.length);
    }

    @Override
    public void enqueue(E elt) {
        arrayRingBufferDelegate.enqueue(elt);
    }

    @Override
    public E dequeue() {
        return arrayRingBufferDelegate.dequeue();
    }

    @Override
    public void enqueueFront(E elt) {
        if (arrayRingBufferDelegate.count == arrayRingBufferDelegate.store.length) {
            resize();
        }

        arrayRingBufferDelegate.front = offset(-1);
        arrayRingBufferDelegate.store[offset(0)] = elt;
        arrayRingBufferDelegate.count++;
    }

    @Override
    public E dequeueRear() {
        E discard = rear();
        arrayRingBufferDelegate.store[offset(arrayRingBufferDelegate.count - 1)] = null;
        arrayRingBufferDelegate.count--;

        return discard;
    }

    @Override
    public E front() {
        return arrayRingBufferDelegate.front();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E rear() {
        return (E) arrayRingBufferDelegate.store[offset(arrayRingBufferDelegate.count-1)];
    }

    @Override
    public void resize() {
        arrayRingBufferDelegate.resize();
    }
}

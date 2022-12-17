package containers;

import java.util.Arrays;
import java.util.function.Function;

public abstract class Queue<E> extends Dispenser<E> {
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        while ( !isEmpty() ) {
            dequeue();
        }
    }

    public abstract void enqueue(E elt);

    public final E dequeue() {
        if ( isEmpty() ) {
            throw new IllegalStateException();
        } else {
            return doDequeue();
        }
    }

    protected abstract E doDequeue();

    public final E front() {
        if ( isEmpty() ) {
            throw new IllegalStateException(); // No EmptyQueueException!!
        } else {
            return doFront();
        }
    }

    protected abstract E doFront();

    @Override
    Queue<E> fill(int count, Function<Integer, E> generator) {
        for (int i = 1; i <= count; i++) {
            enqueue(generator.apply(i));
        }

        return this; // Must cast for Deque?!
    }

    @Override
//    public E[] elements() {
    public <T> T[] toArray(T[] a) {
//        E[] elements = (E[]) new Object[size()];
        Object[] elements = new Object[size()];
        int i = 0;
        int count = size();

        while ( !isEmpty() ) {
            elements[i++] = dequeue();
        }

        //noinspection unchecked
        return (T[]) Arrays.copyOf(elements, count, a.getClass());
    }
}

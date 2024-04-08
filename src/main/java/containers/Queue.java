package containers;

import java.util.Arrays;
import java.util.function.Function;

public interface Queue<E> extends Dispenser<E> {
    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    default void clear() {
        while ( !isEmpty() ) {
            dequeue();
        }
    }

    void enqueue(E elt);

    default E dequeue() {
        if ( isEmpty() ) {
            throw new IllegalStateException();
        } else {
            return doDequeue();
        }
    }

    E doDequeue();

    default E front() {
        if ( isEmpty() ) {
            throw new IllegalStateException(); // No EmptyQueueException!!
        } else {
            return doFront();
        }
    }

    E doFront();

    @Override
    default Queue<E> fill(int count, Function<Integer, E> generator) {
        for (int i = 1; i <= count; i++) {
            enqueue(generator.apply(i));
        }

        return this; // Must cast for Deque?!
    }

    @Override
//    public E[] elements() {
    default <T> T[] toArray(T[] a) {
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

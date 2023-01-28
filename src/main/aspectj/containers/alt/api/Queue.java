package containers.alt.api;

import java.util.Arrays;
import java.util.function.Function;

public interface Queue<E> extends Dispenser<E> {
    void enqueue(E elt);
    E dequeue();
    E front();

    @Override
    default boolean isEmpty(){
        return size() == 0;
    }

    @Override
    default void clear() {
        while ( !isEmpty() ) {
            dequeue();
        }
    }

    @Override
    default Queue<E> fill(int count, Function<Integer, E> generator) {
        for (int i = 1; i <= count; i++) {
            enqueue(generator.apply(i));
        }

        return this; // Must cast for Deque?!
    }

    @Override
    default <T> T[] toArray(T[] a) {
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

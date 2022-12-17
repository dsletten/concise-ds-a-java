package containers;

import java.util.Arrays;
import java.util.function.Function;

public abstract class PersistentQueue<E> extends PersistentDispenser<E> {
    public abstract PersistentQueue<E> enqueue(E elt);

    public final PersistentQueue<E> dequeue() {
        if ( isEmpty() ) {
            throw new IllegalStateException();
        } else {
            return doDequeue();
        }
    }

    protected abstract PersistentQueue<E> doDequeue();

    public final E front() {
        if ( isEmpty() ) {
            throw new IllegalStateException(); // No EmptyQueueException!!
        } else {
            return doFront();
        }
    }

    protected abstract E doFront();

    @Override
    PersistentQueue<E> fill(int count, Function<Integer, E> generator) {
        PersistentQueue<E> queue = this;

        for (int i = 1; i <= count; i++) {
            queue = queue.enqueue(generator.apply(i));
        }

        return queue;
    }

    public <T> T[] toArray(T[] a) {
        PersistentQueue<E> queue = this;
        //noinspection unchecked
        E[] elements = (E[]) new Object[size()];
        int i = 0;
        int count = size();

        while ( !queue.isEmpty() ) {
            elements[i++] = queue.front();
            queue = queue.dequeue();
        }

        //noinspection unchecked
        return (T[]) Arrays.copyOf(elements, count, a.getClass());
    }
}

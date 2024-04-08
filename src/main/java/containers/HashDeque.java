package containers;

import java.util.HashMap;
import java.util.Map;

public class HashDeque<E> implements Deque<E> {
    private Map<Integer, E> store = new HashMap<>();
    private int front = 0;
    private int rear = 0;

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public void clear() {
        store.clear();
        front = rear = 0;
    }

    @Override
    public void enqueue(E elt) {
        if ( !isEmpty() ) {
            rear++;
        }

        store.put(rear, elt);
    }

    @Override
    public void enqueueFront(E elt) {
        if ( !isEmpty() ) {
            front--;
        }

        store.put(front, elt);
    }

    @Override
    public E doDequeue() {
        E discard = store.remove(front);

        if ( !isEmpty() ) {
            front++;
        }

        return discard;
    }

    @Override
    public E doDequeueRear() {
        E discard = store.remove(rear);

        if ( !isEmpty() ) {
            rear--;
        }

        return discard;
    }

    @Override
    public E doFront() {
        return store.get(front);
    }

    @Override
    public E doRear() {
        return store.get(rear);
    }
}

package containers;

import java.util.ArrayList;
import java.util.List;

public class ArrayListQueue<E> extends Queue<E> {
    private List<E> store = new ArrayList<>();

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public void clear() {
        store.clear();
    }

    @Override
    public void enqueue(E elt) {
        store.add(elt);
    }

    @Override
    protected E doDequeue() {
        return store.remove(0);
    }

    @Override
    protected E doFront() {
        return store.get(0);
    }
}

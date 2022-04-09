package containers;

import java.util.HashMap;
import java.util.Map;

public class HashStack<E> extends Stack<E> {
    private Map<Integer, E> store = new HashMap<>();

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
    public void push(E obj) {
        store.put(store.size()+1, obj);
    }

    @Override
    protected E doPop() {
        return store.remove(store.size());
    }

    @Override
    protected E doPeek() {
        return store.get(store.size());
    }
}

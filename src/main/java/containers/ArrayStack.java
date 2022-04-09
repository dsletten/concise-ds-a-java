package containers;

import java.util.ArrayList;
import java.util.List;

public class ArrayStack<E> extends Stack<E> {
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
    public void push(E obj) {
        store.add(obj);
    }

    @Override
    protected E doPop() {
        return store.remove(store.size() - 1);
    }

    @Override
    protected E doPeek() {
        return store.get(store.size() - 1);
    }
}

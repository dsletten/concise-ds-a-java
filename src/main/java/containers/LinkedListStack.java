package containers;

public class LinkedListStack<E> extends Stack<E> {
    private final List<E> list = new SinglyLinkedListX<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void push(E obj) {
        list.insert(0, obj);
    }

    @Override
    protected E doPop() {
        return list.delete(0);
    }

    @Override
    protected E doPeek() {
        return list.get(0);
    }
}

package containers;

public class LinkedListQueue<E> extends Queue<E> {
    private List<E> list = new SinglyLinkedListX<>();

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
    public void enqueue(E elt) {
        list.add(elt);
    }

    @Override
    protected E doDequeue() {
        return list.delete(0);
    }

    @Override
    protected E doFront() {
        return list.get(0);
    }
}

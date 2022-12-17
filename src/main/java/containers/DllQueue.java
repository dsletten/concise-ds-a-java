package containers;

public class DllQueue<E> extends Queue<E> {
    private final List<E> list = new DoublyLinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void enqueue(E elt) {
        //noinspection unchecked
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

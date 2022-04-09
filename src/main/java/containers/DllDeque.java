package containers;

public class DllDeque<E> extends Deque<E> {
    private List<E> list = new DoublyLinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void enqueue(E elt) {
        list.add(elt);
    }

    @Override
    public void enqueueFront(E elt) {
        list.insert(0, elt);
    }

    @Override
    protected E doDequeue() {
        return list.delete(0);
    }

    @Override
    protected E doDequeueRear() {
        return list.delete(-1);
    }

    @Override
    protected E doFront() {
        return list.get(0);
    }

    @Override
    protected E doRear() {
        return list.get(-1);
    }
}

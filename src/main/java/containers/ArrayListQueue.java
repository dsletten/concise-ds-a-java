package containers;

public class ArrayListQueue<E> implements Queue<E> {
    private final List<E> list = new ArrayListX<>();

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
    public E doDequeue() {
        return list.delete(0);
    }

    @Override
    public E doFront() {
        return list.get(0);
    }
}

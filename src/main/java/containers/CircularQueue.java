package containers;

//    See ch. 6 exercise 5
public class CircularQueue<E> extends Queue<E> {
    private Node<E> index = null;
    private int count = 0;

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        index = null;
        count = 0;
    }

    @Override
    public void enqueue(E elt) {
        Node<E> node = new Node<>(elt, null);

        if ( index == null ) {
            index = node;
            index.setRest(node);
        } else {
            node.setRest(index.rest());
            index.setRest(node);
            index = node;
        }

        count++;
    }

    @Override
    protected E doDequeue() {
        E discard = front();

        if ( index == index.rest() ) {
            index = null;
        } else {
            index.setRest(index.rest().rest());
        }

        count--;

        return discard;
    }

    @Override
    protected E doFront() {
        return index.rest().first();
    }
}

package containers;

public class LinkedQueue<E> implements Queue<E> {
    protected Node<E> front = null;  // These fields are protected to facilitate RecyclingQueue, LinkedRingBuffer...Ugh
    protected Node<E> rear = null;
    protected int count = 0;

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        front = rear = null;
        count = 0;
    }

    @Override
    public void enqueue(E elt) {
        Node<E> node = new Node<>(elt, null);

        if ( isEmpty() ) {
            if ( rear != null ) {
                throw new IllegalStateException("Queue is in illegal state.");
            }
            rear = front = node;
        } else {
            rear = rear.setRest(node);
        }
        count++;
    }

    @Override
    public E doDequeue() {
        E discard = front();
        front = front.rest();

        if ( front == null ) {
            rear = front;
        }

        count--;

        return discard;
    }

    @Override
    public E doFront() {
        return front.first();
    }
}

package containers;

public class LinkedQueue<E> extends Queue<E> {
    protected Node<E> front = null;  // These fields are protected to facilitate RecyclingQueue/RingBuffer...Ugh
    protected Node<E> rear = null;
    protected int count = 0;

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        front = rear = null;
        count = 0;
    }

    @Override
    public void enqueue(E elt) {
        if ( front == null ) {
            if ( rear != null ) {
                throw new IllegalStateException("Queue is in illegal state.");
            }
            rear = front = new Node<>(elt, null);
        } else {
            rear = rear.setRest(new Node<>(elt, null));
        }
        count++;
    }

    @Override
    protected E doDequeue() {
        E discard = front();
        front = front.rest();

        if ( front == null ) {
            rear = front;
        }

        count--;

        return discard;
    }

    @Override
    protected E doFront() {
        return front.first();
    }
}

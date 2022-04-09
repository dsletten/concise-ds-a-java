package containers;

/*
 *    This is not really a `ring buffer`. It is just a variant of CircularQueue...
 */
public class RingBuffer<E> extends LinkedQueue<E> {
    private static final int CAPACITY = 20;

    public RingBuffer() {
        front = Node.makeList(CAPACITY);
        rear = front;
        front.last().setRest(front);
    }

    @Override
    public void clear() {
        while ( !isEmpty() ) {
            dequeue();
        }
    }

    @Override
    public void enqueue(E elt) {
        rear.setFirst(elt);

        if ( rear.rest() == front ) {
            Node<E> more = Node.makeList(count + 1);
            more.last().setRest(front);
            rear.setRest(more);
        }

        rear = rear.rest();
        count++;
    }

    @Override
    protected E doDequeue() {
        E discard = front();

        front.setFirst(null);
        front = front.rest();
        count--;

        return discard;
    }
}

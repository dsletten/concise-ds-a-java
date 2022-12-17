package containers;

/*
 *    This is not really a `ring buffer`. It is just a variant of CircularQueue...
 */
public class LinkedRingBuffer<E> extends RingBuffer<E> {
    private static final int CAPACITY = 20;

    protected Node<E> front;
    protected Node<E> rear;
    protected int count = 0;

    public LinkedRingBuffer() {
        front = Node.makeList(CAPACITY);
        rear = front;
        front.last().setRest(front);
    }

    @Override
    public int size() {
        return count;
    }

    //    @Override
//    public void clear() {
//        while ( !isEmpty() ) {
//            dequeue();
//        }
//    }

    @Override
    void resize() {
        if ( rear.rest() == front ) {
            Node<E> more = Node.makeList(count + 1);
            more.last().setRest(front);
            rear.setRest(more);
        } else {
            throw new IllegalStateException("resize() called without full store."); // Test?
        }
    }

    @Override
    public void enqueue(E elt) {
        if ( rear.rest() == front ) {
            resize();
        }

        rear.setFirst(elt);
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

    @Override
    protected E doFront() {
        return front.first();
    }
}

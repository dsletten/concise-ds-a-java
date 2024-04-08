package containers;

public class LinkedRingBuffer<E> extends LinkedQueue<E> implements RingBuffer<E> {
    private static final int CAPACITY = 20;

    public LinkedRingBuffer() {
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
    public boolean isFull() {
        return rear.rest() == front;
    }

    @Override
    public void doResize() {
        Node<E> more = Node.makeList(count + 1);
        more.last().setRest(front);
        rear.setRest(more);
    }

    @Override
    public void doEnqueue(E elt) {
        rear.setFirst(elt);
        rear = rear.rest();
        count++;
    }

    @Override
    public E doDequeue() {
        E discard = front();

        front.setFirst(null);
        front = front.rest();
        count--;

        return discard;
    }
}

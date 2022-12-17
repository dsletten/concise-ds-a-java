package containers;

/*
 *    This class is kind of pointless. It is a trivial (and less efficient) variant of LinkedRingBuffer.
 */
public class RecyclingQueue<E> extends LinkedQueue<E> {
    private static final int CAPACITY = 20;
    protected Node<E> ass;

    public RecyclingQueue() {
        front = Node.makeList(CAPACITY);
        rear = front;
        ass = front.last();
    }

    @Override
    public void clear() {
        while ( !isEmpty() ) {
            dequeue();
        }
    }

    private void resize() {
        if ( rear == ass ) {
            Node<E> more = Node.makeList(count + 1);
            ass.setRest(more);
            ass = more.last();
        } else {
            throw new IllegalStateException("resize() called without full store."); // Test?
        }
    }

    @Override
    public void enqueue(E elt) {
        if ( rear == ass ) {
            resize();
        }

        rear.setFirst(elt);
        rear = rear.rest();
        count++;
    }

    @Override
    protected E doDequeue() { // 3 extra assignments compared to LinkedRingBuffer!
        E discard = front();

        ass.setRest(front);  // X
        ass = front;         // X
        front = front.rest();
        ass.setFirst(null);
        ass.setRest(null);   // X
        count--;

        return discard;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new RecyclingQueue<>();

        for (int i = 1; i <= 30; i++) {
            queue.enqueue(i);
        }

        System.out.println(queue.size());

        while ( !queue.isEmpty() ) {
            System.out.println(queue.front());
            queue.dequeue();
        }

        //noinspection CastCanBeRemovedNarrowingVariableType
        Node<Integer> node = ((RecyclingQueue<Integer>) queue).front;
        while ( node != null ) {
            if ( node.first() != null ) {
                System.out.println("Whaa? " + node.first());
            }
            node = node.rest();
        }
    }
}

package containers;

public class PersistentLinkedQueue<E> extends PersistentQueue<E> {
    private Node<E> front = null;
    private Node<E> rear = null;
    private int count = 0;

    private static <E> PersistentQueue<E> initializeQueue(Node<E> front, Node<E> rear, int count) {
        PersistentLinkedQueue<E> newQueue = new PersistentLinkedQueue<>();
        newQueue.front = front;
        newQueue.rear = rear;
        newQueue.count = count;

        return newQueue;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public PersistentQueue<E> clear() {
        return new PersistentLinkedQueue<>();
    }

    @Override
    public PersistentQueue<E> enqueue(E elt) {
        if ( isEmpty() ) {
            return initializeQueue(new Node<>(elt, front), null, 1);
        } else {
            return initializeQueue(front, new Node<>(elt, rear), count + 1);
        }
    }

    @Override
    protected PersistentQueue<E> doDequeue() {
        if ( front.rest() == null ) {
            return initializeQueue(Node.reverse(rear), null, count - 1);
        } else {
            return initializeQueue(front.rest(), rear, count - 1);
        }
    }

    @Override
    protected E doFront() {
        return front.first();
    }

    public static void main(String[] args) {
        PersistentQueue<Integer> queue = new PersistentLinkedQueue<>();

        System.out.println(queue.enqueue(8).enqueue(9).enqueue(-2).front());
        System.out.println(queue.enqueue(8).enqueue(9).enqueue(-2).dequeue().front());
        System.out.println(queue.enqueue(8).enqueue(9).enqueue(-2).dequeue().dequeue().front());
    }
}

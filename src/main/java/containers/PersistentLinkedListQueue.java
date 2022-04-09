package containers;

public class PersistentLinkedListQueue<E> extends PersistentQueue<E> {
    private static final PersistentList<Object> EMPTY = new PersistentLinkedList<>();

    private PersistentList<Object> list = EMPTY;

    private static <E> PersistentQueue<E> initializeQueue(PersistentList<Object> list) {
        PersistentLinkedListQueue<E> newQueue = new PersistentLinkedListQueue<>();
        newQueue.list = list;

        return newQueue;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public PersistentQueue<E> clear() {
        return new PersistentLinkedListQueue<>();
    }

    @Override
    public PersistentQueue<E> enqueue(E elt) {
        return initializeQueue(list.add(elt));
    }

    @Override
    protected PersistentQueue<E> doDequeue() {
        return initializeQueue(list.delete(0));
    }

    @Override
    protected E doFront() {
        return (E) list.get(0);
    }

    public static void main(String[] args) {
        PersistentQueue<Integer> queue = new PersistentLinkedListQueue<>();

        System.out.println(queue.enqueue(8).enqueue(9).enqueue(-2).front());
        System.out.println(queue.enqueue(8).enqueue(9).enqueue(-2).dequeue().front());
        System.out.println(queue.enqueue(8).enqueue(9).enqueue(-2).dequeue().dequeue().front());
    }
}

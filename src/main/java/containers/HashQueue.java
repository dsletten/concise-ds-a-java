package containers;

import java.util.HashMap;
import java.util.Map;

public class HashQueue<E> extends Queue<E> {
    private Map<Integer, E> store = new HashMap<>();
    private int front = 0;
    private int rear = 0;

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public void clear() {
        store.clear();
        front = rear = 0;
    }

    @Override
    public void enqueue(E elt) {
        store.put(rear, elt);
        rear++;
    }

    @Override
    protected E doDequeue() {
        E discard = store.remove(front);
        front++;

        return  discard;
    }

    @Override
    protected E doFront() {
        return store.get(front);
    }

    public static void main(String[] args) {
        Queue<Integer> q = new HashQueue<>();
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }

        System.out.println(q.size());
        System.out.println(q.front());

        while ( !q.isEmpty() ) {
            System.out.println(q.dequeue());
        }

        for (int i = 0; i < 41; i++) {
            q.enqueue(i);
        }

        System.out.println(q.size());
        System.out.println(q.front());

        while ( !q.isEmpty() ) {
            System.out.println(q.dequeue());
        }
    }
}

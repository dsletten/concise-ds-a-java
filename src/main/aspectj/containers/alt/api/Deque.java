package containers.alt.api;

public interface Deque<E> extends Queue<E> {
    void enqueueFront(E elt);
    E dequeueRear();
    E rear();
}

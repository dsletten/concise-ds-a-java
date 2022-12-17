package containers;

public abstract class RingBuffer<E> extends Queue<E> {
    abstract void resize();
}

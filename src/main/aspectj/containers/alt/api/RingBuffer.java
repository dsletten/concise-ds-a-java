package containers.alt.api;

public interface RingBuffer<E> extends Queue<E> {
    void resize();
}

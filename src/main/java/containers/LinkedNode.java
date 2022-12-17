package containers;

public interface LinkedNode<E> {
    void spliceBefore(E obj);

    void spliceAfter(E obj);

    E exciseNode();

    E exciseChild();
}

package containers;

public abstract class LinkedList<E> extends List<E> {
    protected LinkedList() {
        super();
    }

    protected LinkedList(E fillElt) {
        super(fillElt);
    }

    public final void insertBefore(Node<E> node, E obj) {
        if ( node == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            doInsertBefore(node, obj);
        }
    }

    protected abstract void doInsertBefore(Node<E> node, E obj);

    public final void insertAfter(Node<E> node, E obj) {
        if ( node == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            doInsertAfter(node, obj);
        }
    }

    protected abstract void doInsertAfter(Node<E> node, E obj);

    public final E deleteNode(Node<E> doomed) {
        if ( doomed == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            return doDeleteNode(doomed);
        }
    }

    protected abstract E doDeleteNode(Node<E> doomed);

    public final E deleteChild(Node<E> parent) {
        if ( parent == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            return doDeleteChild(parent);
        }
    }

    protected abstract E doDeleteChild(Node<E> parent);
}

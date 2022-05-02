package containers;

public abstract class MutableLinkedList<E> extends MutableList<E> {
    protected MutableLinkedList() {
        super();
    }

    protected MutableLinkedList(E fillElt) {
        super(fillElt);
    }

    public final void insertBefore(Node<E> node, E obj) {
        if ( node == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            doInsertBefore(node, obj);
            countModification();
        }
    }

    protected abstract void doInsertBefore(Node<E> node, E obj);

    public final void insertAfter(Node<E> node, E obj) {
        if ( node == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            doInsertAfter(node, obj);
            countModification();
        }
    }

    protected abstract void doInsertAfter(Node<E> node, E obj);

    public final E deleteNode(Node<E> node) {
        if ( node == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            E doomed = doDeleteNode(node);
            countModification();

            return doomed;
        }
    }

    protected abstract E doDeleteNode(Node<E> doomed);

    public final E deleteChild(Node<E> parent) {
        if ( parent == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            countModification();
            return doDeleteChild(parent);
        }
    }

    protected abstract E doDeleteChild(Node<E> parent);
}

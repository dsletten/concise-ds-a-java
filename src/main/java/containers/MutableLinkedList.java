package containers;

public abstract class MutableLinkedList<E> extends MutableList<E> {
    protected MutableLinkedList() {
        super();
    }

    protected MutableLinkedList(E fillElt) {
        super(fillElt);
    }

    public final void insertBefore(LinkedNode<E> node, E obj) {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty");
        } else if ( node == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            doInsertBefore(node, obj);
            countModification();
        }
    }

    protected abstract void doInsertBefore(LinkedNode<E> node, E obj);

    public final void insertAfter(LinkedNode<E> node, E obj) {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty");
        } else if ( node == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            doInsertAfter(node, obj);
            countModification();
        }
    }

    protected abstract void doInsertAfter(LinkedNode<E> node, E obj);

    public final E deleteNode(LinkedNode<E> node) {
        if ( node == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            E doomed = doDeleteNode(node);
            countModification();

            return doomed;
        }
    }

    protected abstract E doDeleteNode(LinkedNode<E> doomed);

    public final E deleteChild(LinkedNode<E> parent) {
        if ( parent == null ) {
            throw new IllegalArgumentException("Invalid node");
        } else {
            countModification();
            return doDeleteChild(parent);
        }
    }

    protected abstract E doDeleteChild(LinkedNode<E> parent);
}

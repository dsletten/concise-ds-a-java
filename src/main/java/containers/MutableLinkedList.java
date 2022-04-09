package containers;

public abstract class MutableLinkedList<E> extends LinkedList<E> {
    private int modificationCount = 0;

    protected MutableLinkedList() {
        super();
    }

    protected MutableLinkedList(E fillElt) {
        super(fillElt);
    }

    @Override
    public final void clear() {
        countModification();
        doClear();
    }

    protected abstract void doClear();

    @Override
    @SuppressWarnings("unchecked")
    public final void add(E... objs) {
        if ( objs.length > 0 ) {
            countModification();
            doAdd(objs);
        }
    }

    @SuppressWarnings("unchecked")
    protected abstract void doAdd(E... objs);

    @Override
    protected final void doInsert(int i, E obj) {
        countModification();
        doDoInsert(i, obj);
    }

    protected abstract void doDoInsert(int i, E obj);

    @Override
    protected final E doDelete(int i) {
        countModification();
        return doDoDelete(i);
    }

    protected abstract E doDoDelete(int i);

    @Override
    protected final void doInsertBefore(Node<E> node, E obj) {
        countModification();
        doDoInsertBefore(node, obj);
    }

    protected abstract void doDoInsertBefore(Node<E> node, E obj);

    @Override
    protected final void doInsertAfter(Node<E> node, E obj) {
        countModification();
        doDoInsertAfter(node, obj);
    }

    protected abstract void doDoInsertAfter(Node<E> node, E obj);

    @Override
    protected final E doDeleteNode(Node<E> doomed) {
        countModification();
        return doDoDeleteNode(doomed);
    }

    protected abstract E doDoDeleteNode(Node<E> doomed);

    @Override
    protected final E doDeleteChild(Node<E> parent) {
        countModification();
        return doDoDeleteChild(parent);
    }

    protected abstract E doDoDeleteChild(Node<E> parent);

    private void countModification() {
        modificationCount++;
    }

    protected int getModificationCount() {
        return modificationCount;
    }
}

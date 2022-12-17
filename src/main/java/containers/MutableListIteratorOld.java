package containers;

//    This should be MutableCollectionIterator!!
//    Need MutableCollection interface?!
@SuppressWarnings("ALL")
public abstract class MutableListIteratorOld<E> extends IteratorOld<E> {
    private MutableList<E> list;
    private int expectedModificationCount;

    protected MutableListIteratorOld(MutableList<E> list) {
        this.list = list;
        this.expectedModificationCount = list.modificationCount;
    }

    private boolean comodified() {
        return expectedModificationCount != ((MutableList<E>) list).modificationCount;
    }

    @Override
    public final boolean isDone() {
        checkComodification();

        return doIsDone();
    }

    protected abstract boolean doIsDone();

    @Override
    protected final E doCurrent() {
        checkComodification();
        return doDoCurrent();
    }

    protected abstract E doDoCurrent();

    @Override
    public final void next() {
        checkComodification();

        doNext();
    }

    protected abstract void doNext();

    private void checkComodification() {
        if ( comodified() ) {
            throw new IllegalStateException("List iterator invalid due to structural modification of collection.");
        }
    }
}

package containers;

public abstract class MutableListListIterator<E> extends ListIterator<E> {
    private int expectedModificationCount;

    public MutableListListIterator(MutableList<E> list) {
        super(list);
        expectedModificationCount = list.getModificationCount();
    }

    private void countModification() {
        expectedModificationCount++;
    }

    private boolean comodified() {
        return expectedModificationCount != ((MutableList<E>) list).getModificationCount();
    }

    @Override
    protected final E doCurrent() {
        checkComodification();

        return doDoCurrent();
    }

    protected abstract E doDoCurrent();

    @Override
    protected final int doCurrentIndex() {
        checkComodification();

        return doDoCurrentIndex();
    }

    protected abstract int doDoCurrentIndex();

    @Override
    protected final void doSetCurrent(E obj) {
        checkComodification();

        doDoSetCurrent(obj);
    }

    protected abstract void doDoSetCurrent(E obj);

    @Override
    public final boolean hasNext() {
//        if ( comodified() ) {
//            comodificationException();
//        } else {
//            return doHasNext();
//        }

        checkComodification();

        return doHasNext();
    }

    protected abstract boolean doHasNext();

    @Override
    public final boolean hasPrevious() {
        checkComodification();

        return doHasPrevious();
    }

    protected abstract boolean doHasPrevious();

    @Override
    protected final E doNext() {
        checkComodification();

        return doDoNext();
    }

    protected abstract E doDoNext();

    @Override
    protected final E doPrevious() {
        checkComodification();

        return doDoPrevious();
    }

    protected abstract E doDoPrevious();

    @Override
    protected final E doRemove() {
        checkComodification();

        E doomed = doDoRemove();

        countModification();

        return doomed;
    }

    protected abstract E doDoRemove();

    @Override
    public final void addBefore(E obj) {
        countModification();

        doAddBefore(obj);

        countModification();
    }

    protected abstract void doAddBefore(E obj);

    @Override
    public final void addAfter(E obj) {
        countModification();

        doAddAfter(obj);

        countModification();
    }

    protected abstract void doAddAfter(E obj);

    private void checkComodification() {
        if ( comodified() ) {
            throw new IllegalStateException("List iterator invalid due to structural modification of collection.");
        }
    }
}

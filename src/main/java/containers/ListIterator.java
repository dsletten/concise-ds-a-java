package containers;

public abstract class ListIterator<E> {
    protected List<E> list;
    protected RemoteControl rc;

    protected ListIterator(List<E> list, RemoteControl rc) {
        this.list = list;
        this.rc = rc;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public final E current() {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty.");
        } else {
            return doCurrent();
        }
    }

    protected abstract E doCurrent();

    public final int currentIndex() {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty.");
        } else {
            return doCurrentIndex();
        }
    }

    protected abstract int doCurrentIndex();

    public final void setCurrent(E obj) {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty.");
        } else {
            doSetCurrent(obj);
        }
    }

    protected abstract void doSetCurrent(E obj);

    public abstract boolean hasNext();

    public abstract boolean hasPrevious();

    public final E next() {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty.");
        } else {
            return doNext();
        }
    }

    protected abstract E doNext();

    @SuppressWarnings("UnusedReturnValue")
    public final E previous() {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty.");
        } else {
            return doPrevious();
        }
    }

    protected abstract E doPrevious();

    @SuppressWarnings("unused")
    public final E remove() {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty.");
        } else {
            return doRemove();
        }
    }

    protected abstract E doRemove();

    public abstract void addBefore(E obj);

    public abstract void addAfter(E obj);
}

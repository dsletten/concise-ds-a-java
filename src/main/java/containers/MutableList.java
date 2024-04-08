package containers;

//
//    This is a little messier in Java since persistent collections have a different inheritance tree.
//    Everything that is a subclass of List<E> is actually a mutable list...
//
public abstract class MutableList<E> extends AbstractList<E> {
    protected int modificationCount = 0;

    protected MutableList() {
        super();
    }

    protected MutableList(E fillElt) {
        super(fillElt);
    }

    @Override
    public final void clear() {
        doClear();
        countModification();
    }

    protected abstract void doClear();

    @Override
    @SuppressWarnings("unchecked")
    protected final MutableList<E> doAdd(E... objs) {
        MutableList<E> result =  doDoAdd(objs);
        countModification();
        return result;
    }

    @SuppressWarnings("unchecked")
    protected abstract MutableList<E> doDoAdd(E... objs);

    @Override
    protected final void doInsert(int i, E obj) {
        doDoInsert(i, obj);
        countModification();
    }

    protected abstract void doDoInsert(int i, E obj);

    @Override
    protected final E doDelete(int i) {
        E doomed = doDoDelete(i);
        countModification();
        return doomed;
    }

    protected abstract E doDoDelete(int i);

    protected void countModification() {
        modificationCount++;
    }

//    protected int getModificationCount() {
//        return modificationCount;
//    }
}

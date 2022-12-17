package containers;

//
//    This is a little messier in Java since persistent collections have a different inheritance tree.
//    Everything that is a subclass of List<E> is actually a mutable list...
//
public abstract class MutableList<E> extends List<E> {
    protected int modificationCount = 0;

    protected MutableList() {
        super();
    }

    protected MutableList(E fillElt) {
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
    public final MutableList<E> doAdd(E... objs) {
        if ( objs.length > 0 ) {
            countModification();
            return doDoAdd(objs);
        } else {
            return this;
        }
    }

    @SuppressWarnings("unchecked")
    protected abstract MutableList<E> doDoAdd(E... objs);

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

    protected void countModification() {
        modificationCount++;
    }

//    protected int getModificationCount() {
//        return modificationCount;
//    }

//    Only mutable List<Integer>?!
//    protected List<Integer> fill(int count) {
//        for (int i = 1; i < count; i++) {
//            add(i);
//        }
//    }
}

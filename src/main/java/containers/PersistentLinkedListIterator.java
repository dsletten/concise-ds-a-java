package containers;

public class PersistentLinkedListIterator<E> extends PersistentIterator<E> {
    private PersistentList<E> collection;

    protected PersistentLinkedListIterator(PersistentList<E> collection) {
        this.collection = collection;
    }

    @Override
    public boolean isDone() {
        return collection.isEmpty();
    }

    @Override
    protected E doCurrent() {
        return collection.get(0);
    }

    @Override
    public PersistentIterator<E> next() {
        if ( isDone() ) {
            return this;
        } else {
            return new PersistentLinkedListIterator<E>(collection.delete(0));
        }
    }
}

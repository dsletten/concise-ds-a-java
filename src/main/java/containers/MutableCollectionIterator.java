package containers;

import java.util.function.Supplier;

public class MutableCollectionIterator<E> extends Iterator<E> {
    private int expectedModificationCount;
    private Supplier<Integer> modificationCount;

    public MutableCollectionIterator(Cursor<E> cursor, Supplier<Integer> modificationCount) {
        super(cursor);
        this.modificationCount = modificationCount;
        expectedModificationCount = modificationCount.get();
    }

    private boolean comodified() {
        return expectedModificationCount != modificationCount.get();
    }

    @Override
    public boolean checkDone() {
        checkComodification();

        return super.checkDone();
    }

    @Override
    protected E currentElement() {
        checkComodification();

        return super.currentElement();
    }

    @Override
    public void nextElement() {
        checkComodification();

        super.nextElement();
    }

    private void checkComodification() {
        if ( comodified() ) {
            throw new IllegalStateException("List iterator invalid due to structural modification of collection.");
        }
    }

}

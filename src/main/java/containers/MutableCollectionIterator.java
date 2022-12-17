package containers;

import java.util.function.Supplier;

public class MutableCollectionIterator<E> extends Iterator<E> {
    @SuppressWarnings("FieldMayBeFinal")
    private int expectedModificationCount;
    private final Supplier<Integer> modificationCount;

    public MutableCollectionIterator(Cursor<E> cursor, Supplier<Integer> modificationCount) {
        super(cursor);
        this.modificationCount = modificationCount;
        expectedModificationCount = modificationCount.get();
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
            throw new IllegalStateException("Iterator invalid due to structural modification of collection.");
        }
    }

    private boolean comodified() {
        return expectedModificationCount != modificationCount.get();
    }
}

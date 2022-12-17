package containers;

import java.util.function.Supplier;

@SuppressWarnings("ALL")
public class MutableCollectionIteratorRC<E> extends IteratorRC<E> {
    private int expectedModificationCount;
    private Supplier<Integer> modificationCount;

//    protected MutableCollectionIterator(Supplier<Boolean> done, Supplier<E> curr, Runnable next, Supplier<Integer> modificationCount) {
//        super(done, curr, next);
//        this.modificationCount = modificationCount;
//        this.expectedModificationCount = modificationCount.get();
//    }

//    private boolean comodified() {
//        return expectedModificationCount != modificationCount.get();
//    }

    public MutableCollectionIteratorRC(RemoteControlIterator<E> remoteControl, Supplier<Integer> modificationCount) {
        super(remoteControl);
        this.modificationCount = modificationCount;
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

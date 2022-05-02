package containers;

public abstract class IteratorRC<E> {
//    private Supplier<Boolean> done;
//    private Supplier<E> curr;
//    private Runnable next;

    //
    //    This won't work in Java since lambdas are not actual closures!
    //    Close over values rather than bindings. (final variables)
    //
//    public IteratorTemplate(Supplier<Boolean> done, Supplier<E> curr, Runnable next) {
//        this.done = done;
//        this.curr = curr;
//        this.next = next;
//    }

    private RemoteControl<E> remoteControl;

    public IteratorRC(RemoteControl<E> remoteControl) {
        this.remoteControl = remoteControl;
    }

    public final boolean isDone() {
        return checkDone();
    }

//    protected abstract boolean checkDone();
    protected boolean checkDone() {
        return remoteControl.checkDone();
    }

    public final E current() {
        if ( isDone() ) {
            throw new IllegalStateException("Iteration already finished");
        } else {
            return currentElement();
        }
    }

//    protected abstract E currentElement();
    protected E currentElement() {
        return remoteControl.currentElement();
    }

    public final E next() {
        if ( isDone() ) {
            return null;
        } else {
            nextElement();

            if ( isDone() ) {
                return null;
            } else {
                return current();
            }
        }
    }

//    protected abstract void nextElement();
    protected void nextElement() {
        remoteControl.nextElement();
//        next.run(); // ????????????
    }
}

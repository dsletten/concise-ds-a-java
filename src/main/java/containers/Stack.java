package containers;

import java.util.EmptyStackException;

public abstract class Stack<E> extends Dispenser<E> {
    public abstract void push(E obj);

    public final E pop() {
        if ( isEmpty() ) {
            throw new EmptyStackException();
        } else {
            return doPop();
        }
    }

    protected abstract E doPop();

    public final E peek() {
        if ( isEmpty() ) {
            throw new EmptyStackException();
        } else {
            return doPeek();
        }
    }

    protected abstract E doPeek();
}

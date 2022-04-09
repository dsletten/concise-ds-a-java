package containers;

import java.util.EmptyStackException;

/**
 *
 * This can't be part of the normal Stack hierarchy since the method signatures are different.
 * The persistent methods always return new instances rather than modifying an instance in place.
 *
 * This could be part of the Container hierarchy but for the clear() method...
 */
public abstract class PersistentStack<E> extends PersistentDispenser<E> {
    public abstract PersistentStack<E> push(E obj);

    public final PersistentStack<E> pop() {
        if ( isEmpty() ) {
            throw new EmptyStackException();
        } else {
            return doPop();
        }
    }

    protected abstract PersistentStack<E> doPop();

    public final E peek() {
        if ( isEmpty() ) {
            throw new EmptyStackException();
        } else {
            return doPeek();
        }
    }

    protected abstract E doPeek();
}

package containers;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.function.Function;

/**
 *
 * This can't be part of the normal Stack hierarchy since the method signatures are different.
 * The persistent methods always return new instances rather than modifying an instance in place.
 * <p>
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

    @Override
    public final PersistentStack<E> clear() {
        return makeEmptyPersistentStack();
    }

    protected abstract PersistentStack<E> makeEmptyPersistentStack();

    @Override
    PersistentStack<E> fill(int count, Function<Integer, E> generator) {
        PersistentStack<E> stack = this;

        for (int i = 1; i <= count; i++) {
            stack = stack.push(generator.apply(i));
        }

        return stack;
    }

    public <T> T[] toArray(T[] a) {
        PersistentStack<E> stack = this;
        //noinspection unchecked
        E[] elements = (E[]) new Object[size()];
        int i = 0;
        int count = size();

        while ( !stack.isEmpty() ) {
            elements[i++] = stack.peek();
            stack = stack.pop();
        }

        //noinspection unchecked
        return (T[]) Arrays.copyOf(elements, count, a.getClass());
    }
}

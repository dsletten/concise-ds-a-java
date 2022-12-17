package containers;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.function.Function;

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

    @Override
    Stack<E> fill(int count, Function<Integer, E> generator) {
        for (int i = 1; i <= count; i++) {
            push(generator.apply(i));
        }

        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
//    public E[] elements() {
    public <T> T[] toArray(T[] a) {
        E[] elements = (E[]) new Object[size()];
        int i = 0;
        int count = size();

        while ( !isEmpty() ) {
            elements[i++] = pop();
        }

        return (T[]) Arrays.copyOf(elements, count, a.getClass());
    }
}

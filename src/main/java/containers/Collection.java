package containers;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public abstract class Collection<E> extends Container<E> {
    public abstract Iterator<E> iterator();

//    public final E contains(E object) {
//        return contains(object, (item, elt) -> item == elt);
//    }
    public final E contains(E object) {
    return contains(object, Objects::equals);
}

    public E contains(E object, BiPredicate<E, E> test) {
        Iterator<E> iterator = iterator();

        while ( !iterator.isDone() ) {
            E elt = iterator.current();

            if ( test.test(object, elt) ) {
                return elt;
            }

            iterator.next();
        }

        return null;
    }

    public abstract void each(Consumer<E> f);

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public final boolean equals(Object o) {
        return equals(o, Objects::equals);
    }

    public abstract boolean equals(Object o, BiPredicate<E, Object> test);
}

package containers;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

//
//    Problems with `interface` vs. `abstract class`
//    - Can't make top-level methods final to prevent overriding, e.g., pop() that doesn't check isEmpty()
//    - Can't make hook methods protected, e.g., doPop()
//
public interface Collection<E> extends Container<E> {
    Iterator<E> iterator();

//    public final E contains(E object) {
//        return contains(object, (item, elt) -> item == elt);
//    }
    default E contains(E object) {
        return contains(object, Objects::equals);
    }

    default E contains(E object, BiPredicate<E, E> test) {
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

    void each(Consumer<E> f);

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
//    @Override
//    default boolean equals(Object o) {
//        return equals(o, Objects::equals);
//    }

            //    Overload!
//    default boolean equals(Collection o) {
//        return equals(o, Objects::equals);
//    }

    boolean equals(Object o, BiPredicate<E, Object> test);
}

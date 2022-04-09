package containers;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public abstract class PersistentCollection<E> extends PersistentContainer<E> {
    public abstract PersistentIterator<E> iterator();

    public E contains(E object) {
        return contains(object, (item, elt) -> item.equals(elt));
    }

    public abstract E contains(E object, BiPredicate<E, E> test);

    public abstract void each(Consumer<E> f);

    @Override
    public final boolean equals(Object o) {
        return equals(o, (elt1, elt2) -> Objects.equals(elt1, elt2));
    }

    public abstract boolean equals(Object o, BiPredicate<E, Object> test);
}

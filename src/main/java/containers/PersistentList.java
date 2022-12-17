package containers;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class PersistentList<E> extends PersistentCollection<E> {
    private final E fillElt;
    @SuppressWarnings("unused")
    public static final int NOT_PRESENT = -1;

    protected PersistentList() {
        this(null);
    }

    protected PersistentList(E fillElt) {
        this.fillElt = fillElt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");

        if ( !isEmpty() ) {
            PersistentIterator<E> iterator = iterator();
            sb.append(iterator.current());
            iterator = iterator.next();

            while (!iterator.isDone()) {
                sb.append(", ");
                sb.append(iterator.current());
                iterator = iterator.next();
            }
        }

        sb.append(")");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o, BiPredicate<E, Object> test) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PersistentList)) {
            return mixedEquals(this, o, test);
        } else {
            //noinspection unchecked
            return persistentEquals(this, (PersistentList<Object>) o, test);
        }
    }

    private static <E> boolean mixedEquals(PersistentList<E> pl, Object o, BiPredicate<E, Object> test) {
        if ( !(o instanceof List) ) {
            return false;
        } else {
            //noinspection unchecked
            List<Object> list = (List<Object>) o;

            if (list.size() != pl.size()) {
                return false;
            } else {
                PersistentIterator<E> i1 = pl.iterator();
                Iterator<Object> i2 = list.iterator();

                while (!i1.isDone() && !i2.isDone()) {
                    if (!test.test(i1.current(), i2.current())) {
                        return false;
                    }

                    i1 = i1.next();
                    i2.next();
                }

                return true;
            }

        }

    }

    private static <E> boolean persistentEquals(PersistentList<E> pl1, PersistentList<Object> pl2, BiPredicate<E, Object> test) {
        if (pl1.size() != pl2.size()) {
            return false;
        } else {
            PersistentIterator<E> i1 = pl1.iterator();
            PersistentIterator<Object> i2 = pl2.iterator();

            while (!i1.isDone() && !i2.isDone()) {
                if (!test.test(i1.current(), i2.current())) {
                    return false;
                }

                i1 = i1.next();
                i2 = i2.next();
            }

            return true;
        }
    }

    @Override
    public void each(Consumer<E> f) {
        PersistentIterator<E> iterator = iterator();

        while ( !iterator.isDone() ) {
            f.accept(iterator.current());
            iterator = iterator.next();
        }
    }

    @SuppressWarnings("unchecked")
    public PersistentList<E> add(E... objs) {
        if ( objs.length == 0 ) {
            return this;
        } else {
            return doAdd(objs);
        }
    }

    @SuppressWarnings("unchecked")
    protected abstract PersistentList<E> doAdd(E... objs);

    @Override
    PersistentList<E> fill(int count, Function<Integer, E> generator) {
        //noinspection unchecked
        E[] elts = (E[]) new Object[count];

        for (int i = 1; i <= count; i++) {
            elts[i] = generator.apply(i);
        }

        add(elts);

        return this;
    }

    public <T> T[] toArray(T[] a) {
        //noinspection unchecked
        E[] elements = (E[]) new Object[size()];
        int i = 0;
        PersistentIterator<E> iterator = iterator();

        while (!iterator.isDone()) {
            elements[i++] = iterator.current();
            iterator = iterator.next();
        }


        //noinspection unchecked
        return (T[]) Arrays.copyOf(elements, size(), a.getClass());
    }

    @SuppressWarnings("unchecked")
    protected PersistentList<E> extendList(int i, E obj) {
        Object[] objs = new Object[i - size() + 1];
        Arrays.fill(objs, fillElt);
        objs[objs.length - 1] = obj;
        return add((E[]) objs); // ?!?!?!
    }

    /**
     *
     * @param i
     * @param obj
     *
     * Insert obj at index i. If i is greater than the current size, extend the list.
     * If i is negative, count backwards from the end of the list.
     */
    @SuppressWarnings("JavadocDeclaration")
    public final PersistentList<E> insert(int i, E obj) {
        if (i < 0) {
            int j = i + size();
            if (j >= 0) {
                return insert(j, obj);
            } else {
                return this;
            }
        } else if ( i >= size() ) {
            return extendList(i, obj);
        } else {
            return doInsert(i, obj);
        }
    }

    protected abstract PersistentList<E> doInsert(int i, E obj);

    public final PersistentList<E> delete(int i) {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty.");
//        } else if ( i < 0 ) {
//            int j = i + size();
//            if (j >= 0) {
//                return delete(j);
//            } else {
//                throw new IndexOutOfBoundsException("Invalid index");
//            }
//        } else if ( i < size() ) {
//            return doDelete(i);
        } else if ( i >= size() ) {
            return this;
        } else if ( i < -size() ) {
            return this;
        } else {
//            throw new IndexOutOfBoundsException("Invalid index");
            return doDelete(i);
        }
    }

    protected abstract PersistentList<E> doDelete(int i);

    /*
     *    Fox's specification returns null for invalid index. I've modified this to throw an exception
     *    in the Java version. The implementation is identical to delete(). Both introduce a slight inconsistency:
     *    a different exception is thrown when i == 0 and the list is empty compared with other out-of-bound indices.
     */
    public final E get(int i) {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty.");
        } else if ( i < 0 ) {
            int j = i + size();
            if (j >= 0) {
                return get(j);
            } else {
                throw new IndexOutOfBoundsException("Invalid index");
            }
        } else if ( i < size() ) {
            return doGet(i);
        } else {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    protected abstract E doGet(int i);

    /*
     *    Identical to insert()
     */
    public final PersistentList<E> set(int i, E obj) {
        if (i < 0) {
            int j = i + size();
            if (j >= 0) {
                return set(j, obj);
            } else {
                return this;
            }
        } else if ( i >= size() ) {
            return extendList(i, obj);
        } else {
            return doSet(i, obj);
        }
    }

    protected abstract PersistentList<E> doSet(int i, E obj);

    public int index(E obj) {
        return index(obj, Object::equals);
    }

    public abstract int index(E obj, BiPredicate<E, E> test);

    public final PersistentList<E> slice(int i, int n) {
        if ( n < 0 ) {
            throw new IllegalArgumentException("Count must be non-negative: " + n);
        } else if (i < 0) {
            int j = i + size();
            if (j < 0) {
                return doSlice(0, 0);
            } else {
                return doSlice(j, n);
            }
        } else {
            return doSlice(i, n);
        }
    }

    protected abstract PersistentList<E> doSlice(int i, int n);

    public E getFillElt() {
        return fillElt;
    }
}

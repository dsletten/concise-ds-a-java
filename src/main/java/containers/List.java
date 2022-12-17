package containers;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class List<E> extends Collection<E> {
    private final E fillElt;
    public static final int NOT_PRESENT = -1;

    protected List() {
        this(null);
    }

    protected List(E fillElt) {
        this.fillElt = fillElt;
    }

    protected abstract List<E> makeEmptyList();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Iterator<E> iterator = iterator();

        while (!iterator.isDone()) {
            sb.append(iterator.current());
            iterator.next();

            if (!iterator.isDone()) {
                sb.append(", ");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    //
    //    See java.util.AbstractList.java
    //
    @Override  // ???
    public boolean equals(Object o, BiPredicate<E, Object> pred) {
        if (o == this) {
            return true;
        } else if (o instanceof PersistentList) { // ????
            //noinspection EqualsBetweenInconvertibleTypes
            return o.equals(this);
        } else if (!(o instanceof List<?> list)) {  // Suggested by IntelliJ!!
            return false;
        } else {
//            List<Object> list = (List<Object>) o;

            if (list.size() != this.size()) {
                return false;
            } else {
                Iterator<E> i1 = iterator();
//                Iterator<E> i2 = ((List<E>) o).iterator();
//                Iterator<Object> i2 = list.iterator();
                Iterator<?> i2 = list.iterator();

                while (!i1.isDone() && !i2.isDone()) {
                    if (!pred.test(i1.current(), i2.current())) {
                        return false;
                    }

                    i1.next();
                    i2.next();
                }

                return true;
            }
        }
    }

    @Override
    public void each(Consumer<E> f) {
        Iterator<E> iterator = iterator();

        while (!iterator.isDone()) {
            f.accept(iterator.current());
            iterator.next();
        }
    }

    public final ListIterator<E> listIterator() {
        return listIterator(0);
    }
    public abstract ListIterator<E> listIterator(int start);

    @SuppressWarnings("unchecked")
    public final List<E> add(E... objs) {
        if ( objs.length == 0 ) {
            return this;
        } else {
            return doAdd(objs);
        }
    }
    @SuppressWarnings("unchecked")
    protected abstract List<E> doAdd(E... objs);

    @Override
    List<E> fill(int count, Function<Integer, E> generator) {
        //noinspection unchecked
        E[] elts = (E[]) new Object[count];

        for (int i = 1; i <= count; i++) {
            elts[i-1] = generator.apply(i);
        }

        add(elts);

        return this;
    }

    @Override
//    public E[] elements() {
    public <T> T[] toArray(T[] a) {
        //noinspection unchecked
        E[] elements = (E[]) new Object[size()];
        int i = 0;
        int count = size();

        Iterator<E> iterator = iterator();

        while (!iterator.isDone()) {
            elements[i++] = iterator.current();
            iterator.next();
        }

        clear();

        //noinspection unchecked
        return (T[]) Arrays.copyOf(elements, count, a.getClass());
    }

    @SuppressWarnings("unchecked")
//    protected void extendList(int i, E obj) {
//        Object[] objs = new Object[i - size() + 1];
//        Arrays.fill(objs, fillElt);
//        objs[objs.length - 1] = obj;
//        add((E[]) objs); // ?!?!?!
//    }
    protected void extendList(int i, E obj) {
        E[] elts = (E[]) new Object[i - size() + 1];
        Arrays.fill(elts, fillElt);
        elts[elts.length - 1] = obj;
        add(elts);
    }

    /**
     * @param i
     * @param obj Insert obj at index i. If i is greater than the current size, extend the list.
     *            If i is negative, count backwards from the end of the list.
     */
    @SuppressWarnings("JavadocDeclaration")
    public final void insert(int i, E obj) {
        if (i < 0) {
            int j = i + size();
            if (j >= 0) {
                insert(j, obj);
            }
        } else if (i >= size()) {
            extendList(i, obj);
        } else {
            doInsert(i, obj);
        }
    }

    protected abstract void doInsert(int i, E obj);

    public final E delete(int i) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        } else if (i < 0) {
            int j = i + size();
            if (j >= 0) {
                return delete(j);
            } else {
                throw new IndexOutOfBoundsException("Invalid index: " + i);  // ???
            }
        } else if (i < size()) {
            return doDelete(i);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + i);  // ???
        }
    }

    protected abstract E doDelete(int i);

    /*
     *    Fox's specification returns null for invalid index. I've modified this to throw an exception
     *    in the Java version. The implementation is identical to delete(). Both introduce a slight inconsistency:
     *    a different exception is thrown when i == 0 and the list is empty compared with other out-of-bound indices.
     */
    public final E get(int i) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        } else if (i < 0) {
            int j = i + size();
            if (j >= 0) {
                return get(j);
            } else {
                throw new IndexOutOfBoundsException("Invalid index");
            }
        } else if (i < size()) {
            return doGet(i);
        } else {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    protected abstract E doGet(int i);

    /*
     *    Identical to insert()
     *    Thus, set() can modify list structure if list is extended. extendList() calls add(), which
     *    updates modification count.
     */
    public final void set(int i, E obj) {
        if (i < 0) {
            int j = i + size();
            if (j >= 0) {
                set(j, obj);
            }
        } else if (i >= size()) {
            extendList(i, obj);
        } else {
            doSet(i, obj);
        }
    }

    protected abstract void doSet(int i, E obj);

    public final int index(E obj) {
        return index(obj, (item, elt) -> item == elt);
    }

    public int index(E obj, BiPredicate<E, E> test) {
        Iterator<E> iterator = iterator();
        int i = 0;

        while (!iterator.isDone()) {
            E elt = iterator.current();

            if (test.test(obj, elt)) {
                return i;
            }

            iterator.next();
            i++;
        }

        return NOT_PRESENT;
    }

//    public final List<E> slice(int i) {
//        if (i < 0) {
//            int j = i + size();
//            if (j < 0) {
//                return slice(0, 0);
//            } else {
//                return slice(j);
//            }
//        } else {
//            return slice(i, size() - i);
//        }
//    }
//
//    public final List<E> slice(int i, int n) {
//        if ( n < 0 ) {
//            throw new IllegalArgumentException("Count must be non-negative: " + n);
//        } else if (i < 0) {
//            int j = i + size();
//            if (j < 0) {
//                return doSlice(0, 0);
//            } else {
//                return doSlice(j, n);
//            }
//        } else {
//            return doSlice(i, n);
//        }
//    }

    public final List<E> slice(int i) {
        return slice(i, null);
    }

    public final List<E> slice(int i, Integer n) {
        if (i < 0) {
            int j = i + size();
            if (j < 0) {
                return doSlice(0, 0);
            } else {
                return doSlice(j, n);
            }
        } else if (n == null) {
            return slice(i, size() - i);
        } else if (n < 0) {
            throw new IllegalArgumentException("Count must be non-negative: " + n);
        } else {
            return doSlice(i, n);
        }
    }

    protected abstract List<E> doSlice(int i, int n);

    public E getFillElt() {
        return fillElt;
    }

    public List<E> reverse() {
        //noinspection unchecked
        E[] reversed = (E[]) new Object[size()];
        int index = size() - 1;

        Iterator<E> iterator = iterator();

        while (!iterator.isDone()) {
            reversed[index--] = iterator.current();

            iterator.next();
        }

        return makeEmptyList().add(reversed);
    }
}

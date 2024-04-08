package containers;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

//    These interface methods have no teeth. For instance, they can't enforce resolving negative indexes.
//    Some instance of List could blow up when given negative indexes!!!
public interface List<E> extends Collection<E> {
    int NOT_PRESENT = -1;

    List<E> makeEmptyList();

//    @Override
//    default String toString() {
//        StringBuilder sb = new StringBuilder("(");
//        Iterator<E> iterator = iterator();
//
//        while (!iterator.isDone()) {
//            sb.append(iterator.current());
//            iterator.next();
//
//            if (!iterator.isDone()) {
//                sb.append(", ");
//            }
//        }
//
//        sb.append(")");
//
//        return sb.toString();
//    }

    //
    //    See java.util.AbstractList.java
    //
//    @Override  // ???
//    public boolean equals(Object o, BiPredicate<E, Object> pred) {
//        if (o == this) {
//            return true;
//        } else if (o instanceof PersistentList) { // ????
//            //noinspection EqualsBetweenInconvertibleTypes
//            return o.equals(this);
//        } else if (!(o instanceof List<?> list)) {  // Suggested by IntelliJ!!
//            return false;
//        } else {
////            List<Object> list = (List<Object>) o;
//
//            if (list.size() != this.size()) {
//                return false;
//            } else {
//                Iterator<E> i1 = iterator();
////                Iterator<E> i2 = ((List<E>) o).iterator();
////                Iterator<Object> i2 = list.iterator();
//                Iterator<?> i2 = list.iterator();
//
//                while (!i1.isDone() && !i2.isDone()) {
//                    if (!pred.test(i1.current(), i2.current())) {
//                        return false;
//                    }
//
//                    i1.next();
//                    i2.next();
//                }
//
//                return true;
//            }
//        }
//    }

    @Override
    default void each(Consumer<E> f) {
        Iterator<E> iterator = iterator();

        while (!iterator.isDone()) {
            f.accept(iterator.current());
            iterator.next();
        }
    }

    default ListIterator<E> listIterator() {
        return listIterator(0);
    }

    ListIterator<E> listIterator(int start);

    List<E> add(E... objs);

    //    @SuppressWarnings("unchecked")
    //    default List<E> add(E... objs) {
    //        if (objs.length == 0) {
    //            return this;
    //        } else {
    //            return doAdd(objs);
    //        }
    //    }
    //
    //    @SuppressWarnings("unchecked")
    //    List<E> doAdd(E... objs);

    @Override
    default List<E> fill(int count, Function<Integer, E> generator) {
        //noinspection unchecked
        E[] elts = (E[]) new Object[count];

        for (int i = 1; i <= count; i++) {
            elts[i - 1] = generator.apply(i);
        }

        add(elts);

        return this;
    }

    @Override
//    public E[] elements() {
    default <T> T[] toArray(T[] a) {
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

//    void extendList(int i, E obj);

    //    protected void extendList(int i, E obj) {
//        Object[] objs = new Object[i - size() + 1];
//        Arrays.fill(objs, fillElt);
//        objs[objs.length - 1] = obj;
//        add((E[]) objs); // ?!?!?!
//    }
//    void extendList(int i, E obj) {
//        E[] elts = (E[]) new Object[i - size() + 1];
//        Arrays.fill(elts, fillElt);
//        elts[elts.length - 1] = obj;
//        add(elts);
//    }

    //
    //    Problems with `around` methods on interface...
    //    Keep this abstract? Put `default` on AbstractList and make doInsert() protected there?
    //
    void insert(int i, E obj);

//    /**
//     * @param i
//     * @param obj Insert obj at index i. If i is greater than the current size, extend the list.
//     *            If i is negative, count backwards from the end of the list.
//     */
//    @SuppressWarnings("JavadocDeclaration")
//    default void insert(int i, E obj) {
//        if (i < 0) {
//            int j = i + size();
//            if (j >= 0) {
//                insert(j, obj);
//            }
//        } else if (i >= size()) {
//            extendList(i, obj);
//        } else {
//            doInsert(i, obj);
//        }
//    }
//
//    void doInsert(int i, E obj);

    E delete(int i);

//    default E delete(int i) {
//        if (isEmpty()) {
//            throw new IllegalStateException("List is empty.");
//        } else if (i < 0) {
//            int j = i + size();
//            if (j >= 0) {
//                return delete(j);
//            } else {
//                throw new IndexOutOfBoundsException("Invalid index: " + i);  // ???
//            }
//        } else if (i < size()) {
//            return doDelete(i);
//        } else {
//            throw new IndexOutOfBoundsException("Invalid index: " + i);  // ???
//        }
//    }
//
//    E doDelete(int i);

    E get(int i);

//    /*
//     *    Fox's specification returns null for invalid index. I've modified this to throw an exception
//     *    in the Java version. The implementation is identical to delete(). Both introduce a slight inconsistency:
//     *    a different exception is thrown when i == 0 and the list is empty compared with other out-of-bound indices.
//     */
//    default E get(int i) {
//        if (isEmpty()) {
//            throw new IllegalStateException("List is empty.");
//        } else if (i < 0) {
//            int j = i + size();
//            if (j >= 0) {
//                return get(j);
//            } else {
//                throw new IndexOutOfBoundsException("Invalid index");
//            }
//        } else if (i < size()) {
//            return doGet(i);
//        } else {
//            throw new IndexOutOfBoundsException("Invalid index");
//        }
//    }
//
//    E doGet(int i);

    void set(int i, E obj);

//    /*
//     *    Identical to insert()
//     *    Thus, set() can modify list structure if list is extended. extendList() calls add(), which
//     *    updates modification count.
//     */
//    default void set(int i, E obj) {
//        if (i < 0) {
//            int j = i + size();
//            if (j >= 0) {
//                set(j, obj);
//            }
//        } else if (i >= size()) {
//            extendList(i, obj);
//        } else {
//            doSet(i, obj);
//        }
//    }
//
//    void doSet(int i, E obj);

    default int index(E obj) {
        return index(obj, (item, elt) -> item == elt);
    }

    default int index(E obj, BiPredicate<E, E> test) {
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

        default List<E> slice(int i) {
            return slice(i, null);
        }

        List<E> slice(int i, Integer n);

//        default List<E> slice(int i, Integer n){
//            if (i < 0) {
//                int j = i + size();
//                if (j < 0) {
//                    return doSlice(0, 0);
//                } else {
//                    return doSlice(j, n);
//                }
//            } else if (n == null) {
//                return slice(i, size() - i);
//            } else if (n < 0) {
//                throw new IllegalArgumentException("Count must be non-negative: " + n);
//            } else {
//                return doSlice(i, n);
//            }
//        }
//
//        List<E> doSlice(int i, int n);

        default List<E> reverse () {
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

package containers;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public abstract class List<E> extends Collection<E> {
    private final E fillElt;
    public static final int NOT_PRESENT = -1;

    protected List() {
        this(null);
    }

    protected List(E fillElt) {
        this.fillElt = fillElt;
    }

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

    @Override
    public boolean equals(Object o, BiPredicate<E, Object> test) {
        if (o == this) {
            return true;
        } else if (o instanceof PersistentList) {
            return o.equals(this);
        } else if (!(o instanceof List)) {
            return false;
        } else {
            List<Object> list = (List<Object>) o;

            if (list.size() != this.size()) {
                return false;
            } else {
//            final int expectedModCount = modCount;    java.util.ArrayList

                Iterator<E> i1 = iterator();
//                Iterator<E> i2 = ((List<E>) o).iterator();
                Iterator<Object> i2 = list.iterator();

                while (!i1.isDone() && !i2.isDone()) {
                    if (!test.test(i1.current(), i2.current())) {
                        return false;
                    }

                    i1.next();
                    i2.next();
                }

                return true;
            }
        }
//            checkForComodification(expectedModCount);
    }

    @Override
    public void each(Consumer<E> f) {
        Iterator<E> iterator = iterator();

        while (!iterator.isDone()) {
            f.accept(iterator.current());
            iterator.next();
        }
    }

    @SuppressWarnings("unchecked")
    public abstract void add(E... objs);

    @SuppressWarnings("unchecked")
    protected void extendList(int i, E obj) {
        Object[] objs = new Object[i - size() + 1];
        Arrays.fill(objs, fillElt);
        objs[objs.length - 1] = obj;
        add((E[]) objs); // ?!?!?!
    }

    /**
     * @param i
     * @param obj Insert obj at index i. If i is greater than the current size, extend the list.
     *            If i is negative, count backwards from the end of the list.
     */
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
                throw new IndexOutOfBoundsException("Invalid index");
            }
        } else if (i < size()) {
            return doDelete(i);
        } else {
            throw new IndexOutOfBoundsException("Invalid index");
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

    public abstract int index(E obj, BiPredicate<E, E> test);

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
}

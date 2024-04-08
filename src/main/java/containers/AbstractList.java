package containers;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

public abstract class AbstractList<E> implements List<E> {
    private final E fillElt;

    protected AbstractList() {
        this(null);
    }

    protected AbstractList(E fillElt) {
        this.fillElt = fillElt;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> add(E... objs) {
        if (objs.length == 0) {
            return this;
        } else {
            return doAdd(objs);
        }
    }

    @SuppressWarnings("unchecked")
    protected abstract List<E> doAdd(E... objs);

    /**
     * @param i
     * @param obj Insert obj at index i. If i is greater than the current size, extend the list.
     *            If i is negative, count backwards from the end of the list.
     */
    @SuppressWarnings("JavadocDeclaration")
    @Override
    public void insert(int i, E obj) {
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

    @Override
    public E delete(int i) {
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
    @Override
    public E get(int i) {
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
    @Override
    public void set(int i, E obj) {
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

    @Override
    public List<E> slice(int i, Integer n) {
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

    protected List<E> doSlice(int i, int n) {
        List<E> slice = makeEmptyList();
        int count = size();

        return slice.add((E[]) sublist(Math.min(i, count), Math.min(i + n, count)));
    }

    //    protected <T> T[] sublist(T[] a, int start, int end) {
    protected Object[] sublist(int start, int end) {
        //noinspection unchecked
        Object[] result = new Object[end - start - 1];

        for (int i = 0; i < result.length; i++) { // Use list iterator!!
            result[i] = get(i + start);
        }

        return result;
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

    //    Can't be defined on Collection interface???
    @Override
    public boolean equals(Object o) {
        return equals(o, Objects::equals);
    }

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

    @SuppressWarnings("unchecked")
    protected void extendList(int i, E obj) {
        E[] elts = (E[]) new Object[i - size() + 1];
        Arrays.fill(elts, fillElt);
        elts[elts.length - 1] = obj;
        add(elts);
    }

    protected E getFillElt () {
        return fillElt;
    }
}

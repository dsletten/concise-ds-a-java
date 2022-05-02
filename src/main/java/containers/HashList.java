package containers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class HashList<E> extends MutableList<E> {
    private Map<Integer, E> store = new HashMap<>();

    public HashList() {
        super();
    }

    public HashList(E fillElt) {
        super(fillElt);
    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    protected void doClear() {
        store.clear();
    }

    @Override
//    public Iterator<E> iterator() {
//        return new RandomAccessListIterator();
//    }
    public Iterator<E> iterator() {
        return new MutableCollectionIterator<>(new Cursor<E>() {
            private int cursor = 0;

            @Override
            public boolean isDone() {
                return cursor == size();
            }

            @Override
            public E current() {
                return get(cursor);
            }

            @Override
            public void advance() {
                cursor++;
            }
        }, () -> getModificationCount());
    }

    @Override
    public E contains(E object, BiPredicate<E, E> test) {
        for (E elt : store.values()) {
            if ( test.test(object, elt) ) {
                return elt;
            }
        }

        return null;
    }

    @Override
    protected void doAdd(E... objs) {
        for (E obj : objs) {
            store.put(store.size(), obj);
        }
    }

    @Override
    protected void doDoInsert(int i, E obj) {
        for (int j = store.size(); j > i; j--) {
            store.put(j, store.get(j-1));
        }

        store.put(i, obj);
    }

    @Override
    protected E doDoDelete(int i) {
        E doomed = get(i);

        for (int j = i; j < size() - 1; j++) {
            store.put(j, store.get(j+1));
        }

        store.remove(size() - 1);

        return doomed;
    }

    @Override
    protected E doGet(int i) {
        return store.get(i);
    }

    @Override
    protected void doSet(int i, E obj) {
        store.replace(i, obj);
    }

    @Override
    public int index(E obj, BiPredicate<E, E> test) {
        for (int i = 0; i < size(); i++) {
            if ( test.test(obj, store.get(i)) ) {
                return i;
            }
        }

        return NOT_PRESENT;
    }

    @Override
    protected List<E> doSlice(int i, int n) {
        List<E> list = new HashList<>(getFillElt());
        int start = Math.min(i, size());
        int end = Math.min(i+n, size());
        Object[] slice = new Object[end-start];

        for (int j = 0, k = start; j < slice.length; j++, k++) {
            slice[j] = get(k);
        }
        list.add((E[]) slice);

        return list;
    }

//    private class RandomAccessListIterator extends MutableListIteratorOld<E> {
//        private int cursor = 0;
//
//        //    Shouldn't give access to enclosing list?!
//        //    Tension between inner class and code reuse!!
//        private RandomAccessListIterator() {
//            super(HashList.this);
//        }
//
//        @Override
//        protected boolean doIsDone() {
//            return cursor == size();
//        }
//
//        @Override
//        protected E doDoCurrent() {
//            return get(cursor);
//        }
//
//        @Override
//        protected void doNext() {
//            if ( !isDone() ) {
//                cursor++;
//            }
//        }
//    }

    public static void main(String[] args) {
        List<Number> hl = new HashList<>();
        hl.add(2);
        hl.add(4);
        hl.add(6);
        hl.add(8);

        System.out.println(hl.size());
        System.out.println(hl);

        System.out.println(hl.get(0));
        hl.set(3, 10);
        System.out.println(hl);

        hl.insert(3, 8);
        System.out.println(hl);

        System.out.println(hl.get(-2));
        System.out.println(hl.get(-1));

        System.out.println(hl.slice(0, 5));
        System.out.println(hl.slice(1, 5));
        System.out.println(hl.slice(2, 5));
        System.out.println(hl.slice(-4, 2));
        System.out.println(hl.slice(-8, 2));

        hl.insert(12, 999);
        System.out.println(hl);

        hl = new HashList<>(0);
        hl.add(2, 4, 6, 8);
        hl.insert(12, 999);
        System.out.println(hl);

//        hl.each(integer -> System.out.println(integer));
        hl.each(System.out::println);

        System.out.println(hl.contains(2));
        System.out.println(hl.contains(2, (item, elt) -> elt.doubleValue() == Math.pow(item.doubleValue(), 3)));
        System.out.println(hl.contains(6.0, (item, elt) -> item.doubleValue() == elt.doubleValue())); // null?
    }
}

package containers;

import java.util.Arrays;

public class ArrayListJ<E> extends MutableList<E> {
    private static final int DEFAULT_CAPACITY = 20;
    private final java.util.ArrayList<E> store = new java.util.ArrayList<>(DEFAULT_CAPACITY);

    public ArrayListJ() {}

    public ArrayListJ(E fillElt) {
        super(fillElt);
    }

    @Override
    protected List<E> makeEmptyList() {
        return new ArrayListJ<>(getFillElt());
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
    public void doClear() {
        store.clear();
    }

    @Override
//    public Iterator<E> iterator() {
//        return new RandomAccessListIterator();
//    }
//    public Iterator<E> iterator() {
//        return new MutableCollectionIterator<>(new Cursor<E>() {
//            private int cursor = 0;
//
//            @Override
//            public boolean isDone() {
//                return cursor == size();
//            }
//
//            @Override
//            public E current() {
//                return get(cursor);
//            }
//
//            @Override
//            public void advance() {
//                cursor++;
//            }
//        }, () -> getModificationCount());
//    }
    public Iterator<E> iterator() {
        return new MutableCollectionIterator<>(CursorFactory.makeRandomAccessListCursor(this),
                () -> modificationCount);
    }

    public ListIterator<E> listIterator(int start) {
        return new RandomAccessListListIterator<>(this, start, new RemoteControl().addCommand("modificationCount", () -> modificationCount));
    }

//    @Override
//    public E contains(E object, BiPredicate<E, E> test) {
//        for (E elt : store) {
//            if ( test.test(object, elt) ) {
//                return elt;
//            }
//        }
//
//        return null;
//    }

    @Override
    @SuppressWarnings("unchecked")
    protected ArrayListJ<E> doDoAdd(E... objs) {
        store.addAll(Arrays.asList(objs));
        return this;
    }

    @Override
    protected void doDoInsert(int i, E obj) {
        store.add(i, obj);
    }

    @Override
    protected E doDoDelete(int i) {
        return store.remove(i);
    }

    @Override
    protected E doGet(int i) {
        return store.get(i);
    }

    @Override
    protected void doSet(int i, E obj) {
        store.set(i, obj);
    }

//    @Override
//    public int index(E obj, BiPredicate<E, E> test) {
//        for (int i = 0; i < store.size(); i++) {
//            if ( test.test(obj, get(i)) ) {
//                return i;
//            }
//        }
//
//        return NOT_PRESENT;
//    }

    @SuppressWarnings("unchecked")
    @Override
    protected List<E> doSlice(int i, int n) {
        //    Most straightforward! But does not follow use of add() to fill slice...
//        ArrayList<E> slice = new ArrayList<>(getFillElt());
//        slice.store.addAll(this.store.subList(i, n));
//        return slice;
        //    Extract to helper method...
        List<E> list = new ArrayListJ<>(getFillElt());
        int start = Math.min(i, size());
        int end = Math.min(i+n, size());
        Object[] slice = new Object[end-start];

        for (int j = 0, k = start; j < slice.length; j++, k++) {
            slice[j] = get(k);
        }
        list.add((E[]) slice);

        return list;
//        int size = size();
//        if ( i < 0 ) {
//            int j = i + size;
//            if ( j < 0 ) {
//                return new ArrayList<>();
//            } else {
//                return subList(j, Math.min(j + n, size));
//            }
//        } else {
//            return subList(Math.min(i, size), Math.min(i + n, size));
//        }
    }

    //    TODO: Fix this to use constructor!! Object[] -> E[]
//    @SuppressWarnings("unchecked")
//    private List<E> subList(int start, int end) {
//        ArrayList<E> slice = new ArrayList<>();
//        for (Object elt : store.subList(start, end)) {
//            slice.add((E) elt);
//        }
//        return slice;
//    }

//    private class RandomAccessListIterator extends MutableListIteratorOld<E> {
//        private int cursor = 0;
//
//        //    Shouldn't give access to enclosing list?!
//        //    Tension between inner class and code reuse!!
//        private RandomAccessListIterator() {
//            super(ArrayList.this);
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
        List<Integer> al = new ArrayListJ<>();
        al.add(2);
        al.add(4);
        al.add(6);
        al.add(8);

        System.out.println(al.size());
        System.out.println(al);

        System.out.println(al.get(0));
        al.set(3, 10);
        al.insert(3, 8);

        System.out.println(al);
        System.out.println(al.get(-2));
        System.out.println(al.get(-1));

        System.out.println(al.slice(0, 5));
        System.out.println(al.slice(1, 5));
        System.out.println(al.slice(2, 5));
        System.out.println(al.slice(-4, 2));
        System.out.println(al.slice(-8, 2));

        al.insert(12, 999);
        System.out.println(al);

        al = new ArrayListJ<>(0);
        al.add(2, 4, 6, 8);
        al.insert(12, 999);
        System.out.println(al);

//        al.each(integer -> System.out.println(integer));
        al.each(System.out::println);

        System.out.println(al.contains(2));
        System.out.println(al.contains(2, (item, elt) -> elt == Math.pow(item, 3)));

        ListIterator<Integer> li = al.listIterator();
        System.out.println(li.current());
        System.out.println(li.currentIndex());
        li.next();
        System.out.println(li.current());
        li.addBefore(-99);
        li.addAfter(-98);
        System.out.println(al);

        li = al.listIterator(al.size()-1);
        System.out.println(li.current());
        li.previous();
        System.out.println(li.current());
    }
}

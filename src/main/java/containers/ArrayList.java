package containers;

import java.util.Arrays;
import java.util.Objects;

public class ArrayList<E> extends MutableList<E> {
    private final Vector store = new Vector();

    public ArrayList() {}

    public ArrayList(E fillElt) {
        super(fillElt);
    }

    public ArrayList<E> makeEmptyList() {
        return new ArrayList<>(getFillElt());
    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
//        return store.isEmpty();
        return size() == 0;
    }

    @Override
    protected void doClear() {
        store.clear();
    }

    public Iterator<E> iterator() {
        return new MutableCollectionIterator<>(CursorFactory.makeRandomAccessListCursor(this),
                () -> modificationCount);
    }

    public ListIterator<E> listIterator(int start) {
        return new RandomAccessListListIterator<>(this, start, new RemoteControl().addCommand("modificationCount", () -> modificationCount));
    }
    @SuppressWarnings("unchecked")
    @Override
    protected ArrayList<E> doDoAdd(E... objs) {
//        for (E elt : objs) {
//            store.pushExtend(elt);
//        }
        store.addAll(objs);

        return this;
    }

    protected void shiftUp(int low) {
        shiftUp(low, size());
    }

    protected void shiftUp(int low, int high) {
        store.shiftUp(low, high);
    }

    protected void shiftDown(int low) {
        shiftDown(low, size());
    }

    protected void shiftDown(int low, int high) {
        store.shiftDown(low, high);
    }

    @Override
    protected void doDoInsert(int i, E obj) {
        store.pushExtend(getFillElt());
        shiftUp(i);
        set(i, obj);
    }

    @Override
    protected E doDoDelete(int i) {
        E doomed = get(i);
        shiftDown(i + 1);
        store.pop();

        return doomed;
    }

    @Override
    protected E doGet(int i) {
        return store.get(i);
    }

    @Override
    protected void doSet(int i, E obj) {
        store.set(i, obj);
    }

    @SuppressWarnings("unchecked")
//    @Override
//    protected List<E> doSlice(int i, int n) {
//        List<E> list = new ArrayList<>(getFillElt());
//        int start = Math.min(i, size());
//        int end = Math.min(i+n, size());
//        Object[] slice = new Object[end-start];
//
//        for (int j = 0, k = start; j < slice.length; j++, k++) {
//            slice[j] = get(k);
//        }
//        list.add((E[]) slice);
//
//        return list;
//    }

    protected class Vector {
        private static final int DEFAULT_INITIAL_SIZE = 20;
        private int active = 0;
        @SuppressWarnings("unchecked")
        private E[] v = (E[]) new Object[DEFAULT_INITIAL_SIZE];

        protected int size() {
            return active;
        }

        protected E get(int i) {
            Objects.checkIndex(i, active);
            return v[i];
        }

        protected void set(int i, E obj) {
            Objects.checkIndex(i, active);
            v[i] = obj;
        }

        protected boolean isEmpty() {
            return active == 0;
        }

        protected void clear() {
            for (int i = 0; i < active; i++) {
                v[i] = null;
            }

            active = 0;
        }

        protected void pushExtend(E obj) {
            if ( active == v.length ) {
                extend();
            }

            v[active++] = obj;
        }

        @SuppressWarnings("unchecked")
        protected void addAll(E... objs) {
            while ( active + objs.length > v.length ) {
                extend();
            }

            System.arraycopy(objs, 0, v, active, objs.length);
            active += objs.length;
        }
        protected E pop() {
            if ( isEmpty() ) {
                throw new IllegalStateException("Vector is empty.");
            } else {
                E doomed = v[active];
                v[active--] = null;
                return doomed;
            }
        }
        private void extend() {
            v = Arrays.copyOf(v, v.length * 2);
        }

        protected void shiftUp(int low, int high) {
            System.arraycopy(v, low, v, low+1, high-low);
        }

        protected void shiftDown(int low, int high) {
            System.arraycopy(v, low, v, low-1, high-low);
        }
    }

    public static void main(String[] args) {
        List<Integer> al = new ArrayList<>();
        al.add(2);
        al.add(4);
        al.add(6);
        al.add(8);

        System.out.println(al.delete(0));
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

        al = new ArrayList<>(0);
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

        al.clear();
        al.fill(20, n -> n + 1);
    }
}

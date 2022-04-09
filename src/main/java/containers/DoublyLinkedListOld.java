package containers;

public class DoublyLinkedListOld<E> {
//    public class DoublyLinkedListOld<E> extends List<E> {
//    protected Dcons<E> store = null;
//    private int count = 0;
//    private Dcursor<E> head = new Dcursor<>(this);
//    private Dcursor<E> cursor = new Dcursor<>(this);
//
//    public DoublyLinkedListOld() {}
//
//    public DoublyLinkedListOld(E fillElt) {
//        super(fillElt);
//    }
//
//    @Override
//    public int size() {
//        return count;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return store == null;
//    }
//
////    @Override
////    public void clear() {
////        store = null;  // Memory leak?!
////        count = 0;
////        head.reset();
////        cursor.reset();
////    }
////
//
//    @Override
//    public void clear() {
//        if ( !isEmpty() ) {
//            Dcons<E> dcons = store;
//            for (int i = 0; i < count; i++) {
//                dcons.setPrevious(null);
//                dcons = dcons.getNext();
//            }
//
////            store.setNext(null);
//            store = null;
//            count = 0;
//            head.reset();
//            cursor.reset();
//        }
//    }
//
//    @Override
//    public Iterator<E> iterator() {
//        return new DoublyLinkedListIterator<>(this);
//    }
//
//    /*
//     *    This is reasonable due to the cursors.
//     */
//    @Override
//    public boolean contains(E object) {
//        for (int i = 0; i < count; i++) {
//            if ( get(i).equals(object) ) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    @Override
////    @SafeVarargs // ????
////    public final void add(E... objs) {
//    @SuppressWarnings("unchecked")
//    public void add(E... objs) {
//        if (objs.length > 0) {
//            Dcons<E> dcons = new Dcons<>(objs[0]);
//
//            if ( isEmpty() ) {
//                store = dcons;
//            } else {
//                Dcons<E> tail = store.getPrevious();
//                Dcons.link(tail, dcons);
//            }
//
//            for (int i = 1; i < objs.length; i++) {
//                Dcons.link(dcons, new Dcons<>(objs[i]));
//                dcons = dcons.getNext();
//            }
//
//            Dcons.link(dcons, store);
//            count += objs.length;
//
//            if ( cursor.node == null ) {
//                cursor.reset();
//            }
//        }
//    }
//
//    /*
//     *    Determine the quickest way to locate the ith node. The floating cursor `cursor` is at its current position
//     *    following a previous operation. The `head` cursor begins at index 0.
//     *    Other methods that rely on this may accept negative indexes, but they must be offset here.
//     */
//    private Dcons<E> nthDcons(int i) {
//        if ( i < 0  ||  i >= count ) {
//            throw new IndexOutOfBoundsException("Invalid index: " + i);
//        } else if ( isEmpty() ) {
//            throw new IllegalStateException("List is empty.");
//        } else {
//            int c = cursor.index;
//
//            if ( i == c ) {
//                return cursor.node;
//            } else if ( i == 0 ) {
//                return store;
//            } else if ( i < c / 2 ) {
//                head.reset();
//                head.advance(i);
//                return head.node;
//            } else if ( i < c ) {
//                cursor.rewind(c - i);
//                return cursor.node;
//            } else if ( i <= (count + c) / 2 ) {
//                cursor.advance(i - c);
//                return cursor.node;
//            } else if ( c == 0 ) {
//                cursor.rewind(count - i);
//                return cursor.node;
//            } else {
//                head.reset();
//                head.rewind(count - i);
//                return head.node;
//            }
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public void doInsert(int i, E obj) {
//        Dcons<E> newDcons = new Dcons<>(obj);
//
//        if (i == 0) {
//            if ( isEmpty() ) {
//                Dcons.link(newDcons, newDcons);
//            } else {
//                Dcons.link(store.getPrevious(), newDcons);
//                Dcons.link(newDcons, store);
//            }
//
//            store = newDcons;
//        } else {
//            Dcons<E> dcons = nthDcons(i);
//
//            Dcons.link(dcons.getPrevious(), newDcons);
//            Dcons.link(newDcons, dcons);
//        }
//
//        count++;
//
//        if ( cursor.node == null  ||
//                isBetweenInclusive(i, 0, cursor.index)  ||
//                (i < 0  &&  isBetweenInclusive(i + count, 0, cursor.index)) ) {
//            cursor.reset();
//        }
//    }
//
//    private static boolean isBetweenInclusive(int i, int low, int high) {
//        return low <= i && i <= high;
//    }
//
//    @Override
//    protected E doDelete(int i) {
//        if (i == 0) {
//            E doomed = store.getContent();
//
//            if (store == store.getNext()) {
//                store = null;
//            } else {
//                Dcons<E> newStore = store.getNext();
//                Dcons.link(store.getPrevious(), newStore);
//                store = newStore;
//            }
//            count--;
//
//            cursor.reset();
//            return doomed;
//        } else {
//            Dcons<E> dcons = nthDcons(i);
//            E doomed = dcons.getContent();
//            Dcons.link(dcons.getPrevious(), dcons.getNext());
//            count--;
//
//            cursor.reset();
//            return doomed;
//        }
//    }
//
//    @Override
//    public E doGet(int i) {
//        return nthDcons(i).getContent();
//    }
//
//    @Override
//    public void doSet(int i, E obj) {
//        nthDcons(i).setContent(obj);
//    }
//
//    /*
//     *    This is reasonable due to the cursors.
//     */
//    @Override
//    public int index(E obj) {
//        for (int i = 0; i < count; i++) {
//            if ( get(i).equals(obj) ) {
//                return i;
//            }
//        }
//
//        return NOT_PRESENT;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    protected List<E> doSlice(int i, int n) {
//        DoublyLinkedListOld<E> list = new DoublyLinkedListOld<>(getFillElt());
//        list.add((E[]) subseq(Math.min(i, count), Math.min(i+n, count)));
//
//        return list;
//    }
//
////    protected Object[] subseq(int start, int end) {
////        Object[] result = new Object[end - start];
//////        Dcons<E> dcons = nthDcons(start);
////
////        for (int i = start, j = 0; i < end; i++, j++) {
////            result[j] = get(i);
////        }
////
////        return result;
////    }
//
//    @SuppressWarnings("unchecked")
//    protected Object[] subseq(int start, int end) {
//        Object[] slice = new Object[end - start];
//
//        if ( start < end ) {
//            Dcons<E> dcons = nthDcons(start);
//
//            for (int i = 0; i < slice.length; i++) {
//                // Check!!!
////        if ( head == null ) {
////            throw new IndexOutOfBoundsException("End of list reached.");
////        }
//                slice[i] = dcons.getContent();
//                dcons = dcons.getNext();
//            }
//        }
//
//        return (E[]) slice;
//    }
//
//    public static void main(String[] args) {
//        DoublyLinkedListOld<Integer> dll = new DoublyLinkedListOld<>(0);
//
//        dll.add(2, 4, 6, 8);
//
//        System.out.println(dll.size());
//        System.out.println(dll.get(0));
//        System.out.println(dll.get(1));
//        System.out.println(dll.get(2));
//        System.out.println(dll.get(3));
//
//        System.out.println(dll.get(-1));
//        System.out.println(dll.get(-4));
//
//        dll.set(0, 0);
//        dll.set(-1, 9);
//        dll.set(6, 11);
//        System.out.println(dll);
//
//        System.out.println(dll.get(0));
//        System.out.println(dll.get(-1));
//        System.out.println(dll.get(6));
//        System.out.println(dll.get(5));
//        System.out.println(dll.get(3));
//
//        dll.clear();
//
//        dll.insert(0, 0);
//        dll.insert(3, 3);
//        dll.insert(1, 1);
//        System.out.println(dll);
//
//        System.out.println(dll.get(0));
//        System.out.println(dll.get(-1));
//        System.out.println(dll.get(-2));
//        System.out.println(dll.get(1));
//
//        dll.clear();
//        dll.add(0, 1, 2, 3, 4, 5);
//        System.out.println(dll);
//
//        System.out.println(dll.delete(0));
//        System.out.println(dll.delete(3));
//        System.out.println(dll.delete(-1));
//        System.out.println(dll.delete(0));
//
//        dll.clear();
//        dll.add(1, 3, 5, 7, 9);
//
//        System.out.println(dll.contains(3));
//        System.out.println(dll.contains(8));
//        System.out.println(dll.index(7));
//        System.out.println(dll.index(4));
//
//        dll.each(integer -> System.out.println(integer));
//        System.out.println(dll);
//
//        System.out.println(dll.slice(0, 3));
//        System.out.println(dll.slice(-3, 3));
//    }
}

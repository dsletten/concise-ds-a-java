package containers;

public class SinglyLinkedList<E> extends MutableLinkedList<E> {
    private Node<E> store = null;
    private int count = 0;

    public SinglyLinkedList() {
        super();
    }

    @Override
    public List<E> makeEmptyList() {
        return new SinglyLinkedList<>(getFillElt());
    }

    public SinglyLinkedList(E fillElt) {
        super(fillElt);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return store == null;
    }

    @Override
    public void doClear() {
        store = null;
        count = 0;
    }

    @Override
//    public Iterator<E> iterator() {
//        return new SinglyLinkedListIterator();
//    }
//    public IteratorRC<E> iterator() {
//        return new MutableCollectionIterator<E>(
//                new RemoteControl<>() {
//                    Node<E> cursor = store;
//
//                    @Override
//                    public boolean checkDone() {
//                        return cursor != null;
//                    }
//
//                    @Override
//                    public E currentElement() {
//                        return cursor.first();
//                    }
//
//                    @Override
//                    public void nextElement() {
//                        cursor = cursor.rest();
//                    }
//                }, () -> getModificationCount());
//    }
//    public Iterator<E> iterator() {
//        return new MutableCollectionIterator<>(new Cursor<E>() {
//            private Node<E> cursor = store;
//
//            @Override
//            public boolean isDone() {
//                return cursor == null;
//            }
//
//            @Override
//            public E current() {
//                return cursor.first();
//            }
//
//            @Override
//            public void advance() {
//                cursor = cursor.rest();
//            }
//        }, () -> getModificationCount());
//    }
    public Iterator<E> iterator() {
        return new MutableCollectionIterator<>(CursorFactory.makeSinglyLinkedListCursor(store),
                () -> modificationCount);
    }

    public ListIterator<E> listIterator(int start) {
        return new SinglyLinkedListListIterator<>(this, start,
                new RemoteControl().addCommand("modificationCount", () -> modificationCount).addCommand("headNode", () -> store));
    }

//    @Override
//    public E contains(E object, BiPredicate<E, E> equalityTest) {
//        return Node.contains(store, object, equalityTest);
//    }

    @Override
//    @SafeVarargs // ????
//    public final void add(E... objs) {
    @SuppressWarnings("unchecked")
    public SinglyLinkedList<E> doDoAdd(E... objs) {
        Node<E> node = null;

        for (int i = objs.length - 1; i >= 0; i--) {
            node = new Node<>(objs[i], node);
        }

        if ( isEmpty() ) {
            store = node;
        } else {
            store.last().setRest(node);
        }

        count += objs.length;

        return this;
    }

    @Override
    public void doDoInsert(int i, E obj) {
//        if (i == 0) {
//            store = new Node<>(obj, store);
//        } else {
//            Node<E> head = Node.nthCdr(store, i - 1);
//            head.setRest(new Node<>(obj, head.rest()));
//        }
        //noinspection ConstantConditions
        Node.nthCdr(store, i).spliceBefore(obj);

        count++;
    }

    @Override
    protected void doInsertBefore(LinkedNode<E> node, E obj) {
        node.spliceBefore(obj);
        count++;
    }

    @Override
    protected void doInsertAfter(LinkedNode<E> node, E obj) {
        node.spliceAfter(obj);
        count++;
    }

    @Override
    protected E doDoDelete(int i) {
        E doomed;
        if (i == 0) {
            doomed = store.first();
            store = store.rest();
        } else {
//            Node<E> head = Node.nthCdr(store, i - 1);
//            E doomed = head.rest().first();
//            head.setRest(head.rest().rest());
            //noinspection ConstantConditions
            doomed = Node.nthCdr(store, i - 1).exciseChild();
        }

        count--;
        return doomed;
    }

    @Override
    protected E doDeleteNode(LinkedNode<E> doomed) {
        if (doomed == store) {
            store = store.rest();
        } else {
            doomed.exciseNode();
        }

        count--;
        return ((Node<E>) doomed).first();
    }

    @Override
    protected E doDeleteChild(LinkedNode<E> parent) {
        count--;
        return parent.exciseChild();
    }

    @Override
    public E doGet(int i) {
        return Node.nth(store, i);
    }

    @Override
    public void doSet(int i, E obj) {
        Node.setNth(store, i, obj);
    }

//    @Override
//    public int index(E obj, BiPredicate<E, E> test) {
//        return Node.index(store, obj, test);
//    }

    @Override
    protected List<E> doSlice(int i, int n) {
        List<E> list = new SinglyLinkedList<>(getFillElt());
        list.add(Node.slice(store, Math.min(i, count), Math.min(i+n, count)));

        return list;
    }

    //
    //    As an inner class, this provides access to `store`, but this
    //    arrangement precludes sharing with SinglyLinkedListX...
    //
//    private class SinglyLinkedListIterator extends MutableListIterator<E> {
//        private Node<E> cursor = store;
//
//        //    Shouldn't give access to enclosing list?!
//        //    Tension between inner class and code reuse!!
//        private SinglyLinkedListIterator() {
//            super(SinglyLinkedList.this);
//        }
//
//        @Override
//        protected boolean doIsDone() {
//            return cursor == null;
//        }
//
//        @Override
//        protected E doDoCurrent() {
//            return cursor.first();
//        }
//
//        @Override
//        protected void doNext() {
//            if ( !isDone() ) {
//                cursor = cursor.rest();
//            }
//        }
//    }

    public static void main(String[] args) {
        List<Integer> sll = new SinglyLinkedList<>(0);
        sll.add(2, 4, 6, 8);
        System.out.println(sll);

        sll.insert(0, 5);
        sll.insert(-1, 11);
        sll.insert(8, 22);
        System.out.println(sll);

        System.out.println(sll.contains(11));
        System.out.println(sll.contains(12));
        System.out.println(sll.index(11));
        System.out.println(sll.index(12));

        for (int i = 0; i < sll.size(); i++) {
            System.out.print(sll.get(i) + " ");
        }
        System.out.println();
        for (int i = 1; i <= sll.size(); i++) {
            System.out.print(sll.get(-i) + " ");
        }
        System.out.println();

        sll.set(0, 0);
        sll.set(15, -9);
        System.out.println(sll);

        System.out.println(sll.slice(0, 3));
        System.out.println(sll.slice(-12, 3));
        System.out.println(sll.slice(-3, 5));
        System.out.println(sll.slice(-20, 3));

//        sll.each(integer -> System.out.println(integer));
        sll.each(System.out::println);

        ListIterator<Integer> li = sll.listIterator();
        System.out.println(li.current());
        System.out.println(li.currentIndex());
        li.next();
        System.out.println(li.current());
        li.addBefore(-99);
        li.addAfter(-98);
        System.out.println(sll);

        li = sll.listIterator(sll.size()-1);
        System.out.println(li.current());
        li.previous();
        System.out.println(li.current());
    }
}

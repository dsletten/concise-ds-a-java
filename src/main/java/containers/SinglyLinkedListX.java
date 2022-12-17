package containers;

/*
 * Variant of SinglyLinkedList which holds a reference to last node of list for efficiency.
 */
public class SinglyLinkedListX<E> extends MutableLinkedList<E> {
    private Node<E> front = null;
    private Node<E> rear = null;
    private int count = 0;

    public SinglyLinkedListX() {
        super();
    }

    public SinglyLinkedListX(E fillElt) {
        super(fillElt);
    }

    @Override
    protected List<E> makeEmptyList() {
        return new SinglyLinkedListX<>(getFillElt());
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public void doClear() {
        front = null;
        rear = null;
        count = 0;
    }

    @Override
//    public Iterator<E> iterator() {
//        return new SinglyLinkedListIterator();
//    }
//    public Iterator<E> iterator() {
//        return new MutableCollectionIterator<>(new Cursor<E>() {
//            private Node<E> cursor = front;
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
        return new MutableCollectionIterator<>(CursorFactory.makeSinglyLinkedListCursor(front),
                () -> modificationCount);
    }

    public ListIterator<E> listIterator(int start) {
        return new SinglyLinkedListListIterator<>(this, start,
                new RemoteControl().addCommand("modificationCount", () -> modificationCount).addCommand("headNode", () -> front));
    }

//    @Override
//    public E contains(E object, BiPredicate<E, E> equalityTest) {
//        return Node.contains(front, object, equalityTest);
//    }

    @Override
//    @SafeVarargs // ????
//    public final void add(E... objs) {
    @SuppressWarnings("unchecked")
    public SinglyLinkedListX<E> doDoAdd(E... objs) {
        if ( isEmpty() ) {
            rear = front = new Node<>(objs[0], null);
            addNodes(objs, 1);
        } else {
            addNodes(objs, 0);
        }

        count += objs.length;

        return this;
    }

    private void addNodes(E[] objs, int start) {
        for (int i = start; i < objs.length; i++) {
            Node<E> node = new Node<>(objs[i], null);
            rear.setRest(node);
            rear = node;
        }
    }

    @Override
    public void doDoInsert(int i, E obj) {
        Node<E> node = Node.nthCdr(front, i);
        assert node != null;
        node.spliceBefore(obj);

        if ( node == rear ) {
            rear = rear.rest();
        }

        count++;
    }

    @Override
    protected void doInsertBefore(LinkedNode<E> node, E obj) {
        node.spliceBefore(obj);

        if ( node == rear ) {
            rear = rear.rest();
        }

        count++;
    }

    @Override
    protected void doInsertAfter(LinkedNode<E> node, E obj) {
        node.spliceAfter(obj);

        if ( node == rear ) {
            rear = rear.rest();
        }

        count++;
    }

    @Override
    protected E doDoDelete(int i) {
        E doomed;
        if (i == 0) {
            doomed = front.first();
            front = front.rest();

            if ( front == null ) {
                rear = null;
            }
        } else {
            Node<E> parent = Node.nthCdr(front, i - 1);
            assert parent != null;
            doomed = parent.exciseChild();

            if ( parent.rest() == null ) {
                rear = parent;
            }
        }

        count--;
        return doomed;
    }

    @Override
    protected E doDeleteNode(LinkedNode<E> doomed) {
        E result;

        if (doomed == front) {
            result = front.first();
            front = front.rest();

            if ( front == null ) {
                rear = null;
            }
        } else {
            result = doomed.exciseNode();

            if ( ((Node<E>) doomed).rest() == null ) {
                rear = (Node<E>) doomed;
            }
        }

        count--;
        return result;
    }

    @Override
    protected E doDeleteChild(LinkedNode<E> parent) {
        E doomed = parent.exciseChild();

        if ( ((Node<E>) parent).rest() == null ) {
            rear = (Node<E>) parent;
        }

        count--;
        return doomed;
    }

    @Override
    public E doGet(int i) {
        return Node.nth(front, i);
    }

    @Override
    public void doSet(int i, E obj) {
        Node.setNth(front, i, obj);
    }

//    @Override
//    public int index(E obj, BiPredicate<E, E> test) {
//        return Node.index(front, obj, test);
//    }

    @Override
    protected List<E> doSlice(int i, int n) {
        List<E> list = new SinglyLinkedListX<>(getFillElt());
        list.add(Node.slice(front, Math.min(i, count), Math.min(i+n, count)));

        return list;
    }

//    private class SinglyLinkedListIterator extends Iterator<E> {
//        private Node<E> cursor = front;
//
//        @Override
//        public boolean isDone() {
//            return cursor == null;
//        }
//
//        @Override
//        protected E doCurrent() {
//            return cursor.first();
//        }
//
//        @Override
//        public void next() {
//            if ( !isDone() ) {
//                cursor = cursor.rest();
//            }
//        }
//    }

    public static void main(String[] args) {
        List<Integer> sllx = new SinglyLinkedListX<>(0);
        sllx.add(2, 4, 6, 8);
        System.out.println(sllx);

        sllx.insert(0, 5);
        sllx.insert(-1, 11);
        sllx.insert(8, 22);
        System.out.println(sllx);

        System.out.println(sllx.contains(11));
        System.out.println(sllx.contains(12));
        System.out.println(sllx.index(11));
        System.out.println(sllx.index(12));

        for (int i = 0; i < sllx.size(); i++) {
            System.out.print(sllx.get(i) + " ");
        }
        System.out.println();
        for (int i = 1; i <= sllx.size(); i++) {
            System.out.print(sllx.get(-i) + " ");
        }
        System.out.println();

        sllx.set(0, 0);
        sllx.set(15, -9);
        System.out.println(sllx);

        System.out.println(sllx.slice(0, 3));
        System.out.println(sllx.slice(-12, 3));
        System.out.println(sllx.slice(-3, 5));
        System.out.println(sllx.slice(-20, 3));

        sllx.each(System.out::println);

        ListIterator<Integer> li = sllx.listIterator();
        System.out.println(li.current());
        System.out.println(li.currentIndex());
        li.next();
        System.out.println(li.current());
        li.addBefore(-99);
        li.addAfter(-98);
        System.out.println(sllx);

        li = sllx.listIterator(sllx.size()-1);
        System.out.println(li.current());
        li.previous();
        System.out.println(li.current());
    }
}

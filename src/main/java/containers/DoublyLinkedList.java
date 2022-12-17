package containers;

public class DoublyLinkedList<E> extends MutableLinkedList<E> {
    protected Dcons<E> store = null;
    private int count = 0;
    private final Dcursor cursor = new Dcursor();
    //    Alternative Dcursor with RemoteControl
//    private Dcursor cursor = setupCursor();

    public DoublyLinkedList() {}

    public DoublyLinkedList(E fillElt) {
        super(fillElt);
    }

    @Override
    protected List<E> makeEmptyList() {
        return new DoublyLinkedList<>(getFillElt());
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
        if ( !isEmpty() ) {
            Dcons<E> dcons = store;
            for (int i = 0; i < count; i++) {
                dcons.setPrevious(null);
                dcons = dcons.getNext();
            }

            store.setNext(null);
            store = null;
            count = 0;
            cursor.reset();
        }
    }

    @Override
//    public Iterator<E> iterator() {
//        return new DoublyLinkedListIterator();
//    }
//    public Iterator<E> iterator() {
//        return new MutableCollectionIterator<>(new Cursor<E>() {
//            private Dcons<E> node = store;
//            private boolean sealedForYourProtection = true;
//
//            @Override
//            public boolean isDone() {
//                return isEmpty() || (!sealedForYourProtection && node == store);
//            }
//
//            @Override
//            public E current() {
//                return node.getContent();
//            }
//
//            @Override
//            public void advance() {
//                if (!isDone()) {
//                    node = node.getNext();
//                    sealedForYourProtection = false;
//                }
//            }
//        }, () -> getModificationCount());
//    }
    public Iterator<E> iterator() {
        return new MutableCollectionIterator<>(new Cursor<>() {
            private final Dcursor dcursor = new Dcursor();
            //            private Dcursor dcursor = setupCursor();
            private boolean sealedForYourProtection = true;

            @Override
            public boolean isDone() {
                return isEmpty() || (!sealedForYourProtection && dcursor.atStart());
            }

            @Override
            public E current() {
                return dcursor.node.getContent();
            }

            @Override
            public void advance() {
                if (!isDone()) {
                    dcursor.advance();
                    sealedForYourProtection = false;
                }
            }
        }, () -> modificationCount);
    }

    public ListIterator<E> listIterator(int start) {
        return new RandomAccessListListIterator<>(this, start, new RemoteControl().addCommand("modificationCount", () -> modificationCount));
    }

    //    Alternative Dcursor
//    private Dcursor setupCursor() {
//        return new Dcursor(new RemoteControl().addCommand("headNode", () -> store).addCommand("size", () -> count));
//    }
    /*
     *    This is reasonable due to the cursors.
     */
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

//    @Override
//    public E contains(E object, BiPredicate<E, E> equalityTest) {
//        Dcons<E> dcons = store;
//        for (int i = 0; i < count; i++) {
//            if ( equalityTest.test(object, dcons.getContent()) ) {
//                return dcons.getContent();
//            }
//
//            dcons = dcons.getNext();
//        }
//
//        return null;
//    }

    @SuppressWarnings("unchecked")
    @Override
    public DoublyLinkedList<E> doDoAdd(E... objs) {
        Dcons<E> dcons = new Dcons<>(objs[0]);

        if (isEmpty()) {
            store = dcons;
        } else {
            store.getPrevious().link(dcons);
        }

        addNodes(dcons, objs);

        if (!cursor.isInitialized()) {
            cursor.reset();
        }

        return this;
    }

    private void addNodes(Dcons<E> start, E[] elts) {
        Dcons<E> dcons = start;
        for (int i = 1; i < elts.length; i++) {
            dcons.link(new Dcons<>(elts[i]));
            dcons = dcons.getNext();
        }

        dcons.link(store);
        count += elts.length;
    }

    /*
     *    Determine the quickest way to locate the ith node. The floating cursor `cursor` is at its current position
     *    following a previous operation. The `head` cursor begins at index 0.
     *    Other methods that rely on this may accept negative indexes, but they must be offset here.
     */
//    private Dcons<E> nthDcons(int i) {
//        if ( i < 0  ||  i >= count ) {
//            throw new IndexOutOfBoundsException("Invalid index: " + i);
//        } else if ( isEmpty() ) {
//            throw new IllegalStateException("List is empty.");
//        } else {
//            int c = cursor.index;
//
//            if ( i == 0 ) {
//                return store;
//            } else if ( i == c ) {
//                return cursor.node;
//            } else if ( i < c / 2 ) {
//                cursor.reset();
//                cursor.advance(i);
//                return cursor.node;
//            } else if ( i < c ) {
//                cursor.rewind(c - i);
//                return cursor.node;
//            } else if ( i <= (count + c) / 2 ) {
//                cursor.advance(i - c);
//                return cursor.node;
//            } else {
//                cursor.reset();
//                cursor.rewind(count - i);
//                return cursor.node;
//            }
//        }
//    }

    private Dcons<E> nthDcons(int i) {
        if ( isEmpty() ) {
            throw new IllegalStateException("List is empty.");
        } else if ( i < 0  ||  i >= count ) {
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        } else {
            repositionCursor(i);

            return cursor.node;
        }
    }

    private void repositionCursor(int i) {
        if ( i == 0 ) {
            cursor.reset();
        } else if ( i < cursor.index ) {
            int indexDelta = cursor.index - i;

            if ( i < indexDelta ) {
                cursor.reset();
                cursor.advance(i);
            } else {
                cursor.rewind(indexDelta);
            }
        } else if ( i > cursor.index ) {
            int indexDelta = i - cursor.index;
            int countDelta = count - i;

            if ( indexDelta <= countDelta ) {
                cursor.advance(indexDelta);
            } else {
                cursor.reset();
                cursor.rewind(countDelta);
            }
        }
    }

    @Override
    public void doDoInsert(int i, E obj) {
        nthDcons(i).spliceBefore(obj);

        if ( i == 0 ) {
            store = store.getPrevious();
        }

        count++;

        if ( !cursor.isInitialized()  ||
                isBetweenInclusive(i, 0, cursor.index)  ||
                (i < 0  &&  isBetweenInclusive(i + count, 0, cursor.index)) ) {
            cursor.reset();
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static boolean isBetweenInclusive(int i, int low, int high) {
        return low <= i && i <= high;
    }

    @Override
    protected E doDoDelete(int i) {
        E doomed = deleteDcons(nthDcons(i));
        count--;
        cursor.reset();

        return doomed;
//        if (i == 0) {
//            E doomed = store.getContent();
//
//            if (store == store.getNext()) {
//                store = null;
//            } else {
//                Dcons<E> newStore = store.getNext();
//                store.getPrevious().link(newStore);
//                store = newStore;
//            }
//            count--;
//
//            cursor.reset();
//            return doomed;
//        } else {
//            Dcons<E> dcons = nthDcons(i);
//            E doomed = dcons.getContent();
//            dcons.getPrevious().link(dcons.getNext());
//            count--;
//
//            cursor.reset();
//            return doomed;
//        }
    }

    private E deleteDcons(Dcons<E> node) {
        if ( node == node.getNext() ) {
            E doomed = node.getContent();
            node.setNext(null);
            store = null;

            return doomed;
        } else {
            E doomed = node.exciseNode();

            if (node == store) {
                store = node.getNext();
            }

            return doomed;
        }
    }

    @Override
    protected void doInsertBefore(LinkedNode<E> node, E obj) {
        node.spliceBefore(obj);

        if ( node == store ) {
            store = store.previous;
        }

        count++;
        cursor.reset();
    }

    @Override
    protected void doInsertAfter(LinkedNode<E> node, E obj) {
        node.spliceAfter(obj);
        count++;
        cursor.reset();
    }

    @Override
    protected E doDeleteNode(LinkedNode<E> node) {
        E doomed = deleteDcons((Dcons<E>) node);
        count--;
        cursor.reset();

        return doomed;
    }

    @Override
    protected E doDeleteChild(LinkedNode<E> parent) {
        Dcons<E> child = ((Dcons<E>) parent).getNext();

        if ( child == store ) {
            throw new IllegalStateException("Parent must have child node");
        } else {
            E doomed = parent.exciseChild();
            count--;
            cursor.reset();

            return doomed;
        }
    }

    @Override
    public E doGet(int i) {
        return nthDcons(i).getContent();
    }

    @Override
    public void doSet(int i, E obj) {
        nthDcons(i).setContent(obj);
    }

    /*
     *    This is reasonable due to the cursors.
     */
//    @Override
//    public int index(E obj, BiPredicate<E, E> test) {
//        for (int i = 0; i < count; i++) {
////            if ( get(i).equals(obj) ) {
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
        DoublyLinkedList<E> list = new DoublyLinkedList<>(getFillElt());
        list.add((E[]) subseq(Math.min(i, count), Math.min(i+n, count)));

        return list;
    }

//    protected Object[] subseq(int start, int end) {
//        Object[] result = new Object[end - start];
////        Dcons<E> dcons = nthDcons(start);
//
//        for (int i = start, j = 0; i < end; i++, j++) {
//            result[j] = get(i);
//        }
//
//        return result;
//    }

    protected Object[] subseq(int start, int end) {
        Object[] slice = new Object[end - start];

        if ( start < end ) {
            Dcons<E> dcons = nthDcons(start);

            for (int i = 0; i < slice.length; i++) {
                // Check!!!
//        if ( head == null ) {
//            throw new IndexOutOfBoundsException("End of list reached.");
//        }
                slice[i] = dcons.getContent();
                dcons = dcons.getNext();
            }
        }

        return slice;
    }

//    private static class Dcons<E> {
    protected static class Dcons<E> implements LinkedNode<E> {
        private E content;
        private Dcons<E> previous = null;
        private Dcons<E> next = null;

        public Dcons(E content) {
            this.content = content;
        }

        protected E getContent() {
            return content;
        }

        protected void setContent(E content) {
            this.content = content;
        }

        protected Dcons<E> getPrevious() {
            return previous;
        }

        protected void setPrevious(Dcons<E> previous) {
            this.previous = previous;
        }

        protected Dcons<E> getNext() {
            return next;
        }

        protected void setNext(Dcons<E> next) {
            this.next = next;
        }

        private void link(Dcons<E> next) {
            this.setNext(next);
            next.setPrevious(this);
        }

//        private void spliceBefore(E obj) {
        public void spliceBefore(E obj) {
            Dcons<E> newDcons = new Dcons<>(obj);
            getPrevious().link(newDcons);
            newDcons.link(this);
        }

//        private void spliceAfter(E obj) {
        public void spliceAfter(E obj) {
            Dcons<E> newDcons = new Dcons<>(obj);
            newDcons.link(getNext());
            link(newDcons);
        }

//        private E exciseNode() {
        public E exciseNode() {
            if ( this == getNext() ) {
                throw new IllegalStateException("Cannot delete sole node.");
            } else {
                getPrevious().link(getNext());
            }

            return getContent();
        }

//        private E exciseChild() {
        public E exciseChild() {
            Dcons<E> child = getNext();

            if ( this == child ) {
                throw new IllegalStateException("Parent must have child node.");
            } else {
                link(child.getNext());
            }

            return child.getContent();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("<");
            printPrevious(sb);
            sb.append(content);
            printNext(sb);
            sb.append(">");

            return sb.toString();
        }

        private void printPrevious(StringBuilder sb) {
            if ( previous == null ) {
                sb.append("∅ ← ");
            } else if ( this == previous ) {
                sb.append("↻ ");
            } else {
                sb.append(previous.content).append(" ← ");
            }
        }

        private void printNext(StringBuilder sb) {
            if ( next == null ) {
                sb.append(" → ∅");
            } else if ( this == next ) {
                sb.append(" ↺");
            } else {
                sb.append(" → ").append(next.content);
            }
        }
    }

    //
    //    Inner class. Can refer to its associated List.
    //
    class Dcursor {
        private Dcons<E> node;
        private int index = 0;

        protected Dcursor() {
            node = store;
        }

        @SuppressWarnings("BooleanMethodIsAlwaysInverted")
        protected boolean isInitialized() {
            return node != null;
        }

        protected boolean atStart() {
            return !isInitialized()  ||  index == 0;
        }

        protected boolean atEnd() {
            return !isInitialized()  ||  index == size() - 1;
        }

        protected void reset() {
            node = store;
            index = 0;
        }

        protected void advance() {
            advance(1);
        }

        // Broken??? Possibly still not initialized? ????????????????
        protected void advance(int step) {
            if ( !isInitialized() ) {
                reset();
//                throw new IllegalStateException("Cursor has not been initialized.");
            } //else {

            doAdvance(step);
        }

        protected void doAdvance(int step) {
            for (int i = 0; i < step; i++) {
                index++;
                node = node.getNext();
            }

            index %= size();
        }

        protected void rewind() {
            rewind(1);
        }

        // Broken??? Possibly still not initialized? ????????????????
        protected void rewind(int step) {
            if ( !isInitialized() ) {
                reset();
//                throw new IllegalStateException("Cursor has not been initialized.");
            } //else {
            doRewind(step);
        }

        protected void doRewind(int step) {
            for (int i = 0; i < step; i++) {
                index--;
                node = node.getPrevious();
            }

            index %= size();
        }

        // Broken??? Possibly still not initialized? ????????????????
        protected void bump() {
            if ( !isInitialized() ) {
                reset();
//                throw new IllegalStateException("Cursor has not been initialized.");
            } //else {
            doBump();
        }

        protected void doBump() {
            node = node.getNext();
        }

        protected void nudge() {
            if ( !isInitialized() ) {
                reset();
            }
            doNudge();
        }

        protected void doNudge() {
            index++;
        }

// ??????????????????
        protected int getIndex() {
            return index;
        }

        protected Dcons<E> getNode() {
            return node;
        }
    }

//    //
//    //    Don't need to jump through all of the hoops with Java due to inner class above.
//    //    But this class could be externalized.
//    //
//    private class Dcursor {
//        private Dcons<E> node;
//        private int index = 0;
//        private RemoteControl remoteControl;
//
//        protected Dcursor(RemoteControl remoteControl) {
//            this.remoteControl = remoteControl;
//            node = (Dcons<E>) remoteControl.press("headNode");
//        }
//
//        protected boolean isInitialized() {
//            return node != null;
//        }
//
//        protected boolean atStart() {
//            return !isInitialized()  ||  index == 0;
//        }
//
//        protected boolean atEnd() {
//            return !isInitialized()  ||  index == (Integer) remoteControl.press("size") - 1;
//        }
//
//        protected void reset() {
//            node = (Dcons<E>) remoteControl.press("headNode");
//            index = 0;
//        }
//
//        protected void advance() {
//            advance(1);
//        }
//
//        // Broken??? Possibly still not initialized? ????????????????
//        protected void advance(int step) {
//            if ( !isInitialized() ) {
//                reset();
////                throw new IllegalStateException("Cursor has not been initialized.");
//            } //else {
//
//            doAdvance(step);
//        }
//
//        protected void doAdvance(int step) {
//            for (int i = 0; i < step; i++) {
//                index++;
//                node = node.getNext();
//            }
//
//            index %= (Integer) remoteControl.press("size");
//        }
//
//        protected void rewind() {
//            rewind(1);
//        }
//
//        // Broken??? Possibly still not initialized? ????????????????
//        protected void rewind(int step) {
//            if ( !isInitialized() ) {
//                reset();
////                throw new IllegalStateException("Cursor has not been initialized.");
//            } //else {
//            doRewind(step);
//        }
//
//        protected void doRewind(int step) {
//            for (int i = 0; i < step; i++) {
//                index--;
//                node = node.getPrevious();
//            }
//
//            index %= (Integer) remoteControl.press("size");
//        }
//
//        // Broken??? Possibly still not initialized? ????????????????
//        protected void bump() {
//            if ( !isInitialized() ) {
//                reset();
////                throw new IllegalStateException("Cursor has not been initialized.");
//            } //else {
//            doBump();
//        }
//
//        protected void doBump() {
//            node = node.getNext();
//        }
//
//        protected void nudge() {
//            if ( !isInitialized() ) {
//                reset();
//            }
//            doNudge();
//        }
//
//        protected void doNudge() {
//            index++;
//        }
//
////        protected int getIndex() {
////            return index;
////        }
////
////        protected Dcons<E> getNode() {
////            return node;
////        }
//    }

//    private class DoublyLinkedListIterator extends Iterator<E> {
//        private Dcons<E> node = store;
//        private boolean sealedForYourProtection = true;
//
//        @Override
//        public boolean isDone() {
//            return isEmpty()  ||  (!sealedForYourProtection  &&  node == store);
//        }
//
//        @Override
//        protected E doCurrent() {
//            return node.getContent();
//        }
//
//        @Override
//        public void next() {
//            if ( !isDone() ) {
//                node = node.getNext();
//                sealedForYourProtection = false;
//            }
//        }
//    }


    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>(0);

        dll.add(2, 4, 6, 8);

        System.out.println(dll.size());
        System.out.println(dll.get(0));
        System.out.println(dll.get(1));
        System.out.println(dll.get(2));
        System.out.println(dll.get(3));

        System.out.println(dll.get(-1));
        System.out.println(dll.get(-4));

        dll.set(0, 0);
        dll.set(-1, 9);
        dll.set(6, 11);
        System.out.println(dll);

        System.out.println(dll.get(0));
        System.out.println(dll.get(-1));
        System.out.println(dll.get(6));
        System.out.println(dll.get(5));
        System.out.println(dll.get(3));

        dll.clear();

        dll.insert(0, 0);
        dll.insert(3, 3);
        dll.insert(1, 1);
        System.out.println(dll);

        System.out.println(dll.get(0));
        System.out.println(dll.get(-1));
        System.out.println(dll.get(-2));
        System.out.println(dll.get(1));

        dll.clear();
        dll.add(0, 1, 2, 3, 4, 5);
        System.out.println(dll);

        System.out.println(dll.delete(0));
        System.out.println(dll.delete(3));
        System.out.println(dll.delete(-1));
        System.out.println(dll.delete(0));

        dll.clear();
        dll.add(1, 3, 5, 7, 9);

        System.out.println(dll.contains(3));
        System.out.println(dll.contains(8));
        System.out.println(dll.index(7));
        System.out.println(dll.index(4));

        dll.each(System.out::println);
        System.out.println(dll);

        System.out.println(dll.slice(0, 3));
        System.out.println(dll.slice(-3, 3));

        Dcons<Integer> d1 = new Dcons<>(1);
        System.out.println(d1);

        d1.link(new Dcons<>(2));
        System.out.println(d1);

        d1.next.link(new Dcons<>(3));
        System.out.println(d1);

        System.out.println(dll.store);

        ListIterator<Integer> li = dll.listIterator();
        System.out.println(li.current());
        System.out.println(li.currentIndex());
        li.next();
        System.out.println(li.current());
        li.addBefore(-99);
        li.addAfter(-98);
        System.out.println(dll);

        li = dll.listIterator(dll.size()-1);
        System.out.println(li.current());
        li.previous();
        System.out.println(li.current());
    }
}

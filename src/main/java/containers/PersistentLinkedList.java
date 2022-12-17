package containers;

import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public class PersistentLinkedList<E> extends PersistentList<E> {
    private Node<E> store = null;
    private int count = 0;

    public PersistentLinkedList() {
        super();
    }

    public PersistentLinkedList(E fillElt) {
        super(fillElt);
    }

    private PersistentLinkedList(E fillElt, Node<E> store, int count) {
        super(fillElt);
        this.store = store;
        this.count = count;
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
    public PersistentContainer<E> clear() {
        return new PersistentLinkedList<>(this.getFillElt());
    }

    @Override
    public PersistentIterator<E> iterator() {
        return new PersistentLinkedListIterator();
    }

    @Override
    public E contains(E object, BiPredicate<E, E> test) {
//        Node<E> node = store;
//        while (node != null) {
//            if ( test.test(object, node.first()) ) {
//                return node.first();
//            }
//
//            node = node.rest();
//        }
//
//        return null;
        return Node.contains(store, object, test);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PersistentList<E> doAdd(E... objs) {
        Node<E> node = null;

        for (int i = objs.length - 1; i >= 0; i--) {
            node = new Node<>(objs[i], node);
        }

        return new PersistentLinkedList<>(getFillElt(), Node.append(store, node), count + objs.length);
    }

    @Override
    protected PersistentList<E> doInsert(int i, E obj) {
        return new PersistentLinkedList<>(getFillElt(), adjustNode(store, i, node -> new Node<>(obj, node)), count + 1);
    }

    @Override
    protected PersistentLinkedList<E> doDelete(int i) {
        return new PersistentLinkedList<>(getFillElt(), adjustNode(store, i, Node::rest), count - 1);
    }

    @Override
    protected E doGet(int i) {
        return Node.nth(store, i);
    }

    @Override
    protected PersistentList<E> doSet(int i, E obj) {
        return new PersistentLinkedList<>(getFillElt(), adjustNode(store, i, node -> new Node<>(obj, node.rest())), count);
    }

    @Override
    public int index(E obj, BiPredicate<E, E> test) {
        return Node.index(store, obj, test);
    }

    @Override
    protected PersistentList<E> doSlice(int i, int n) {
        int start = Math.min(i, count);
        int end = Math.min(i+n, count);
        return new PersistentLinkedList<>(getFillElt(), Node.subseq(store, start, end), end - start);
    }

    private Node<E> adjustNode(Node<E> store, int i, UnaryOperator<Node<E>> adjustment) {
        Node<E> front = null;
        Node<E> rear = null;
        Node<E> node = store;

        for (int j = 0; j < i; j++) {
            Node<E> newNode = new Node<>(node.first(), null);

            if ( front == null ) {
                rear = front = newNode;
            } else {
                rear.setRest(newNode);
                rear = rear.rest();
            }

            node = node.rest();
        }

        Node<E> tail = adjustment.apply(node);

        if ( front == null ) {
            front = tail;
        } else {
            rear.setRest(tail);
        }

        return front;
    }

    private class PersistentLinkedListIterator extends PersistentIterator<E> {
        @Override
        public boolean isDone() {
            return isEmpty();
        }

        @Override
        protected E doCurrent() {
            return get(0);
        }

        @Override
        public PersistentIterator<E> next() {
            if ( isDone() ) {
                return this;
            } else {
                return delete(0).iterator();
            }
        }
    }

    public static void main(String[] args) {
        PersistentList<Integer> pl = new PersistentLinkedList<>(0);
        pl = pl.add(2, 4, 6, 8);
        System.out.println(pl);

        pl = pl.insert(0, 5).insert(-1, 11).insert(8, 22);
        System.out.println(pl);

        pl = pl.delete(2);
        System.out.println(pl);

        pl = pl.set(0, 99);
        System.out.println(pl);

        PersistentList<Number> pl1 = new PersistentLinkedList<>();
        pl1 = pl1.add(1.0, 2, 3.0, 4, 5.0, 6);
        System.out.println(pl1.contains(3, (item, elt) -> item.doubleValue() == elt.doubleValue())); // null?
        System.out.println(pl1.contains(3.0));
        System.out.println(pl1.contains(7));
        System.out.println(pl1.index(5.0));
        System.out.println(pl1.index(5, (item, elt) -> item.doubleValue() == elt.doubleValue()));
        System.out.println(pl1.index(7));

        for (int i = 0; i < pl.size(); i++) {
            System.out.print(pl.get(i) + " ");
        }
        System.out.println();

        for (int i = 1; i <= pl.size(); i++) {
            System.out.print(pl.get(-i) + " ");
        }
        System.out.println();

        pl = pl.set(0, 0).set(15, -9);
        System.out.println(pl);

        System.out.println(pl.slice(0, 3));
        System.out.println(pl.slice(-12, 3));
        System.out.println(pl.slice(-3, 5));
        System.out.println(pl.slice(-20, 3));

        pl.each(System.out::println);

        ArrayListJ<Integer> al = new ArrayListJ<>();
        pl.each(al::add);

        //noinspection EqualsBetweenInconvertibleTypes
        System.out.println(al.equals(pl));
        //noinspection EqualsBetweenInconvertibleTypes
        System.out.println(pl.equals(al));
    }
}

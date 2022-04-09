package containers;

import java.util.function.BiPredicate;

public class Node<E> {
    public static final int NOT_PRESENT = -1;

    private E first;
    private Node<E> rest;

    public Node(E first, Node<E> rest) {
        this.first = first;
        this.rest = rest;
    }

    public E first() {
        return first;
    }

    public Node<E> rest() {
        return rest;
    }

    protected void setFirst(E first) {
        this.first = first;
    }

    protected Node<E> setRest(Node<E> rest) {
        return this.rest = rest;
    }

    protected void spliceBefore(E obj) {
        Node<E> copy = new Node<>(first, rest);
        first = obj;
        rest = copy;
    }

    protected void spliceAfter(E obj) {
        Node<E> tail = new Node<>(obj, rest);
        rest = tail;
    }

    protected E exciseNode() {
        E doomed = first;
        Node<E> saved = rest;

        if ( saved == null ) {
            throw new IllegalStateException("Target node must have non-nil next node");
        } else {
            first = saved.first;
            rest = saved.rest;

            return doomed;
        }
    }

    protected E exciseChild() {
        Node<E> child = rest;

        if ( child == null ) {
            throw new IllegalStateException("Parent must have child node");
        } else {
            rest = child.rest;

            return child.first;
        }
    }

    public static <E> E nth(Node<E> node, int i) {
        if ( node == null ) {
            return null;
        } else if ( i == 0 ) {
            return node.first;
        } else {
            return nth(node.rest, i - 1);
        }
    }

    public static <E> void setNth(Node<E> node, int i, E obj) {
        if ( node == null ) {
            throw new IllegalStateException("Node is empty.");
        } else if ( i == 0 ) {
            node.setFirst(obj);
        } else {
            setNth(node.rest, i - 1, obj);
        }
    }

    public static <E> Node<E> nthCdr(Node<E> node, int i) {
        if ( node == null ) {
            return null;
        } else if ( i == 0 ) {
            return node;
        } else {
            return nthCdr(node.rest, i - 1);
        }
    }

    public static <E> Node<E> reverse(Node<E> list) {
        return reverse(list, null);
    }

    private static <E> Node<E> reverse(Node<E> list, Node<E> result) {
        if ( list == null ) {
            return result;
        } else {
            return reverse(list.rest(), new Node<>(list.first(), result));
        }
    }

    public Node<E> last() {
        Node<E> node = this;
        while ( node.rest != null ) {
            node = node.rest;
        }

        return node;
    }

    public static <T> Node<T> makeList(int n) {
        Node<T> result = null;

        for (int i = 0; i < n; i++) {
            result = new Node<>(null, result);
        }

        return result;
    }

    public static <T> Node<T> append(Node<T> l1, Node<T> l2) {
        if ( l1 == null ) {
            return l2;
        } else {
            return new Node<>(l1.first(), Node.append(l1.rest(), l2));
        }
    }

    public static <E> E contains(Node<E> node, E obj) {
        return contains(node, obj, (item, elt) -> item.equals(elt));
    }

    public static <E> E contains(Node<E> store, E obj, BiPredicate<E, E> test) {
        Node<E> node = store;
        while (node != null) {
            if ( test.test(obj, node.first()) ) {
                return node.first();
            }

            node = node.rest();
        }

        return null;
    }

    public static <E> int index(Node<E> node, E obj) {
        return index(node, obj, (item, elt) -> item.equals(elt));
    }

    public static <E> int index(Node<E> node, E obj, BiPredicate<E, E> test) {
        return index(node, obj, 0, test);
    }

    private static <E> int index(Node<E> node, E obj, int i, BiPredicate<E, E> test) {
        if ( node == null ) {
            return NOT_PRESENT;
        } else if ( test.test(obj, node.first) ) {
            return i;
        } else {
            return index(node.rest, obj, i+1, test);
        }
    }

    /*
     *    The semantics here are consistent with SUBSEQ in Lisp. If you request a subsequence that exceeds
     *    the length of the list, then an exception is thrown. On the other hand, slice() on the List interface
     *    simply truncates the end index to ensure that it does not exceed the length of the list. Thus, the
     *    method which calls this may simply return fewer elements than requested in the slice.
     */
    @SuppressWarnings("unchecked")
    public static <E> E[] slice(Node<E> node, int start, int end) {
        Node<E> head = Node.nthCdr(node, start);
        Object[] slice = new Object[end-start];

        for (int i = 0; i < slice.length; i++) {
            if ( head == null ) {
                throw new IndexOutOfBoundsException("End of list reached.");
            }
            slice[i] = head.first();
            head = head.rest();
        }

        return (E[]) slice;
    }

    protected static <E> Node<E> subseq(Node<E> head, int start, int end) {
        Node<E> front = null;
        Node<E> rear = null;
        Node<E> node = Node.nthCdr(head, start);

        for (int i = start; i < end  &&  node != null; i++) {
            Node<E> newNode = new Node<>(node.first(), null);

            if ( front == null ) {
                rear = front = newNode;
            } else {
                rear.setRest(newNode);
                rear = rear.rest();
            }

            node = node.rest();
        }

        return front;
    }
}

package containers;

public class LinkedStack<E> extends Stack<E> {
    private Node<E> top = null;
    private int count = 0;

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void clear() {
        top = null;
        count = 0;
    }

    @Override
    public void push(E obj) {
        top = new Node<>(obj, top);
        count++;
    }

    @Override
    protected E doPop() {
//        E discard = top.first();
        E discard = peek();
        top = top.rest();
        count--;

        return discard;
    }

    @Override
    protected E doPeek() {
        return top.first();
    }
}

package containers;

public class PersistentLinkedStack<E> extends PersistentStack<E> {
    private Node<E> top = null;
    private int count = 0;

    private static <E> PersistentStack<E> initializeStack(Node<E> top, int count) {
        PersistentLinkedStack<E> newStack = new PersistentLinkedStack<>();
        newStack.top = top;
        newStack.count = count;

        return newStack;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    protected PersistentStack<E> makeEmptyPersistentStack() {
        return new PersistentLinkedStack<>();
    }

    @Override
    public PersistentStack<E> push(E obj) {
        return initializeStack(new Node<>(obj, top), count + 1);
    }

    @Override
    protected PersistentStack<E> doPop() {
        return initializeStack(top.rest(), count - 1);
    }

    @Override
    protected E doPeek() {
        return top.first();
    }
}

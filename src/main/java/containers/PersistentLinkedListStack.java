package containers;

public class PersistentLinkedListStack<E> extends PersistentStack<E> {
    private static final PersistentList<Object> EMPTY = new PersistentLinkedList<>();

    private PersistentList<Object> list = EMPTY;

    private static <E> PersistentStack<E> initializeStack(PersistentList<Object> list) {
        PersistentLinkedListStack<E> newStack = new PersistentLinkedListStack<>();
        newStack.list = list;

        return newStack;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    protected PersistentStack<E> makeEmptyPersistentStack() {
        return new PersistentLinkedListStack<>();
    }

    @Override
    public PersistentStack<E> push(E obj) {
        return initializeStack(list.insert(0, obj));
    }

    @Override
    protected PersistentStack<E> doPop() {
        return initializeStack(list.delete(0));
    }

    @Override
    protected E doPeek() {
        //noinspection unchecked
        return (E) list.get(0);
    }
}

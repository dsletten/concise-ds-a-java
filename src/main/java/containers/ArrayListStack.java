package containers;

public class ArrayListStack<E> extends Stack<E> {
    private final List<E> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void push(E obj) {
        //noinspection unchecked
        list.add(obj);
    }

    @Override
    protected E doPop() {
        return list.delete(-1);
    }

    @Override
    protected E doPeek() {
        return list.get(-1);
    }

}

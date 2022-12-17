package containers;

public class RandomAccessListListIterator<E> extends MutableListListIterator<E> {
    private int cursor;

    protected RandomAccessListListIterator(MutableList<E> list, int start, RemoteControl remoteControl) {
        super(list, remoteControl);

        if ( start < 0  ||  start > Math.max(list.size(), 1) ) {
            throw new IllegalStateException("Invalid index: " + start);
        }

        cursor = start;
    }

    @Override
    protected E doDoCurrent() {
        return list.get(cursor);
    }

    @Override
    protected int doDoCurrentIndex() {
        return cursor;
    }

    @Override
    protected void doDoSetCurrent(E obj) {
        list.set(currentIndex(), obj);
    }

    @Override
    protected boolean doHasNext() {
        return cursor < list.size() - 1;
    }

    @Override
    protected boolean doHasPrevious() {
        return cursor > 0;
    }

    @Override
    protected E doDoNext() {
        if ( hasNext() ) {
            cursor++;
            return current();
        } else {
            return null;
        }
    }

    @Override
    protected E doDoPrevious() {
        if ( hasPrevious() ) {
            cursor--;
            return current();
        } else {
            return null;
        }
    }

    @Override
    protected E doDoRemove() {
        int index = cursor;

        if ( hasPrevious()  &&  !hasNext() ) {
            cursor--;
        }

        return list.delete(index);
    }

    @Override
    protected void doAddBefore(E obj) {
        if ( isEmpty() ) {
            //noinspection unchecked
            list.add(obj);
        } else {
            list.insert(cursor, obj);
            cursor++;
        }
    }

    @Override
    protected void doAddAfter(E obj) {
        if ( isEmpty() ) {
            //noinspection unchecked
            list.add(obj);
        } else {
            list.insert(cursor + 1, obj);
        }
    }
}

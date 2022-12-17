package containers;

public class SinglyLinkedListListIterator<E> extends MutableListListIterator<E> {
    private int index = 0;
    private Node<E> cursor;
    private final Stack<Node<E>> history = new LinkedStack<>();

    protected SinglyLinkedListListIterator(MutableLinkedList<E> list, int start, RemoteControl remoteControl) {
        super(list, remoteControl);

        if ( start < 0  ||  start > Math.max(list.size(), 1) ) {
            throw new IllegalStateException("Invalid index: " + start);
        }

        initializeCursor();

        for (int i = 0; i < start; i++) {
            next();
        }
    }

    @Override
    protected E doDoCurrent() {
        return cursor.first();
    }

    @Override
    protected int doDoCurrentIndex() {
        return index;
    }

    @Override
    protected void doDoSetCurrent(E obj) {
        cursor.setFirst(obj);
    }

    @Override
    protected boolean doHasNext() {
        return !(cursor == null  ||  cursor.rest() == null);
    }

    @Override
    protected boolean doHasPrevious() {
        //noinspection unchecked,RedundantCast
        return !(cursor == null  ||  cursor == (Node<E>) rc.press("headNode"));
    }

    @Override
    protected E doDoNext() {
        if ( hasNext() ) {
            history.push(cursor);
            cursor = cursor.rest();
            index++;

            return current();
        } else {
            return null;
        }
    }

    @Override
    protected E doDoPrevious() {
        if ( hasPrevious() ) {
            cursor = history.pop();
            index--;

            return current();
        } else {
            return null;
        }
    }

    @Override
    protected E doDoRemove() {
        if ( index == 0 ) {
            E result = ((MutableLinkedList<E>) list).deleteNode(cursor);
            initializeCursor();

            return result;
        } else {
            Node<E> parent = history.peek();
            if ( hasNext() ) {
                cursor = cursor.rest();
            } else {
                cursor = history.pop();
                index--;
            }

            return ((MutableLinkedList<E>) list).deleteChild(parent);
        }
    }

    @Override
    protected void doAddBefore(E obj) {
        if ( isEmpty() ) {
            //noinspection unchecked
            list.add(obj);
            initializeCursor();
        } else {
            ((MutableLinkedList<E>) list).insertBefore(cursor, obj);
            history.push(cursor);
            cursor = cursor.rest();
            index++;
        }
    }

    @Override
    protected void doAddAfter(E obj) {
        if ( isEmpty() ) {
            //noinspection unchecked
            list.add(obj);
            initializeCursor();
        } else {
            ((MutableLinkedList<E>) list).insertAfter(cursor, obj);
        }
    }

    private void initializeCursor() {
        //noinspection unchecked
        cursor = (Node<E>) rc.press("headNode");
    }
}

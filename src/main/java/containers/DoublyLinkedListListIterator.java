package containers;

@SuppressWarnings("unused")
public class DoublyLinkedListListIterator<E> extends MutableListListIterator<E> {
//    DoublyLinkedList.Dcursor<E> cursor;

    @SuppressWarnings("rawtypes")
    DoublyLinkedList.Dcursor cursor;

    protected DoublyLinkedListListIterator(MutableLinkedList<E> list, int start, RemoteControl remoteControl) {
        super(list, remoteControl);

        if ( start < 0  ||  start > Math.max(list.size(), 1) ) {
            throw new IllegalStateException("Invalid index: " + start);
        }

        //noinspection rawtypes
        cursor = (DoublyLinkedList.Dcursor) rc.press("initialize");

        if ( start != 0 ) {
            cursor.advance(start);
        }
    }

    @Override
    protected E doDoCurrent() {
        //noinspection unchecked
        return (E) cursor.getNode().getContent();
    }

    @Override
    protected int doDoCurrentIndex() {
        return cursor.getIndex();
    }

    @Override
    protected void doDoSetCurrent(E obj) {
        //noinspection unchecked
        cursor.getNode().setContent(obj);
    }

    @Override
    protected boolean doHasNext() {
        return !cursor.atEnd();
    }

    @Override
    protected boolean doHasPrevious() {
        return !cursor.atStart();
    }

    @Override
    protected E doDoNext() {
        if ( hasNext() ) {
            cursor.advance();
            return current();
        } else {
            return null;
        }
    }

    @Override
    protected E doDoPrevious() {
        if ( hasPrevious() ) {
            cursor.rewind();
            return current();
        } else {
            return null;
        }
    }

    @Override
    protected E doDoRemove() {
        if ( cursor.getIndex() == 0 ) {
            //noinspection unchecked
            E doomed = ((MutableLinkedList<E>) list).deleteNode((DoublyLinkedList.Dcons<E>) cursor.getNode());
            cursor.reset();

            return doomed;
        } else {
            //noinspection unchecked
            DoublyLinkedList.Dcons<E> currentNode = cursor.getNode();

            if ( hasNext() ) {
                cursor.bump();
            } else {
                cursor.rewind();
            }

            return ((MutableLinkedList<E>) list).deleteNode(currentNode);
        }
    }

    @Override
    protected void doAddBefore(E obj) {
        if ( isEmpty() ) {
            //noinspection unchecked
            list.add(obj);
            cursor.reset();
        } else {
            //noinspection unchecked
            ((MutableLinkedList<E>) list).insertBefore((DoublyLinkedList.Dcons<E>) cursor.getNode(), obj);
            cursor.nudge();
        }
    }

    @Override
    protected void doAddAfter(E obj) {
        if ( isEmpty() ) {
            //noinspection unchecked
            list.add(obj);
            cursor.reset();
        } else {
            //noinspection unchecked
            ((MutableLinkedList<E>) list).insertAfter((DoublyLinkedList.Dcons<E>) cursor.getNode(), obj);
        }
    }
}

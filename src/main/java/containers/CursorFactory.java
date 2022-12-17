package containers;

public class CursorFactory {
    public static <E> Cursor<E> makeRandomAccessListCursor(List<E> list) {
        return new Cursor<>() {
            private int index = 0;

            @Override
            public boolean isDone() {
                return index == list.size();
            }

            @Override
            public E current() {
                return list.get(index);
            }

            @Override
            public void advance() {
                index++;
            }
        };
    }

    public static <E> Cursor<E> makeSinglyLinkedListCursor(Node<E> node) {
        return new Cursor<>() {
            private Node<E> index = node;

            @Override
            public boolean isDone() {
                return index == null;
            }

            @Override
            public E current() {
                return index.first();
            }

            @Override
            public void advance() {
                index = index.rest();
            }
        };
    }

    //    Can't access Dcursor class!
    @SuppressWarnings("unused")
    public static <E> Cursor<E> makeDoublyLinkedListCursor(@SuppressWarnings("rawtypes") DoublyLinkedList.Dcursor dc) {
        //noinspection rawtypes
        return new Cursor<>() {
            private final DoublyLinkedList.Dcursor dcursor = dc;
            private boolean sealedForYourProtection = true;

            @Override
            public boolean isDone() {
                return !dcursor.isInitialized() || (!sealedForYourProtection && dcursor.atStart());
            }

            @Override
            public E current() {
                //noinspection unchecked
                return (E) dcursor.getNode().getContent();
            }

            @Override
            public void advance() {
                if (!isDone()) {
                    dcursor.advance();
                    sealedForYourProtection = false;
                }
            }
        };
    }
}

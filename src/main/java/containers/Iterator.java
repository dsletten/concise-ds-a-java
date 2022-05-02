package containers;

public class Iterator<E> {
    public Cursor<E> cursor;

    public Iterator(Cursor<E> cursor) {
        this.cursor = cursor;
    }

    public final boolean isDone() {
        return checkDone();
    }

    protected boolean checkDone() {
        return cursor.isDone();
    }

    public final E current() {
        if ( isDone() ) {
            throw new IllegalStateException("Iteration already finished");
        } else {
            return currentElement();
        }
    }

    protected E currentElement() {
        return cursor.current();
    }

    public final E next() {
        if ( isDone() ) {
            return null;
        } else {
            nextElement();

            if ( isDone() ) {
                return null;
            } else {
                return current();
            }
        }
    }

    protected void nextElement() {
        cursor.advance();
    }
}

package ch07;

public class StringEnumerator {
    private String s;
    private int index = 0;

    public StringEnumerator(String source) {
        s = source;
    }

    public boolean isEmpty() {
        return index == s.length();
    }

    public char current() {
        if ( isEmpty() ) {
            throw new IllegalStateException("String enumerator is empty.");
        } else {
            return s.charAt(index);
        }
    }

    public char advance() {
        if ( isEmpty() ) {
            throw new IllegalStateException("String enumerator is empty.");
        } else {
            char ch = current();
            index++;
            return ch;
        }
    }

    public void  reset() {
        index = 0;
    }

    public StringEnumerator duplicate() {
        StringEnumerator copy = new StringEnumerator(s);
        copy.index = index;
        return copy;
    }
}

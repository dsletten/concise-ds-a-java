package ch02;

public class CyclicCounter extends AbstractCounter {
    private int index = 0;
    private final int modulus;

    public CyclicCounter(int modulus) {
        if ( modulus < 1 ) {
            throw new IllegalArgumentException("Modulus must be at least 1.");
        } else {
            this.modulus = modulus;
        }
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public int modulus() {
        return modulus;
    }

    public Counter advance(int n) {
        index = AbstractCounter.mod(index + n, modulus);

        return this;
    }

    public Counter set(int n) {
        index = AbstractCounter.mod(n, modulus);

        return this;
    }

    public static void main(String[] args) {
        CyclicCounter c4 = new CyclicCounter(4);
        System.out.println(c4);

        c4.advance();
        System.out.println(c4);

        c4.advance();
        System.out.println(c4);

        c4.advance();
        System.out.println(c4);

        c4.advance();
        System.out.println(c4);
    }
}

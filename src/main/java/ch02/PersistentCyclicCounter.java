package ch02;

public class PersistentCyclicCounter extends AbstractCounter {
    private final int index;
    private final int modulus;

    private PersistentCyclicCounter(int index, int modulus) {
        if ( modulus < 1 ) {
            throw new IllegalArgumentException("Modulus must be at least 1.");
        } else {
            this.index = AbstractCounter.mod(index, modulus);
            this.modulus = modulus;
        }
    }

    public static PersistentCyclicCounter makePersistentCyclicCounter(int modulus) {
        return new PersistentCyclicCounter(0, modulus);
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
        return new PersistentCyclicCounter(index + n, modulus);
    }

    public Counter set(int n) {
        return new PersistentCyclicCounter(n, modulus);
    }

    public static void main(String[] args) {
        Counter c4 = PersistentCyclicCounter.makePersistentCyclicCounter(4);
        System.out.println(c4);

        c4 = c4.advance();
        System.out.println(c4);

        c4 = c4.advance();
        System.out.println(c4);

        c4 = c4.advance();
        System.out.println(c4);

        c4 = c4.advance();
        System.out.println(c4);
    }
}

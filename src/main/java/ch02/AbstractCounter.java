package ch02;

public abstract class AbstractCounter implements Counter {
    public String toString() {
        return String.format("%s [%d/%d]", getClass().getSimpleName(), index(), modulus());
    }

    public static int mod(int number, int divisor) {
        int rem = number % divisor;

        if ( rem != 0  &&  (divisor < 0 ? number > 0 : number < 0) ) {
            return rem + divisor;
        } else {
            return rem;
        }
    }
}

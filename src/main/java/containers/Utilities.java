package containers;

public class Utilities {
    public static int mod(int number, int divisor) {
        int remainder = number % divisor;

        if ( remainder != 0  &&  (divisor < 0 ? number > 0 : number <  0) ) {
            return remainder + divisor;
        } else {
            return remainder;
        }
    }
}

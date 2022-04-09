package ch04;

public class StorageFacility {
    //
    //    @inv: rented.length == NUM_LOCKERS
    //    @inv: 0 <= availableLockers <= NUM_LOCKERS
    //
    private static final int NUM_LOCKERS = 138;

    private boolean[] rented = new boolean[NUM_LOCKERS];
    private int availableLockers = NUM_LOCKERS;

    public StorageFacility() {}

    //    Find an empty locker, mark it rented, return its number
    //    @pre: !isFull()
    //    @post: availableLockers == oldAvailableLockers - 1
    public int rentLocker() {
        if ( isFull() ) {
            throw new IllegalStateException("Storage facility is full.");
        } else {
            int lockerNumber = findFirstAvailable();
            rented[lockerNumber] = true;
            availableLockers--;

            return lockerNumber;
        }
    }

    //    Mark a locker as no longer rented
    //    @pre: 0 <= lockerNumber < NUM_LOCKERS, !isFree?(lockerNumber)
    //    @post: isFree(locker_number), availableLockers == oldAvailableLockers + 1
    public void releaseLocker(int lockerNumber) {
        if ( !isValidLocker(lockerNumber) ) {
            throw new IllegalArgumentException("Invalid locker: " + lockerNumber);
        } else if ( isFree(lockerNumber) ) {
            throw new IllegalArgumentException("Locker is already free: " + lockerNumber);
        } else {
            rented[lockerNumber] = false;
            availableLockers++;
        }
    }

    private int findFirstAvailable() {
        for (int i = 0; i < rented.length; i++) {
            if ( isFree(i) ) {
                return i;
            }
        }
        throw new IllegalStateException("No available locker.");
    }

    public int getAvailableLockers() {
        return availableLockers;
    }
    
    public boolean isFull() {
        return availableLockers == 0;
    }

    public boolean isFree(int lockerNumber) {
        if ( !isValidLocker(lockerNumber) ) {
            throw new IllegalArgumentException("Invalid locker: " + lockerNumber);
        } else {
            return !rented[lockerNumber];
        }
    }

    private int capacity() {
        return rented.length;
    }

    private boolean isValidLocker(int lockerNumber) {
        return lockerNumber >= 0  &&  lockerNumber < capacity();
    }
}

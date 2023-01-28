package containers.alt.api.aspects;

import containers.alt.api.Deque;

public aspect DequeAspect {

    pointcut callDequeueRear(Deque deque):
        // "call" instead of "execution" won't match HERE AND ONLY HERE!
        // I spent hours and can't explain why it happens ONLY here.
        // So I switched all pointcuts to "execution".
        execution(Object Deque.dequeueRear())
        && target(deque);

    pointcut callRear(Deque deque):
        execution(Object Deque.rear())
        && target(deque);

    before(Deque deque) : callDequeueRear(deque){
        if(deque.isEmpty()){
            throw new IllegalStateException();
        }
    }

    before(Deque deque) : callRear(deque){
        if(deque.isEmpty()){
            throw new IllegalStateException();
        }
    }
}

package containers.alt.api.aspects;

import containers.alt.api.Queue;
import containers.alt.api.Deque;

public aspect QueueAspect {

   pointcut emptyCheck(Queue queue) :
        (execution(Object Queue.dequeue())
        || execution(Object Queue.front())
        || execution(Object Deque.dequeueRear())
        || execution(Object Deque.rear()))
        && target(queue);

    before(Queue queue) : emptyCheck(queue){
        if(queue.isEmpty()){
            throw new IllegalStateException();
        }
    }
}
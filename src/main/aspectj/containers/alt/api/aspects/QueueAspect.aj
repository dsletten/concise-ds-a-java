package containers.alt.api.aspects;

import containers.alt.api.Queue;

public aspect QueueAspect {

   pointcut callDequeue(Queue queue) :
        execution(Object Queue.dequeue())
        && target(queue);

   pointcut callFront(Queue queue):
        execution(Object Queue.front())
        && target(queue);

   before(Queue queue) : callDequeue(queue){
       if(queue.isEmpty()){
           throw new IllegalStateException();
       }
   }

    before(Queue queue) : callFront(queue){
        if(queue.isEmpty()){
            throw new IllegalStateException();
        }
    }
}
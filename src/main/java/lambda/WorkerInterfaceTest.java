package lambda;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/15
 */
@FunctionalInterface
interface WorkerInterface {

    void doSomeWork(int x);

    //void doSomeMoreWork();

}

public class WorkerInterfaceTest {
    public static void execute(WorkerInterface worker,int x) {
        worker.doSomeWork(x);
    }

    public static void main(String [] args) {

        //invoke doSomeWork using Annonymous class
        execute(new WorkerInterface() {
            @Override
            public void doSomeWork(int x) {
                System.out.println("Worker invoked using Anonymous class");
            }
        },2);

        //invoke doSomeWork using Lambda expression
        execute( (x) -> System.out.println("Worker invoked using Lambda expression"), 3);

        execute((x)-> System.out.println(3*x),70);
    }
}

package current;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * function
 * Author: yang.hao
 * Date: 2017/2/4
 */
public class Main {
    public static void main(String a[]){
        System.out.println("=====================start");
        MyExecutor myExecutor = new MyExecutor(2,3,1000, TimeUnit.MICROSECONDS,new LinkedBlockingQueue<>());

        List<Future<String>> results = new ArrayList<>();

        for (int i=0;i<10;i++){
            SleepTwoSecondsTask task = new SleepTwoSecondsTask();
            Future<String> result = myExecutor.submit(task);
            results.add(result);
        }

        for (int i =0;i<10;i++){
            try {
                String result=results.get(i).get();
                System.out.printf("Main: Result for Task %d :%s\n",i,result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            myExecutor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("================Main: End of the program.\n");

    }
}

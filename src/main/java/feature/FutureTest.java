package feature;

import java.util.Random;
import java.util.concurrent.*;

/**
 * author :y.hao
 * time :2018/2/24
 * function:
 */

class HandelCallable implements Callable<Integer>{
    private String name;

    public HandelCallable(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("task"+name+"开始计算");
        Thread.sleep(3000);
        int sum = new Random().nextInt(300);
        int result = 0;
        for (int i = 0;i<sum;i++){
            result += i;
        }
        return result;
    }
}

public class FutureTest {
    public static void main(String []arg){
        System.out.println("main Thread begin at:"+ System.nanoTime());
        ExecutorService executorService = Executors.newCachedThreadPool();
        HandelCallable callable1 = new HandelCallable("1");
        HandelCallable callable2 = new HandelCallable("2");
        HandelCallable callable3 = new HandelCallable("3");

        Future result1 = executorService.submit(callable1);
        Future result2 = executorService.submit(callable2);
        Future result3 = executorService.submit(callable3);
        executorService.shutdown();

        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        try {
            System.out.println("call1结果："+ result1.get());
            System.out.println("call2结果："+ result2.get());
            System.out.println("call3结果："+ result3.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("main Thread finish at:"+ System.nanoTime());
    }
}


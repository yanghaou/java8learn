package feature;

import java.util.Random;
import java.util.concurrent.*;

/**
 * author :y.hao
 * time :2018/2/24
 * function:直接通过New Thread来启动线程
 */
class MyTask implements Callable<Integer> {
    private String name;

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("task"+ name + "开始进行计算");
        Thread.sleep(3000);
        int sum = new Random().nextInt(300);
        int result = 0;
        for (int i = 0; i < sum; i++)
            result += i;
        return result;
    }
}


public class FutrueTaskTest {
    public static void main(String []arg){
        System.out.println("main Thread begin at:"+ System.nanoTime());
        ExecutorService executorService = Executors.newCachedThreadPool();
        MyTask myTask1 = new MyTask("1");
        MyTask myTask2 = new MyTask("2");
        Future<Integer> future1= executorService.submit(myTask1);
        Future<Integer> future2 = executorService.submit(myTask2);
        executorService.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        try {
            System.out.println("task1返回结果:"  + future1.get());
            System.out.println("task2返回结果:"  + future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("main Thread finish at:"+ System.nanoTime());
    }

    public static void main1(String []arg){
        //采用直接启动线程的方法
        System.out.println("main Thread begin at:"+ System.nanoTime());

        MyTask myTask1 = new MyTask("1");
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(myTask1);
        Thread thread1 = new Thread(futureTask1);
        thread1.start();

        MyTask myTask2 = new MyTask("2");
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(myTask2);
        Thread thread2 = new Thread(futureTask2);
        thread2.start();

        MyTask myTask3 = new MyTask("3");
        FutureTask<Integer> futureTask3 = new FutureTask<Integer>(myTask3);
        Thread thread3 = new Thread(futureTask3);
        thread3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        try {
            System.out.println("task1返回结果:"  + futureTask1.get());
            System.out.println("task2返回结果:"  + futureTask2.get());
            System.out.println("task3返回结果:"  + futureTask3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("main Thread finish at:"+ System.nanoTime());
    }
}

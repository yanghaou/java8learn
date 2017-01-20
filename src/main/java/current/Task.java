package current;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/19
 */
public class Task {
    //thread
    public void test0(){
        Runnable runnable =()->{
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };
        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Done!");
    }
    public void test01(){
        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


    //newSingleThreadExecutor
    public void test1(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() ->{
           String threadName = Thread.currentThread().getName();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello "+threadName);
        });

        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

    public void test2(){
        Callable<Integer> task = ()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(task);
        System.out.println("future done? " + future.isDone());

        Integer result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();

        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);
        System.out.println("executor "+executorService.isShutdown());
    }

    public void test3(){
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        try {
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        }
                        catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test4(){
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3));

        String result = null;
        try {
            result = executor.invokeAny(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    public void test5(){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);
        try {
            TimeUnit.MILLISECONDS.sleep(1337);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms", remainingDelay);
    }

    public static void main(String a[]){
        Task task = new Task();
        task.test5();
    }
}

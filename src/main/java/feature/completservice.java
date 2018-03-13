package feature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * author :y.hao
 * time :2018/2/24
 * function:
 */
class HandleFuture implements Callable<Integer>{
    private Integer num;

    public HandleFuture(Integer num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3*100);
        System.out.println(Thread.currentThread().getName());
        return num;
    }
}
public class completservice {
    public static void main(String a[]) throws Exception{
        CompleTest();
    }
    public static void FutureTest() throws InterruptedException, ExecutionException {
        System.out.println("main Thread begin:");
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Integer>> result = new ArrayList<Future<Integer>>();
        for (int i = 0;i<10;i++) {
            Future<Integer> submit = executor.submit(new HandleFuture(i));
            result.add(submit);
        }
        executor.shutdown();
        for (int i = 0;i<10;i++) {//一个一个等待返回结果
            System.out.println("返回结果："+result.get(i).get());
        }
        System.out.println("main Thread end:");
    }

    public static void CompleTest() throws InterruptedException, ExecutionException {
        System.out.println("main Thread begin:");
        ExecutorService executor = Executors.newCachedThreadPool();
        // 构建完成服务
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
        for (int i = 0;i<10;i++) {
            completionService.submit(new HandleFuture(i));
        }
        for (int i = 0;i<10;i++) {//一个一个等待返回结果
            System.out.println("返回结果："+completionService.take().get());
        }
        System.out.println("main Thread end:");
    }

}

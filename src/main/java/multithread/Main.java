package multithread;

/**
 * author :y.hao
 * time :2018/2/23
 * function: join学习
 */
class Thread1 extends Thread{
    private String name;
    public Thread1(String name) {
        super(name);
        this.name=name;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程"+name + "运行 : " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
    }
}

class ThreadYield extends Thread{
    public ThreadYield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i ==30) {
                this.yield();
            }
        }

    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
        Thread1 mTh1=new Thread1("A");
        Thread1 mTh2=new Thread1("B");
        mTh1.start();
        mTh2.start();

        try {
            mTh1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mTh2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");

    }

}

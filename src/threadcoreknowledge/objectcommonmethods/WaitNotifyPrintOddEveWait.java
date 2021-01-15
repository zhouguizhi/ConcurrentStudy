package threadcoreknowledge.objectcommonmethods;
/**
 * 描述：两个线程交替打印0~100的奇偶数，用wait和notify
 */
public class WaitNotifyPrintOddEveWait {
    private static int count = 0;
    private static final int max_count = 100;
    private static final Object lock = new Object();

    public static void main(String[] args) {
       new WaitNotifyPrintOddEveWait().test();
//        new Thread(new TurningRunner(), "偶数").start();
//        new Thread(new TurningRunner(), "奇数").start();
    }
    public  void test(){
        for(int i=0;i<30;i++){
            System.out.println("i="+i);
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 1. 拿到锁，我们就打印
     * 2. 打印完，唤醒其他线程，自己就休眠
     */
    static class TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= max_count) {
                synchronized (lock) {
                    //拿到锁就打印
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    if (count <= max_count) {
                        try {
                            //如果任务还没结束，就让出当前的锁，并休眠
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

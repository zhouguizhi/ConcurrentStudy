package threadcoreknowledge.objectcommonmethods;
/**
 * wait方法演示:展示wait和notify的基本用法 1. 研究代码执行顺序 2. 证明wait释放锁
 */
public class WaitMethodTest {
    private static Object object = new Object();
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        try {
            //sleep的目的是让让线程先wait 不然如果先执行了notify，再执行wait Thread1 线程就一直处于等待状态
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread2.start();
    }
    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程"+Thread.currentThread().getName()+"开始执行任务");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程"+Thread.currentThread().getName()+"任务执行完毕");
            }
        }
    }
    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                object.notify();
                System.out.println("线程"+Thread.currentThread().getName()+"去唤醒");
            }
        }
    }
}

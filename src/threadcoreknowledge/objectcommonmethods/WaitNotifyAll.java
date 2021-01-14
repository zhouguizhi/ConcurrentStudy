package threadcoreknowledge.objectcommonmethods;
/**
 * 描述:3个线程，线程1和线程2首先被阻塞，线程3唤醒它们。notify, notifyAll。 start先执行不代表线程先启动。
 */
public class WaitNotifyAll implements Runnable{
    //锁
    private static Object lock = new Object();
    public static void main(String[] args) {
        WaitNotifyAll waitNotifyAll = new WaitNotifyAll();
        Thread thread1 = new Thread(waitNotifyAll);
        Thread thread2 = new Thread(waitNotifyAll);
        NotifyAllThread notifyAllThread = new NotifyAllThread();
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAllThread.start();
    }
    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName()+" got  lock.");
            try {
                System.out.println(Thread.currentThread().getName()+" waits to start.");
                lock.wait();
                System.out.println(Thread.currentThread().getName()+"'s waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class NotifyAllThread extends Thread{
        @Override
        public void run() {
            super.run();
            synchronized (lock){
                lock.notifyAll();
            }
        }
    }
}

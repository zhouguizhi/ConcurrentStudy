package threadcoreknowledge.threadsafety;
/**
 * 描述:演示死锁
 */
public class DeadLock implements Runnable{
    int flag = 1;
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();
        deadLock1.flag = 1;
        deadLock2.flag = 0;
        new Thread(deadLock1).start();
        new Thread(deadLock2).start();
    }
    @Override
    public void run() {
        System.out.println("flag: = "+flag);
        if(flag==1){
            synchronized (lock1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("1");
                }
            }
        }

        if(flag==0){
            synchronized (lock2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("0");
                }
            }
        }
    }
}

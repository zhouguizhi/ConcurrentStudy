package threadcoreknowledge.objectcommonmethods;
/**
 * 描述:证明wait只释放当前的那把锁
 * 在多线程中使用2个锁 然后使用wait,notify进行验证
 */
public class WaitNotifyReleaseOwnMonitor {
    //这里为什么要使用volatile修饰呢？
    private static volatile Object LockA = new Object();
    private static volatile Object LockB = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LockA) {
                    System.out.println("ThreadA got LockA .");
                    synchronized (LockB) {
                        System.out.println("ThreadA got LockB .");
                        try {
                            System.out.println("ThreadA releases  lockA.");
                            LockA.wait();//释放锁A

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LockA) {
                    System.out.println("ThreadB got  lockA.");
                    System.out.println("ThreadB tries to  lockB.");
                    //B锁在线程1中没有被释放因为没有锁去唤醒它,所以下面这句话是一直停留在这 程序其实是没死的
                    synchronized (LockB) {
                        System.out.println("锁B一直未被释放,所有它会一直等待");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

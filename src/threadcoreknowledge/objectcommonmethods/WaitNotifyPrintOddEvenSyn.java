package threadcoreknowledge.objectcommonmethods;
/**
 * 描述：两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 * 在这学到了一点怎么判断奇偶数  n&1=0表示偶数 n&1=1表示奇数
 * 这个是实现方案不是最好的方案,因为会出现当count为偶数或者奇数,它会执行奇数线程,偶数线程,会造成cpu的浪费,虽然功能上能实现
 * 所以最好还是改用 wait,notify实现
 */
public class WaitNotifyPrintOddEvenSyn {
    private static int count;
    private static int max_count = 100;
    private static final Object lock = new Object();
    
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < max_count) {
                    synchronized (lock) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        }, "偶数").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < max_count) {
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        }, "奇数").start();
    }
}

package threadcoreknowledge.objectcommonmethods;
/**
 * 描述：两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 * 在这学到了一点怎么判断奇偶数  n&1=0表示偶数 n&1=1表示奇数
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count;
    private static int max_count = 100;
    private static final Object lock = new Object();

    //新建2个线程
    //1个只处理偶数，第二个只处理奇数（用位运算）
    //用synchronized来通信
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

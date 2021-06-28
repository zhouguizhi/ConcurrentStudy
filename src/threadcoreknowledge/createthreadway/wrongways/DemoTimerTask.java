package threadcoreknowledge.createthreadway.wrongways;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 演示定时器
 * 阅读源码发现定时器其实内部也是使用了继承Thread方式来创建一个线程,首先通过new Timer()构造函数跟进去
 * public Timer() {
 *         this("Timer-" + serialNumber());
 *     }
 * 它会调用有一个String参数的构造函数
 *  public Timer(String name) {
 *         thread.setName(name);
 *         thread.start();
 *     }
 *     然而thread变量是Timer的成员变量:
 *     private final TimerThread thread = new TimerThread(queue);
 *     再看看TimerThread类
 *     class TimerThread extends Thread {
 *          .....
 *     }
 *     走到这里终然发现它是继承了Thread类
 */
public class DemoTimerTask {
    public static void main(String[] args) {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"::定时器...");
            }
        },1000,1000);
    }
}

package threadcoreknowledge.objectcommonmethods;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 每隔1s打印下当前时间,但是过一段时间,线程被中断,
 */
public class SleepInterrupter implements Runnable {
    public static void main(String[] args) {
        SleepInterrupter sleepInterrupter = new SleepInterrupter();
        Thread thread = new Thread(sleepInterrupter);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("线程状态:="+thread.getState());
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("我被中断了");
            }
        }
    }
}

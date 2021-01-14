package threadcoreknowledge.stopthreads;
/**
 * 这是在正常的情况下停止线程 没有粗塞 也就是run方法没有sleep或者wait方法时停止线程
 * 在调用了线程的interrupt()方法后,还要在run()方法中去判断是否被中断isInterrupted()
 */
public class RightStopThreadWithoutSleep implements Runnable{
    public  static void main(String[] args) {
       Thread thread = new Thread(new RightStopThreadWithoutSleep());
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0;
        //1073740000
        //155110000
        while (!Thread.currentThread().isInterrupted()&&num<=Integer.MAX_VALUE/2){
            if(num%10000==0){
                System.out.println(num+"是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束");
    }
}

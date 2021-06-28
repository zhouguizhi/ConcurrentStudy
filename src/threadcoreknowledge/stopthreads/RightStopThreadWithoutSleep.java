package threadcoreknowledge.stopthreads;
/**
 * 描述:run方法没有sleep或者wait方法,停止线程
 * 这是在正常的情况下停止线程 没有粗塞 也就是run方法没有sleep或者wait方法时停止线程
 * 在调用了线程的interrupt()方法后,还要在run()方法中去判断是否被中断isInterrupted()
 */
public class RightStopThreadWithoutSleep implements Runnable{
    public  static void main(String[] args) throws InterruptedException {
       Thread thread = new Thread(new RightStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0;
        //1073740000 没有!Thread.currentThread().isInterrupted()这个判断是这个数
        //有了!Thread.currentThread().isInterrupted()这个条件num肯定是会比1073740000小的
        while (!Thread.currentThread().isInterrupted()&&num<=Integer.MAX_VALUE/2){
            if(num%10000==0){
                System.out.println(num+"是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束");
    }
}

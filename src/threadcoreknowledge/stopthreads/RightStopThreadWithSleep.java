package threadcoreknowledge.stopthreads;
/**
 * 这是在粗塞的情况下,停止线程
 * 这是在线程真在休眠或者粗塞的情况下 这个时候如果中断线程 ,怎么处理让程序不挂,那么处理的方案就是catch主异常,
 */
public class RightStopThreadWithSleep implements Runnable{
    public static void main(String[] args) throws InterruptedException {
       Thread thread = new Thread(new RightStopThreadWithSleep());
        thread.start();
        Thread.sleep(500);
        //这是模拟程序查看程序有没有挂
        for(int i=0;i<100000;i++){
            System.out.println("i>>>>>>>>>>>"+i);
        }
    }

    @Override
    public void run() {
        int num = 0;
        try{
            while (!Thread.currentThread().isInterrupted()&&num<=300){
                if(num%100==0){
                    System.out.println(num+"是100的倍数");
                }
                num++;
            }
            Thread.sleep(1000);
            System.out.println("任务运行结束");
        }catch (Exception e){
           e.printStackTrace();
        }
    }
}

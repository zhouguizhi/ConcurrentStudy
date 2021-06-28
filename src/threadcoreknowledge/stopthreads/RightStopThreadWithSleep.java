package threadcoreknowledge.stopthreads;
/**
 * 描述:带有在run方法中sleep中断线程的写法
 * 凡是有sleep或者wait方法情况下,线程都会被阻塞
 * 这是在线程真在休眠或者粗塞的情况下 这个时候如果中断线程 ,怎么处理让程序不挂,那么处理的方案就是catch主异常,
 * java.lang.InterruptedException: sleep interrupted
 * 说明线程在sleep休眠的过程中被打断了,
 */
public class RightStopThreadWithSleep implements Runnable{
    public static void main(String[] args) throws InterruptedException {
       Thread thread = new Thread(new RightStopThreadWithSleep());
        thread.start();
        //休眠500毫秒是因为线程在执行while任务后还休眠了1000毫秒,所以说在这里main函数休眠500毫秒 子线程一定是还没执行完任务也就是run方法还没执行完毕的
        Thread.sleep(500);
        thread.interrupt();
        //这是模拟程序查看程序有没有挂
//        for(int i=0;i<100000;i++){
//            System.out.println("i>>>>>>>>>>>"+i);
//        }
    }

    @Override
    public void run() {
        int num = 0;
        try{
            while (!Thread.currentThread().isInterrupted()&&num<=300){
                if(num%100==0&&num>0){
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

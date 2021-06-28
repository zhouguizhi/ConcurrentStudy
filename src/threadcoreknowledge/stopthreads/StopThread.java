package threadcoreknowledge.stopthreads;
/**
 * 描述:错误的停止线程的方法：用stop方法来停止线程,会导致线程运行一般突然停止,它没有办法完成一个基本单位的操作
 * 模拟一个连队,会造成脏数据(有的连队多或者少领取装备)
 */
public class StopThread implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Runnable target;
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(1000);
        thread.stop();
    }
    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println("连队"+i+"开始领取装备");
            for(int j=0;j<10;j++){
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队"+i+"领取装备完成");
        }
    }
}
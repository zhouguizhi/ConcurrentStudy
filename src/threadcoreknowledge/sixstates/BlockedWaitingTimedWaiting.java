package threadcoreknowledge.sixstates;
/**
 * 描述:演示线程三种状态分别似乎Block,Waiting,TimedWaiting
 */
public class BlockedWaitingTimedWaiting implements Runnable{
    public static void main(String[] args) {
        BlockedWaitingTimedWaiting target = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(target);
        thread1.start();
        Thread thread2 = new Thread(target);
        thread2.start();
        //打印出TIMED_WAITING状态,因为线程运行在Thread.sleep(1000); 休眠状态
        System.out.println("线程1状态:"+thread1.getState());
        //线程运行在BLOCKED状态,因为线程1还在休眠状态没释放锁 syn方法还进不去
        System.out.println("线程2状态:"+thread2.getState());
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程1状态:"+thread1.getState());
        System.out.println("线程2状态:"+thread2.getState());
    }
    @Override
    public void run() {
        syn();
    }
    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

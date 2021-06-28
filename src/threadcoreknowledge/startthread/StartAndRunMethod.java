package threadcoreknowledge.startthread;
/**
 * 对比start  run方法
 * 直接调用Runnable中的run()其实就是一个普通的类去调用方法,和线程没有任何关系, 所以它是运行在主线程中,
 * 而new Thread(runnable).start(); 是一个线程调用了start(),
 * start()含义:
 * 启动新线程,只有调start方法才是让线程处于就绪状态,等待系统分配cpu资源来运行线程
 *
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            System.out.println("thread name is:"+Thread.currentThread().getName());
        };
        runnable.run();

        new Thread(runnable).start();
    }
}

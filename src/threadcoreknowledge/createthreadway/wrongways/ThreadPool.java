package threadcoreknowledge.createthreadway.wrongways;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 演示线程池创建线程,但是不属于线程的创建方法,因为它的内部
 * 通过阅读源代码发现Executors.newCachedThreadPool() 进入后找到它的线程工厂,如果没有传会有个默认的线程工厂
 * DefaultThreadFactory,
 * public Thread newThread(Runnable r) {
 *             Thread t = new Thread(group, r,
 *                                   namePrefix + threadNumber.getAndIncrement(),
 *                                   0);
 *             if (t.isDaemon())
 *                 t.setDaemon(false);
 *             if (t.getPriority() != Thread.NORM_PRIORITY)
 *                 t.setPriority(Thread.NORM_PRIORITY);
 *             return t;
 *         }
 *         这个就是通过继承Thread类来创建一个线程的,也就是说线程池内部其实就是使用了继承Thread类方式来创建线程,只不过线程池起到了缓存的作用
 */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            executorService.submit(new Task());
        }
    }
}
 class Task implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"--执行任务");
    }
}

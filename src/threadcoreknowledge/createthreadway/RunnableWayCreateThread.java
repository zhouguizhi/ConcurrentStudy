package threadcoreknowledge.createthreadway;
/**
 * 实现Runnable 方式创建线程
 */
public class RunnableWayCreateThread implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableWayCreateThread());
        thread.start();
    }
    @Override
    public void run() {
        System.out.println("通过实现Runnable方式创建线程");
    }
}

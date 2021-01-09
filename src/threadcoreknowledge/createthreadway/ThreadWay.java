package threadcoreknowledge.createthreadway;
/**
 * 通过继承Thread类的方式创建线程
 */
public class ThreadWay extends Thread{
    public static void main(String[] args) {
        ThreadWay thread = new ThreadWay();
        thread.start();
    }
    @Override
    public void run() {
        super.run();
    }
}

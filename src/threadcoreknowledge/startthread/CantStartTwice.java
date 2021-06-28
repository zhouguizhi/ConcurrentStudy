package threadcoreknowledge.startthread;
/**
 * 描述:thread二次调用start方法会怎么样? 这也是一道面试题
 * Exception in thread "main" java.lang.IllegalThreadStateException
 */
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}

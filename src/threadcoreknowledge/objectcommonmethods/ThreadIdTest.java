package threadcoreknowledge.objectcommonmethods;
/**
 * 线程id属性
 * 描述:线程id是从1开始的,从下面代码中得到答案
 * JVM运气起来后,我们自己创建的线程ID早已不是0
 */
public class ThreadIdTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());
        Thread thread = new Thread();
        //hread线程ID:11 为啥子线程id是11呢？那是因为jVM在背后还帮我们创建了很多线程,可以debug模式去查看, 比如会创建gc线程等
        System.out.println("thread线程ID:"+thread.getId());
    }
}

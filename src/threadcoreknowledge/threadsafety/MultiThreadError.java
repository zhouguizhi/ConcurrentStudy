package threadcoreknowledge.threadsafety;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:在多线程中运行结果出错
 * 演示计数不正确(减少情况) 找出具体出错的位置
 * 为什么使用while循环index最后是10000呢？
 *
 */
public class MultiThreadError implements Runnable{
    int index = 0;
    static AtomicInteger realCount = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    static MultiThreadError multiThreadError = new MultiThreadError();
    final boolean[] marked = new boolean[1000000000];
    public static void main(String[] args) {

        Thread thread1 = new Thread(multiThreadError);
        Thread thread2 = new Thread(multiThreadError);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("index:="+multiThreadError.index);
        System.out.println("realCount:="+realCount.get());
        System.out.println("wrongCount:="+wrongCount.get());
    }
    @Override
    public void run() {
//        while (index<10000){
//            index++;
//        }
        for(int i=0;i<10000;i++){
            index++;
            synchronized (multiThreadError){
                if(marked[index]){
                    System.out.println("发生了错误:"+index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
            realCount.incrementAndGet();
        }
    }
}

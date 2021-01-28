package threadcoreknowledge.jmm;
/**
 * 描述:在多线程情况下演示a++出错问题
 */
public class MultiThreadError implements Runnable{
    private int a = 0;
    public static void main(String[] args) throws InterruptedException {
        MultiThreadError multiThreadError = new MultiThreadError();
        Thread thread1 =  new Thread(multiThreadError);
        Thread thread2 =  new Thread(multiThreadError);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        
        System.out.println("a:="+multiThreadError.a);
    }
    @Override
    public void run() {
        for(int i=0;i<10000;i++){
            a++;
        }
    }
}

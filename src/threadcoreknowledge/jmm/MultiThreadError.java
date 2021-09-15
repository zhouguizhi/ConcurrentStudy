package threadcoreknowledge.jmm;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 描述:在多线程情况下演示a++出错问题
 * 演示计数不正确(减少),找出具体出错的位置
 */
public class MultiThreadError implements Runnable{
    Object object = new Object();
//    private AtomicInteger a = new AtomicInteger();
    private int a;
     int index = 0;
//    private  AtomI a = 0;
    public static void main(String[] args) throws InterruptedException {
        MultiThreadError multiThreadError = new MultiThreadError();
        Thread thread1 =  new Thread(multiThreadError);
        Thread thread2 =  new Thread(multiThreadError);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("a:="+multiThreadError.a);
//        System.out.println("index:="+multiThreadError.index);
    }
    @Override
    public void run() {
        //预期是20000,因为是2个线程每次都++,所以理想情况下a应该是10000*2(2个线程),但是实际情况是不到20000,这就出现了数据不安全的情况了
        //因为a++,其实在执行的时候是分为三步的 ,第一步 a=1,第二步a+1,第三步a=2,而且2个线程并不知道对方的值,那么问题就出现了
        //比如线程一执行到了a++,这个时候假如a=1,然后a+1,的结果是2, 这个时候cpu切换到线程二去了,还是会执行a+1,因为线程二并不知道线程一加了,所以结果是2,这个时候再执行线程1的第三步, 线程二的第三步,这就是为啥结果小于20000的原因了
        synchronized (object){

            for(int i=0;i<10000;i++){
                a++;
            }
        }

//        while (index<10000){
//            index++;
//        }
    }
}

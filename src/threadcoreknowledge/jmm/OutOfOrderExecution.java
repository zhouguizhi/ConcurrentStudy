package threadcoreknowledge.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 描述:演示重排序现象问题
 * 综合上面的可能会四种情况
 * 1:线程1先运行 线程2后运行,结果是x=0,y=1
 * 2:线程2先运行,线程1后运行,x=1,y=0 ,让线程2先执行start()就很容易出现
 * 3:线程1和线程2都统一先执行到第一步,然后再执行第二行代码,a=1,b=1,然后再执行x = b  y=a,这个时候就是x=1,y=1了
 * 4:第四种情况就是x=0,y=0了 这个是在什么场景下发生的呢？
 */
public class OutOfOrderExecution {
    private static int x,y=0;
    private static int a,b = 0;
    public static void main(String[] args) throws InterruptedException {
        int i=0;
        for(; ;) {
            i++;
            x=0;
            y=0;
            a=0;
            b=0;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            };
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            };
            thread2.start();
            thread1.start();
            countDownLatch.countDown();
            thread1.join();
            thread2.join();
            String result = "第"+i+"次("+"x="+x+","+"y="+y+")";
            
//            if(x==1&&y==1){
//                System.out.println(result);
//                break;
//            }else{
//                System.out.println(result);
//            }
            //这是演示重排序的问题
            if(x==0&&y==0){
                System.out.println(result);
                break;
            }else{
                System.out.println(result);
            }
        }
    }
}

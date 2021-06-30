package threadcoreknowledge.stopthreads.volatiledemo;
/**
 * 描述:使用volatile设计boolean标记 当陷入粗塞的时候volatile是无法停止线程的
 * 例子说明:生产者生产的速度很快,消费者消费的很慢,所以粗塞队列满了以后,生产者就会粗塞,等待消费者进行消费,
 */
public class WrongWayVolatileCantStop implements Runnable{
    private volatile boolean canceld = false;

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileCantStop wrongWayVolatile = new WrongWayVolatileCantStop();
        Thread thread = new Thread(new WrongWayVolatileCantStop());
        thread.start();
        Thread.sleep(2000);
        wrongWayVolatile.canceld = true;

    }
    @Override
    public void run() {
        int num = 0;
        try{
            while (num<=1000&&!canceld){
                if(num%100==0){
                    System.out.println(num+"是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        }catch (Exception e){

        }
    }
}

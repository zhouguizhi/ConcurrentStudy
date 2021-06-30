package threadcoreknowledge.stopthreads.volatiledemo;
/**
 * 描述:使用volatile设计boolean标记 不正确的方式
 */
public class WrongWayVolatile implements Runnable{
    private volatile boolean canceld = false;

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(new WrongWayVolatile());
        thread.start();
        Thread.sleep(5000);
        wrongWayVolatile.canceld = true;

    }
    @Override
    public void run() {
        int num = 0;
        try{
            while (num<=100000&&!canceld){
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

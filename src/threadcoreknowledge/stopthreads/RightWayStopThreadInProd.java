package threadcoreknowledge.stopthreads;
/**
 * 描述:这是在实际的开发场景中怎么去解决while里面带try...catch...,导致中断失效的bug,看这个类CanInterrupt
 * 最佳实战,catch住了InterruptedException之后,在方法签名中抛出异常,那么就会在run方法中强制try...catch...
 *
 */
public class RightWayStopThreadInProd implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
    @Override
    public void run() {
        while (true){
            System.out.println("开始执行...");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }
}

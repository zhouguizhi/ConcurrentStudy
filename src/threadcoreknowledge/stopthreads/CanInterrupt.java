package threadcoreknowledge.stopthreads;
/**
 * 描述:如果while里面房try...catch..,会导致中断失效
 * 为什么呢?
 * 这是因为线程被中断后被catch住了,而while条件又没满足,所以就会一直执行，因为在while中有sleep函数,它会清除中断标记的
 * 那么这里怎么解决呢?
 * 第一种方法:传递中断
 * 第二种:不想或者无法传递,恢复中断
 * 不能去屏蔽中断
 */
public class CanInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;
            //这里!Thread.currentThread().isInterrupted()条件是无效的,因为在while中有sleep函数,它会清除中断标记的
            while (num<=10000&&!Thread.currentThread().isInterrupted()){
                if(num%100==0){
                    System.out.println(num+"是100的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}

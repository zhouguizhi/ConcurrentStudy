package threadcoreknowledge.stopthreads;
/**
 * 描述:线程在每次迭代后都阻塞,这里所说的迭代是只for或者while
 * 在迭代(for or while)中线程被粗塞 这个时候线程中断的情况
 * 如果在执行过程中，每次循环都会调用sleep或wait等方法，那么不需要每次迭代都检查是否已中断 因为sleep方法会清空中断标记
 * 在while里面出现了异常,直接退出while循环了,可以在for循环里面模拟写个测试类似的场景
 */
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
//        try{
//        for(int i=0;i<10;i++){
//                System.out.println("i="+i);
//                System.out.println(i/0);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    //sleep方法中断后
                    //抛出此异常时清除当前线程的中断状态 所以 Thread.currentThread().isInterrupted()在while中加入这个条件是无效的
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}

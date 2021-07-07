package threadcoreknowledge.objectcommonmethods;
/**
 * 描述:join时候线程被中断
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("thread1 finished");
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("子线程中断");
                }
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"线程被中断");
            //main线程被中断后,把 中断传递给thread1线程
            thread.interrupt();
//            e.printStackTrace();
        }
        System.out.println("子线程运行完毕");
    }
}

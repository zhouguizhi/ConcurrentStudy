package threadcoreknowledge.objectcommonmethods;
/**
 * 查看join 时线程处于什么状态 这个是查看main线程处于WAITING状态,而子线程是处于RUNNABLE状态
 */
public class JoinThreadState {
    private static Thread mainThread;
    public static void main(String[] args) {
        mainThread = Thread.currentThread();
        MyThread myThrad = new MyThread();
        myThrad.start();
        try {
            myThrad.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("住线程执行完毕");
    }
    static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("开始执行子线程");
            try {
                sleep(3000);
                System.out.println("主线程的状态:="+mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

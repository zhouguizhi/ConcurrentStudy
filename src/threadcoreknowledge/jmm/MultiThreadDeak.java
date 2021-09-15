package threadcoreknowledge.jmm;
/**
 * 演示死锁问题
 */
public class MultiThreadDeak implements Runnable{
    int flag = 1;
    static Object object1 = new Object();
    static Object object2 = new Object();
    @Override
    public void run() {
        System.out.println("flag:="+flag);
        if(flag==1){
            synchronized (object1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2){
                    System.out.println("11");
                }
            }
        }

        if(flag==0){
            synchronized (object2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1){
                    System.out.println("000");
                }
            }
        }
    }

    public static void main(String[] args) {
        MultiThreadDeak rl = new MultiThreadDeak();
        MultiThreadDeak r2 = new MultiThreadDeak();
        rl.flag = 1;
        r2.flag = 0;
        new Thread(rl).start();
        new Thread(r2).start();
    }
}

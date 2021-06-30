package threadcoreknowledge.objectcommonmethods;
/**
 * 描述:展示wait和notify基本用法
 * 1:研究代码的执行顺序
 * 2:证明wait是释放锁的
 * 要证明wait是释放锁的话,其实就是证明另外一个线程同步代码块是否在wait释放了锁后执行了代码,如果执行了就说明这个wait是释放锁的
 * 而不是往死里想怎么证明,换一个思考方式
 */
public class Wait {
    private static Object object = new Object();
    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程1开始执行了");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1获取到了锁");
            }
        }
    }
    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                object.notify();
                System.out.println("线程2调用了notify");
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        //必须是Thread1 先执行, 如果不是线程1先执行还是线程2先执行的话,到时候它唤醒的时候 线程1不是还没进行run方法代码嘛,这就是为啥线程1要先执行的原因,我们可以让主
        //线程休眠200毫秒后再调用线程2start()方法,其实这是一种不能保证100%的让线程1执行,但是在这我们暂时这么做吧
        thread1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

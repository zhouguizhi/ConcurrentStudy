package threadcoreknowledge.objectcommonmethods;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * join方法面试题 :有三个线程T1,T2,T3,如何保证顺序执行？
 * 第一种实现:使用join方法,使用join方法唯一的一点就是要搞懂谁等谁,这个问题搞懂了,那么就好做了,在t2线程中调用t1.join()其实是让t1执行完后再执行t2,那么按照
 * 这个思想的话就简单了,在t2线程中等待t1,在t3线程中等待t2
 * 第二种是实现方案是通过java给我们提供的工具类
 * 第三种是是实现方式是通过线程池的方法
 * 第四种方法就是在T1线程执行完毕后执行T2 线程 T2线程执行完毕后执行T3线程,这是最简单的方法 ,就是T1线程要持有T2线程的对象,T2线程要持有T3线程的变量 method4方法
 *
 */
public class JoinInterviewQuestion {
    public static void main(String[] args) throws InterruptedException {
//        method1();
//        method2();
        method4();

    }
    private static void method4() throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable,"T1");
        Thread thread2 = new Thread(myRunnable,"T2");
        Thread thread3 = new Thread(myRunnable,"T3");
        thread1.start();
        thread1.join();
        System.out.println("t2线程开始调用start方法");
        thread2.start();
        thread2.join();
        System.out.println("t3线程开始调用start方法");
        thread3.start();
        thread3.join();
    }
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程："+Thread.currentThread().getName()+"执行完毕");
        }
    }
    public static void method1(){
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1");
            }
        });
        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t3");
            }

        });

        t1.start();
        t2.start();
        t3.start();
    }
    public static void method2(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Mythread1());
        executorService.execute(new Mythread2());
        executorService.execute(new Mythread3());

    }
    public static void method3(){
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Mythread4 mythread4 = new Mythread4(countDownLatch);
        Mythread5 mythread5 = new Mythread5(countDownLatch);
        Mythread6 mythread6 = new Mythread6(countDownLatch);
        mythread4.start();
        mythread5.start();
        mythread6.start();
    }

    static class Mythread4 extends Thread{
        private final CountDownLatch countDownLatch;
        public Mythread4(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            super.run();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1执行完毕...");
            countDownLatch.countDown();
        }
    }
    static class Mythread5 extends Thread{
        private final CountDownLatch countDownLatch;
        public Mythread5(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            super.run();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2执行完毕...");
        }
    }
    static class Mythread6 extends Thread{
        private final CountDownLatch countDownLatch;
        public Mythread6(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            super.run();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程3执行完毕...");
        }
    }



    static class Mythread1 extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1执行完毕...");
        }
    }
    static class Mythread2 extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2执行完毕...");
        }
    }
    static class Mythread3 extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程3执行完毕...");
        }
    }
}

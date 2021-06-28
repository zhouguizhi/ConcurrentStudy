package threadcoreknowledge.createthreadway;
/**
 * 描述:同时使用实现Runnable接口和继承Thread类
 */
public class BothRunnableAndThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是实现了Runnable接口");
            }
        }){
            @Override
            public void run() {
                /**
                 * super.run()方法的话,通过看源代码
                 * if (target != null) {
                 *             target.run();
                 *  }
                 *  这就会调用上面的Runnable中的run(),然后会重写run方法,那么这二个方法都会打印
                 */
                //
//                super.run();
                System.out.println("我是继承了Thread类");
            }
        }.start();
    }
}

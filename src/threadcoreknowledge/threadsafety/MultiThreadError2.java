package threadcoreknowledge.threadsafety;
/**
 * 描述:初始化未完毕,就this赋值
 */
public class MultiThreadError2 {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMark().start();
//        Thread.sleep(10);
        Thread.sleep(105);
        if(point!=null){
            System.out.println(point);
        }
    }
}
    class Point{
        private final int x,y;
        public Point(int x,int y) throws InterruptedException {
            this.x = x;
            MultiThreadError2.point = this;
            Thread.sleep(100);
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    class PointMark extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                new Point(1,1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


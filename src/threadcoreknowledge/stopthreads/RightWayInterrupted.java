package threadcoreknowledge.stopthreads;
/**
 * 描述:注意Thread.interrupted()方法的目标对象是当前线程,而不管本方法来自那个对象
 * 也就是说不管是对象调用interrupted()方法还是Thread.interrupted()是看这个是在那个线程中被调用,和这个线程有关系
 * 比如threadOne.interrupted()是在主线程中被调用,那么就是判断主线程是否被中断,而和threadOne线程没有任何关系
 */
public class RightWayInterrupted {
    public static void main(String[] args) {

    }
}

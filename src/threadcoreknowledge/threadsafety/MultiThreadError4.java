package threadcoreknowledge.threadsafety;
import java.util.HashMap;
import java.util.Map;
/**
 * 描述: 构造函数中新建线程这是一个很危险的工作
 * 意思就是说如果在构造函数中使用了线程,比如在线程中进行赋值操作,因为线程执行需要cpu分配时间来执行的
 * 如果马上获取这个值,可能会操作异常,就好比这个例子空指针异常一样
 */
public class MultiThreadError4 {
    private Map<String,String> map;
    public MultiThreadError4(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                map = new HashMap<>();
                map.put("1","周一");
                map.put("2","周二");
                map.put("3","周三");
                map.put("4","周四");
            }
        }).start();

    }
    public Map<String,String> getMap(){
        return map;
    }

    public static void main(String[] args){
        MultiThreadError4 multiThreadError4 = new MultiThreadError4();
        System.out.println(multiThreadError4.getMap().get("1"));
    }
}

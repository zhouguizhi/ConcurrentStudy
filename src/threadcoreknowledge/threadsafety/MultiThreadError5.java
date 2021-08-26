package threadcoreknowledge.threadsafety;
import java.util.HashMap;
import java.util.Map;
/**
 * 描述:解决MultiThreadError1代码中的问题
 * 使用副本的方法,但是感觉这也有个问题如果
 * Map<String, String> map = multiThreadError5.getMapImproved();
 * 如果以后操作都是使用map集合 其实也会的,
 *
 * 下面这样不会出现问题那是因为每次getMapImproved()都是返回新的HashMap对象
 * System.out.println(multiThreadError5.getMapImproved().get("1"));
 * multiThreadError5.getMapImproved().remove("1");
 * System.out.println(multiThreadError5.getMapImproved().get("1"));
 *
 *
 */
public class MultiThreadError5 {
    private Map<String,String> map;
    public MultiThreadError5(){
        map = new HashMap<>();
        map.put("1","周一");
        map.put("2","周二");
        map.put("3","周三");
        map.put("4","周四");
    }
    public Map<String,String> getMapImproved(){
        return new HashMap<>(map);
    }
    public static void main(String[] args) {
        MultiThreadError5 multiThreadError5 = new MultiThreadError5();
        System.out.println(multiThreadError5.getMapImproved().get("1"));
        multiThreadError5.getMapImproved().remove("1");
        System.out.println(multiThreadError5.getMapImproved().get("1"));

    }
}

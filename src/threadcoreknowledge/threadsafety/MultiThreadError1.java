package threadcoreknowledge.threadsafety;
import java.util.HashMap;
import java.util.Map;
/**
 * 描述:发布逸出
 * 意思就是说如果获取map集合后马上就去改里面的值是很危险的
 */
public class MultiThreadError1 {
    private Map<String,String> map;
    public MultiThreadError1(){
        map = new HashMap<>();
        map.put("1","周一");
        map.put("2","周二");
        map.put("3","周三");
        map.put("4","周四");
    }
    public Map<String,String> getMap(){
        return map;
    }

    public static void main(String[] args) {
        MultiThreadError1 multiThreadError1 = new MultiThreadError1();
        Map<String, String> map = multiThreadError1.getMap();
        System.out.println(map.get("1"));
        map.remove("1");
        System.out.println(map.get("1"));
    }
}

package test.thread;

import java.util.HashMap;
import java.util.Map;

public class threadlocal {
    private static final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);


    public static void main(String[] args) {
        Map<String, Object> stringObjectMap = threadLocal.get();
        stringObjectMap.put("1", 1);
        ThreadLocal<Map<String, Object>> threadLocal2 = ThreadLocal.withInitial(HashMap::new);

        System.out.println(threadLocal2.get().get("1"));
    }
}

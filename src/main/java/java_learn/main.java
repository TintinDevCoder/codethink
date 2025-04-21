package java_learn;

import java.io.*;

public class main implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    public String requestId;
    public String interfaceName;
    public String methodName;
    public Object[] parameters;
    public Class<?>[] paramTypes;
    public main(String requestId, String interfaceName, String methodName, Object[] parameters, Class<?>[] paramTypes) {
        this.requestId = requestId;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameters = parameters;
        this.paramTypes = paramTypes;
    }
    public static void main(String[] args) {
        test<String> t = new test();
        t.key = "1";
        t.method2(t.method1("1", "2"));
    }
}
class test<T> {
    public T key;
    public <T> T method1(T num1, T num2) {
        System.out.println(key.getClass());
        return (T)"1";
    }
    public <T> void method2(T num) {
        System.out.println(num.getClass());
    }
}
class Pair<T, K> {
    private T first;
    private K last;

    public Pair(T first, K last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public K getLast() {
        return last;
    }
}
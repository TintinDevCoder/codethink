package changjingti;

/**
 * 单例模式：懒汉，饿汉，双重校验锁
 */
public class t4 {

}
class SingletonLH {
    private SingletonLH(){}

    private static SingletonLH singletonLH;

    public static SingletonLH getSingletonLH() {
        if (singletonLH == null) singletonLH = new SingletonLH();
        return singletonLH;
    }
}
class SingletonEH {
    private SingletonEH(){}

    private static SingletonEH singletonEH = new SingletonEH();

    public static SingletonEH getSingletonEH() {
        return singletonEH;
    }
}

class SingletonDoubleCheck {
    private SingletonDoubleCheck(){}
    private static volatile SingletonDoubleCheck singletonDoubleCheck;

    public static SingletonDoubleCheck getSingletonDoubleCheck() {
        if (singletonDoubleCheck == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (singletonDoubleCheck == null) {
                    singletonDoubleCheck = new SingletonDoubleCheck();
                }
            }
        }
        return singletonDoubleCheck;
    }
}
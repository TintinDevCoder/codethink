package codethink.erchashu;

public class test {
    void testmethod() throws ArithmeticException{
        try {
            int i = 1/0;
        }catch (Exception e){
            throw new ArithmeticException("");
        }
    }
    public static void main(String[] args) {
        test t = new test();
        try {
            t.testmethod();
        }catch (ArithmeticException e) {
            throw new ArithmeticException("catch");
        }
    }
}

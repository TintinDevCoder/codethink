package changjingti;

import java.util.Random;

public class t5 {

    public static void main(String[] args) {
        int n = 10000;
        int x = 10, y = 4;
        int[] tar = new int[x + 1];
        while (n-- != 0){
            int i = RandXFromRandY.randX(x, y);
            tar[i]++;
        }
        for (int i = 0; i < x + 1; i++) {
            System.out.println(i + ":" + tar[i]);
        }
    }
}
class RandFromRand4{
    static Random random = new Random();
    public static int rand4() {
        return random.nextInt(5);
    }
    public static int rand6() {
        while (true) {
            int sum = 0;
            for (int i = 0; i < 7; i++) {
                sum += rand4();
            }
            return sum % 7;
        }
    }
}
class RandXFromRandY {
    static Random random = new Random();
    public static int randY(int y) {
        return random.nextInt(y + 1);
    }
    public static int randX(int x, int y) {
        while (true) {
            int sum = 0;
            for (int i = 0; i < x + 1; i++) {
                sum += randY(y);
            }
            return sum % (x + 1);
        }
    }
}
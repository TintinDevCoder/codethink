package bishi.meituan;

import java.util.Scanner;

public class meituan3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int sum = 0;
            for(int i = 0; i < n; i++){
                int k = sc.nextInt();
                sum += k;
            }
            long x = sc.nextLong();
            long y = sc.nextLong();
            long p = sc.nextLong();
            long q = sc.nextLong();
            long sub = Math.abs((x + y) - (p + q));
            sum -= sub;
            if (sum < 0 ||sum % 2 != 0) {
                System.out.println("No");
            }else System.out.println("Yes");
        }
    }
}

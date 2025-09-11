package bishi.haixin;

import java.util.Scanner;

public class t1 {
    public static void stoneGame() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] a = new long[n + 1];
        long xorSum = 0;
        for (int i = 1; i < a.length; i++) {
            a[i] = sc.nextLong();
            xorSum ^= a[i];
        }
        System.out.println(xorSum != 0 ? "win" : "lose");
        for (int i = 0; i < m; i++) {
            int p = sc.nextInt();
            long k = sc.nextLong();
            xorSum ^= a[p];
            a[p] += k;
            xorSum ^= a[p];
            System.out.println(xorSum != 0 ? "win" : "lose");
        }
    }
    public static void build() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char digit = '9';
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(digit);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        stoneGame();
    }
}

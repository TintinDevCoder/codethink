package bishi.shunfeng;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class t1 {

    public static void xinhaodeng() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        final int MOD = 1000000007;
        long[][] dp = new long[n + 1][3];
        dp[1][0] = 8; //n位置不为1的正常
        dp[1][1] = 1; //n位置为1的正常
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (((dp[i - 1][0] + dp[i - 1][1]) % MOD) * 8) % MOD;
            dp[i][1] = (dp[i - 1][0] - (dp[i - 2][1] * 8) % MOD) % MOD;
        }
        System.out.println((dp[n][0] + dp[n][1]) % MOD);
    }

    public static void maopao() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- != 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                b[i] = sc.nextInt();
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(a[i], i);
            }
            int end = n - 2;
            int pos = map.get(b[n - 1]);
            boolean is = false;
            while (end >= 0) {
                Integer t = map.get(b[end]);
                if (t < pos) {
                    end--;
                    pos = t;
                }else {
                    System.out.println(end + 1);
                    is = true;
                    break;
                }
            }
            if (!is) System.out.println(0);
        }
    }

    public static void main(String[] args) {
        maopao();
    }
}

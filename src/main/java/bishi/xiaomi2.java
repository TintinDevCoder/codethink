package bishi;

import java.util.Scanner;

public class xiaomi2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] b = new int[m];
        int[] c = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            c[i] = sc.nextInt();
        }
        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = Long.MAX_VALUE;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = Long.MAX_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    long s = dp[k][j - 1] + c[j - 1];
                    long ts = 0;
                    for (int o = k; o < i; o++) {
                        ts += (long) b[j - 1] - a[o];
                    }
                    s += ts;
                    dp[i][j] = Math.min(dp[i][j], s);
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}

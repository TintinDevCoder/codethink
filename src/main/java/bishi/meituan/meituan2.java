package bishi.meituan;

import java.util.Scanner;

public class meituan2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }
        long[][] dp = new long[n + 1][2];
        dp[1][0] = 0;
        dp[1][1] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + i;
            int killCount = countKill(dp, i - 1) + 1;
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]) + a[i - 1] + (killCount % 10) * a[i - 1];
        }
        System.out.println(Math.max(dp[n][0], dp[n][1]));
    }
    public static int countKill(long[][] dp, int i) {
        int count = 0;
        for (int j = 1; j <= i; j++) {
            if (dp[j][1] > dp[j][0]){
                count++;
            }
        }
        return count;
    }
}

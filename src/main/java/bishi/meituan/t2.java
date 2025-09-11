package bishi.meituan;

import java.util.Scanner;

public class t2 {
    static long stackResult = 0;
    public static void stackMonsterDfs(int[] a, int index, long sum, int kill) {
        if (index >= a.length) {
            stackResult = Math.max(stackResult, sum);
            return;
        }
        if (a[index] >= index) {
            stackMonsterDfs(a, index + 1, sum + a[index] + ((kill + 1) % 10) * a[index], kill + 1);
        }else {
            stackMonsterDfs(a, index + 1, sum + a[index] + ((kill + 1) % 10) * a[index], kill + 1);
            stackMonsterDfs(a, index + 1, sum + index, kill);
        }
    }
    private static long calculateMaxExperience(int n, long[] a) {
        long[][] dp = new long[n + 1][n + 2];

        for (int i = 1; i <= n; i++) {
            for (int k = 0; k < i; k++) {
                // 放走当前怪物
                dp[i][k] = dp[i - 1][k] + i;

                // 击败当前怪物
                dp[i][k + 1] = Math.max(dp[i][k + 1], dp[i - 1][k] + a[i - 1] + ((k + 1) % 10) * a[i - 1]);
            }
        }

        // 找到最大经验值
        long maxExperience = 0;
        for (int k = 0; k <= n; k++) {
            maxExperience = Math.max(maxExperience, dp[n][k]);
        }

        return maxExperience;
    }
    public static void stackMonster() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }
        long l = calculateMaxExperience(n, a);
        System.out.println(l);
    }

    public static void main(String[] args) {
        stackMonster();
    }
}

package codethink.dp;

public class part1 {
    public int fib(int n) {
        if (n <= 1) return n;
        int n1 = 0, n2 = 1;
        for (int i = 0; i < n - 1; i++) {
            int temp = n1 + n2;
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int star1 = 1;
        int star2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = star1 + star2;
            star1 = star2;
            star2 = temp;
        }
        return star2;
    }
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];
        dp[1] = cost[0];
        dp[2] = cost[1];
        for (int i = 3; i <= len; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i - 1];
        }
        return Math.min(dp[len], dp[len - 1]);
    }

}

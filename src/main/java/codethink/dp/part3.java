package codethink.dp;

import java.util.Scanner;

public class part3 {
    public static int bag(int[] valve, int[] weight, int capacity) {
        int len = valve.length;
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < len; i++) {
            for (int j = capacity; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + valve[i]);
            }
        }
        return dp[capacity];
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                if (!dp[j]) dp[j] = dp[j - nums[i]];
            }
        }
        return dp[sum];
    }
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] weight = new int[m];
        int[] value = new int[m];
        for (int i = 0; i < m; i++) {
            weight[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            value[i] = sc.nextInt();
        }
        int r = bag(value, weight, n);
        System.out.println(r);
    }
}

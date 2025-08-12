package mianjing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class zijie {
    static class  Line {
        String ip;
        int start;
        int end;
        Line() {}
        Line(String ip, int start, int end) {
            this.ip = ip;
            this.start = start;
            this.end = end;
        }
    }
    public int findPeakOnlineCount(Line[] lines) {
        int len = lines.length;
        int maxSeconds = 86400; // 一天的秒数
        int[] diff = new int[maxSeconds + 2];
        for (Line line : lines) {
            diff[line.start]++;
            diff[line.end + 1]--;
        }
        int maxCount = 0;
        int currentNum = 0;
        int peekTime = 0;
        for (int i = 1; i <= maxSeconds; i++) {
            currentNum += diff[i];
            if (currentNum > maxCount) {
                maxCount = currentNum;
                peekTime = i;
            }
        }
        return peekTime;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                if (nums[right] < 0) break;
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> n = new ArrayList<>();
                    n.add(nums[i]);
                    n.add(nums[left]);
                    n.add(nums[right]);
                    result.add(n);
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }else if (sum > 0) right--;
                else left++;
            }
        }
        return result;
    }

    //买卖股票的最佳时机
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        int result = 0;
        int maxPrice = prices[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            result = Math.max(result, maxPrice - prices[i]);
        }
        return result;
    }
    public int maxProfit2(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) sum += prices[i] - prices[i - 1];
        }
        return sum;
    }
    public int maxProfit3(int[] prices) {
        int[] dp = new int[4]; // 0:第一次买入  1:第一次卖出  2:第二次买入  3:第二次卖出
        dp[0] = dp[2] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[0] = Math.max(dp[0], -prices[i]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
            dp[2] = Math.max(dp[2], dp[1] - prices[i]);
            dp[3] = Math.max(dp[3], dp[2] + prices[i]);
        }
        return dp[3];
    }
    public int maxProfit4(int k, int[] prices) {
        int[] dp = new int[2 * k + 1];
        for (int i = 1; i < 2 * k; i+=2) {
            dp[i] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= 2 * k; j+=2) {
                dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + prices[i]);
            }
        }
        return dp[2 * k];
    }
    public int maxProfit5(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len + 1][2]; // 0：持有  1：不持有
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[len - 1][1];
    }
    public int maxProfit6(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len][3]; // 0：买入 1：不持有，当天为冷冻期 2：不持有，当天不是冷冻期
        dp[0][0] = -prices[0];
        dp[0][1] = dp[0][2] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }
        return Math.max(dp[len - 1][1], dp[len - 1][2]);
    }
    public static void main(String[] args) {
        zijie z = new zijie();
        z.threeSum(new int[]{-1,0,1,2,-1,-4});
    }

}

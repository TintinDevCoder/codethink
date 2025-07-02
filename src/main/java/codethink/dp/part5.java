package codethink.dp;

public class part5 {
    public int rob1(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len + 1][2];
        for (int i = 1; i <= len; i++) {
            dp[i][0] = dp[i - 1][1] + nums[i - 1];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.max(dp[len][0], dp[len][1]);
    }

    public int rob2(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int len = nums.length - 1;
        int[][] dp = new int[len + 1][2];
        for (int i = 1; i <= len; i++) {
            dp[i][0] = dp[i - 1][1] + nums[i - 1];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        int re1 = Math.max(dp[len][0], dp[len][1]);
        dp = new int[len + 1][2];
        for (int i = 1; i <= len; i++) {
            dp[i][0] = dp[i - 1][1] + nums[i + 1 - 1];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        int re2 = Math.max(dp[len][0], dp[len][1]);
        return Math.max(re1, re2);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int rob3(TreeNode root) {
        int[] result = robNode(root);
        return Math.max(result[0], result[1]);
    }
    public int[] robNode(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] left = robNode(root.left);
        int[] right = robNode(root.right);
        int have = root.val + left[1] + right[1];
        int nohave = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{have, nohave};
    }
    public int maxProfit1(int[] prices) {
        int result = 0;
        int max = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            result = Math.max(result, max - prices[i]);
        }
        return result;
    }
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len + 1][2]; //0 持有 1 不持有
        dp[0][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
    public int maxProfit3(int[] prices) {
        int[] dp = new int[5];
        dp[1] = dp[3] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[1] = Math.max(dp[1], -prices[i]);
            dp[2] = Math.max(dp[2], dp[1] + prices[i]);
            dp[3] = Math.max(dp[3], dp[2] - prices[i]);
            dp[4] = Math.max(dp[4], dp[3] + prices[i]);
        }
        return dp[4];
    }
    public int maxProfit4(int k, int[] prices) {
        int[] dp = new int[2 * k + 1];
        for (int i = 1; i < 2 * k; i += 2) dp[i] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= 2 * k; j += 2) {
                dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + prices[i]);
            }
        }
        return dp[2 * k];
    }
    public int maxProfit5(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] dp = new int[3];//0:持股 1:不持股并处于冷冻期 2:不持股并不处于冷冻期
        dp[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int temp = dp[0];
            dp[0] = Math.max(dp[0], dp[2] - prices[i]);
            dp[2] = Math.max(dp[2], dp[1]);
            dp[1] = temp + prices[i];
        }
        return Math.max(dp[1], dp[2]);
    }
    public int maxProfit6(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len + 1][2]; //0 持有 1 不持有
        dp[0][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i] - fee, dp[i - 1][1]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
    public int lengthOfLIS1(int[] nums) {
        int len = nums.length;
        if (len == 1) return 1;
        int[] dp = new int[len + 1];
        int result = 0;
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
    public int lengthOfLIS2(int[] nums) {
        int[] target = new int[nums.length + 1];
        int index = 1;
        target[index] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (target[index] < nums[i]) {
                target[++index] = nums[i];
            }else {
                int left = 1;
                int right = index;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (target[mid] < nums[i]) left = mid + 1;
                    else right = mid - 1;
                }
                target[left] = nums[i];
            }
        }
        return index;
    }
    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        int result = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) dp[i] = dp[i - 1] + 1;
            else dp[i] = 1;
            result = Math.max(result, dp[i]);
        }
        return result;
    }
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] dp = new int[len1 + 1];
        int result = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = len2; j >= 1; j--) {
                if (nums2[j - 1] == nums1[i - 1]) dp[j] = dp[j - 1] + 1;
                else dp[j] = 0;
                result = Math.max(result, dp[j]);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        part5 p = new part5();
        p.maxProfit2(new int[]{1,2,3,4,5});
    }
}

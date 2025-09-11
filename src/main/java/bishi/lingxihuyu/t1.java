package bishi.lingxihuyu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class t1 {
    public static int findMaxConsecutiveOnes (int[] nums) {
        // write code here
        int len = nums.length;
        int result = 0;
        int[] count = new int[len];
        count[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] == 1) {
                count[i] = count[i - 1] + 1;
            }else {
                count[i] = 0;
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                int sum = 0;
                if (i != 0) sum += count[i - 1];
                int j = i + 1;
                while (j < len && nums[j] != 0) {
                    j++;
                    sum++;
                }
                result = Math.max(result, sum + 1);
            }
        }
        return result;
    }
    public boolean isJinBu(int num) {
        String s = String.valueOf(num);
        for (int i = 0; i < s.length() - 1; i++) {
            int d1 = s.charAt(i) - '0';
            int d2 = s.charAt(i + 1) - '0';
            if (Math.abs(d1 - d2) != 1) {
                return false;
            }
        }
        return true;
    }
    public ArrayList<Integer> countSteppingNumbers (int low, int high) {
        // write code here
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = low; i <= high; i++) {
            if (isJinBu(i)) {
                list.add(i);
            }
        }
        return list;
    }
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public int splitArray (int[] nums) {
        // write code here
        int len = nums.length;
        int[] dp = new int[len + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = i; j <= len; j++) {
                if (gcd(nums[i - 1], nums[j - 1]) > 1) {
                    dp[j] = Math.min(dp[i - 1] + 1, dp[j]);
                }else {
                    dp[j] = Math.min(dp[j - 1] + 1, dp[j]);
                }
            }
        }
        return dp[len];
    }
    public static void main(String[] args) {
        t1 t = new t1();
        t.splitArray(new int[]{12, 36, 45, 56});
    }
}

package likou150;

import java.util.*;

public class DoublePoint {
    /**
     * 125. 验证回文串
     *
     * @param s
     * @return
     */
    public boolean isPalindrome1(String s) {
        char[] c = s.toCharArray();
        Stack<Character> stackUse = new Stack<>();
        int k = 0;
        for (char c1 : c) {
            if ((c1 >= 'a' && c1 <= 'z') || (c1 >= '0' && c1 <= '9')) {
                c[k++] = c1;
                stackUse.push(c1);
            } else if (c1 >= 'A' && c1 <= 'Z') {
                stackUse.push(Character.toLowerCase(c1));
                c[k++] = Character.toLowerCase(c1);
            }
        }
        int len = stackUse.size() / 2;
        for (int i = 0; i < len; i++) {
            Character pop = stackUse.pop();
            if (pop != c[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(String s) {
        char[] c = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(c[left])) left++;
            while (left < right && !Character.isLetterOrDigit(c[right])) right--;
            if (Character.toLowerCase(c[left]) != Character.toLowerCase(c[right])) return false;
            left++;
            right--;
        }
        return true;
    }

    /**
     * 392. 判断子序列
     *
     * @param s
     * @param t
     * @return
     */
    //双指针
    public boolean isSubsequence1(String s, String t) {
        if (s.equals("")) return true;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int index = 0;
        for (char c : tc) {
            if (sc[index] == c) {
                index++;
            }
            if (index == sc.length) break;
        }
        return index == sc.length;
    }

    //dp
    public boolean isSubsequence2(String s, String t) {
        if (s.equals("")) return true;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[][] dp = new int[sc.length + 1][tc.length + 1];
        for (int i = 1; i <= sc.length; i++) {
            for (int j = 1; j <= tc.length; j++) {
                if (sc[i - 1] == tc[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }
        return dp[sc.length][tc.length] == s.length();
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int sum = 0;
        while (left < right) {
            sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 11. 盛最多水的容器
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;
        while (left < right) {
            result = Math.max(result, (right - left) * Math.min(height[left], height[right]));
            if (height[left] > height[right]) right--;
            else left++;
        }
        return result;
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[right] < 0) break;
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else left++;
            }
        }
        return result;
    }





    public static void main(String[] args) {
        DoublePoint dp = new DoublePoint();
    }
}

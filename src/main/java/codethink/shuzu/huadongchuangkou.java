package codethink.shuzu;

public class huadongchuangkou {
    /*209.长度最小的子数组https://leetcode.cn/problems/minimum-size-subarray-sum/description/*/
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int minnums = Integer.MAX_VALUE;
        int now = 0;
        for (int end = 0; end < nums.length; end++) {
            now += nums[end];
            if (now >= target) {
                while (start <= end && now >= target) {
                    now -= nums[start++];
                }
                minnums = Math.min(end - start + 2, minnums);
            }
        }
        return minnums == Integer.MAX_VALUE ? 0 : minnums;
    }
    /*76. 最小覆盖子串https://leetcode.cn/problems/minimum-window-substring/description/*/
    public String minWindow(String s, String t) {
        int[] counts = new int[123];
        int count = t.length();
        for (int i = 0; i < count; i++) {
            counts[t.charAt(i)]++;
        }
        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
        int rleft = 0, rright = 0;
        while (right < s.length()) {
            if (counts[s.charAt(right)]-- > 0) count--;
            while (left <= right && count == 0) {
                if (right - left < len) {
                    rleft = left;
                    rright = right;
                    len = right - left + 1;
                }
                if (++counts[s.charAt(left++)] > 0) count++;
            }
            right++;
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(rleft, rright + 1);
    }
    /*904. 水果成篮https://leetcode.cn/problems/fruit-into-baskets/description/*/
    public int totalFruit(int[] fruits) {
        int slow = 0, fast = 0;
        int maxlen = 0;
        int f1 = fruits[0], f2 = fruits[0];
        for (fast = 1; fast < fruits.length; fast++) {
            if (fruits[fast] != f1 && fruits[fast] != f2) {
                maxlen = Math.max(maxlen, fast - slow);
                slow = fast - 1;
                f2 = fruits[fast];
                f1 = fruits[slow];
                while (slow >= 1 && f1 == fruits[slow - 1]) {
                    slow--;
                }
            }
        }
        return Math.max(maxlen, fast - slow);
    }
    public static void main(String[] args) {
        System.out.println((int)'A');
        huadongchuangkou s = new huadongchuangkou();
        s.totalFruit(new int[]{0,1,2,2});
    }
}

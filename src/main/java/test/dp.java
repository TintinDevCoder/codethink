package test;

public class dp {
    public int lengthOfLIS1(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        int result = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        int[] target = new int[len + 1];
        target[0] = nums[0];
        int index = 0;
        for (int i = 1; i < len; i++) {
            if (target[index] < nums[i]) {
                target[++index] = nums[i];
            } else {
                int left = 0, right = index;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (target[mid] < nums[i]) left = mid + 1;
                    else right = mid - 1;
                }
                target[left] = nums[i];
            }
        }
        return index + 1;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int len1 = s1.length;
        int len2 = s2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (s1[i] == s2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }else dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }
        return dp[len1][len2];
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }else dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }
        return dp[len1][len2];
    }

    public int findLengthOfLCIS1(int[] nums) {
        int n = 1;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                n++;
            }else {
                result = Math.max(result, n);
                n = 1;
            }
        }
        return Math.max(result, n);
    }

    public int findLengthOfLCIS2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            if (nums[i] > nums[i - 1]) { // 连续记录
                dp[i] = dp[i - 1] + 1;
            }
            if (dp[i] > result) result = dp[i];
        }
        return result;
    }
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int result = 0;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    result = Math.max(result, dp[i + 1][j + 1]);
                }
            }
        }
        return result;
    }
    public int maxSubArray1(int[] nums) {
        int result = Integer.MIN_VALUE;
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (n + nums[i] < nums[i]) {
                n = nums[i];
            }else n += nums[i];
            result = Math.max(result, n);
        }
        return result;
    }
    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }
    public boolean isSubsequence1(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int slen = sc.length;
        int tlen = tc.length;
        int[][] dp = new int[slen + 1][tlen + 1];
        for (int i = 0; i < slen; i++) {
            for (int j = 0; j < tlen; j++) {
                if (sc[i] == tc[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }else dp[i + 1][j + 1] = dp[i + 1][j];
            }
        }
        return dp[slen][tlen] == s.length();
    }
    public boolean isSubsequence2(String s, String t) {
        if (s.equals("")) return true;
        if (t.equals("")) return false;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int j = 0;
        for (int i = 0; i < tc.length; i++) {
            if (sc[j] == tc[i]) {
                j++;
                if (j == s.length()) return true;
            }
        }
        return false;
    }
    public int numDistinct(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int slen = sc.length;
        int tlen = tc.length;
        int[][] dp = new int[slen + 1][tlen + 1];
        for (int i = 0; i < slen; i++) {
            dp[i][0] = 1;
            for (int j = 0; j < tlen; j++) {
                if (sc[i] == tc[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
                }else dp[i + 1][j + 1] = dp[i][j + 1];
            }
        }
        return dp[slen][tlen];
    }

    public int minDistance(String word1, String word2) {
        char[] sc = word1.toCharArray();
        char[] tc = word2.toCharArray();
        int slen = sc.length;
        int tlen = tc.length;
        int[][] dp = new int[slen + 1][tlen + 1];
        for (int i = 1; i <= slen; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= tlen; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < slen; i++) {
            for (int j = 0; j < tlen; j++) {
                if (sc[i] == tc[j]) {
                    dp[i + 1][j + 1] = dp[i][j];
                }else {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1], dp[i + 1][j]) + 1;
                }
            }
        }
        return dp[slen][tlen];
    }

    public int minDistance2(String word1, String word2) {
        char[] sc = word1.toCharArray();
        char[] tc = word2.toCharArray();
        int slen = sc.length;
        int tlen = tc.length;
        int[][] dp = new int[slen + 1][tlen + 1];
        for (int i = 1; i <= slen; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= tlen; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < slen; i++) {
            for (int j = 0; j < tlen; j++) {
                if (sc[i] == tc[j]) {
                    dp[i + 1][j + 1] = dp[i][j];
                }else {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j])) + 1;
                }
            }
        }
        return dp[slen][tlen];
    }
    public int countSubstrings1(String s) {
        char[] c = s.toCharArray();
        int result = 0;
        boolean[][] dp = new boolean[c.length + 1][c.length + 1];
        for (int i = c.length - 1; i >= 0; i--) {
            for (int j = i; j < c.length; j++) {
                if (c[i] == c[j]) {
                    if (j - i <= 1 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        result++;
                    }
                }
            }
        }
        return result;
    }
    public int countSubstrings2(String s) {
        int result = 0;
        char[] c = s.toCharArray();
        int len = c.length;
        for (int i = 0; i < len; i++) {
            int left = i;
            int right = i + 1;
            while (left >=0 && right < len && c[left--] == c[right++]) result++;
            right = left = i;
            while (left >=0 && right < len && c[left--] == c[right++]) result++;
        }
        return result;
    }

    public static void main(String[] args) {
        dp d = new dp();
        d.minDistance("a", "b");
    }
}

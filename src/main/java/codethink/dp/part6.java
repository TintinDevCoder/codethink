package codethink.dp;

public class part6 {
    public boolean isSubsequence1(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        if (slen > tlen) return false;
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        int[][] dp = new int[slen + 1][tlen + 1];
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= tlen; j++) {
                if (sCharArray[i - 1] == tCharArray[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = dp[i][j - 1];
            }
        }
        if (dp[slen][tlen] != slen) return false;
        return true;
    }
    public boolean isSubsequence2(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        if (slen <= 0) return true;
        if (slen > tlen) return false;
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        int j = 0;
        for (int i = 0; i < tlen; i++) {
            if (tCharArray[i] == sCharArray[j]) j++;
            if (j == s.length()) return true;
            if (slen - j > tlen - i) return false;
        }
        return false;
    }
    public int numDistinct(String s, String t) {
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        int slen = s.length();
        int tlen = t.length();
        int[][] dp = new int[slen + 1][tlen + 1];
        for (int i = 0; i <= slen; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= tlen; j++) {
                if (sCharArray[i - 1] == tCharArray[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[slen][tlen];
    }
    public int minDistance(String word1, String word2) {
        char[] charArray1 = word1.toCharArray();
        char[] charArray2 = word2.toCharArray();
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (charArray1[i - 1] == charArray2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
    //72.编辑距离
    public int minDistance2(String word1, String word2) {
        char[] charArray1 = word1.toCharArray();
        char[] charArray2 = word2.toCharArray();
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (charArray1[i - 1] == charArray2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[len1][len2];
    }
    public int countSubstrings1(String s) {
        char[] c = s.toCharArray();
        int len = s.length();
        int result = 0;
        boolean[][] dp = new boolean[len + 1][len + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
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
        char[] c = s.toCharArray();
        int len = s.length();
        int result = 0;
        for (int i = 0; i < c.length; i++) {
            int left = i;

            int right = i + 1;
            while (left >= 0 && right < len && left <= right && c[left--] == c[right++]) result++;
            left = i;
            right = i;
            while (left >= 0 && right < len && left <= right && c[left--] == c[right++]) result++;
        }
        return result;
    }
    public int longestPalindromeSubseq(String s) {
        char[] c = s.toCharArray();
        int len = c.length;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (c[j] == c[i]) {
                    if (j - i <= 2) dp[i][j] = j - i + 1;
                    else dp[i][j] = dp[i + 1][j - 1] + 2;
                }else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][len - 1];
    }

}

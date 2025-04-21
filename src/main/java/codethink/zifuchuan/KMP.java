package codethink.zifuchuan;

public class KMP {
    public int[] getnextnums(char[] s) {
        int[] next = new int[s.length];
        next[0] = -1;
        int j = -1;
        for (int i = 1; i < s.length; i++) {
            while (j > -1 && s[j + 1] != s[i]) {
                j = next[j];
            }
            if (s[j + 1] == s[i]) j++;
            next[i] = j;
        }
        return next;
    }
    /*28. 找出字符串中第一个匹配项的下标https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/*/
    public int strStr(String haystack, String needle) {
        char[] c1 = haystack.toCharArray();
        char[] c2 = needle.toCharArray();
        int[] next = getnextnums(c2);
        int j = -1;
        for (int i = 0; i < c1.length; i++) {
            while (j >= 0 && c2[j + 1] != c1[i]) {
                j = next[j];
            }
            if (c2[j + 1] == c1[i]) {
                j++;
            }
            if (j == c2.length - 1) {
                return i - c2.length + 1;
            }
        }
        return -1;
    }
    /*459.重复的子字符串https://leetcode.cn/problems/repeated-substring-pattern/description/*/
    public boolean repeatedSubstringPattern(String s) {

        return true;
    }
    public static void main(String[] args) {
        KMP k = new KMP();
        k.strStr("mississippi", "issip");

    }
}

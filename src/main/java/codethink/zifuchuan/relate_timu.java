package codethink.zifuchuan;

import codethink.lianbiao.ListNode;

public class relate_timu {
    /*28. 找出字符串中第一个匹配项的下标https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/*/
    public int strStr(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        for (int i = 0; i <= len1 - len2; i++) {
            String sub = haystack.substring(i, i + len2);
            if (sub.equals(needle)) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        relate_timu rel = new relate_timu();
        rel.strStr("abc", "c");
    }

}

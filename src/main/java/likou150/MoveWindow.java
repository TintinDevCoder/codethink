package likou150;

import java.util.*;

public class MoveWindow {
    /**
     * 209. 长度最小的子数组
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int result = Integer.MAX_VALUE;
        int now = 0;
        for (int end = 0; end < nums.length; end++) {
            now += nums[end];
            if (now >= target) {
                while (start < end && now - nums[start] >= target) {
                    now -= nums[start++];
                }
                result = Math.min(result, end - start + 1);
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 3. 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        int[] target = new int[260];
        Arrays.fill(target, -1);
        int result = 0;
        int left = 0;
        for (int i = 0; i < c.length; i++) {
            left = Math.max(left, target[c[i]] + 1);
            result = Math.max(result, i - left + 1);
            target[c[i]] = i;
        }
        return result;
    }

    /**
     * 30. 串联所有单词的子串
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int len1 = words.length;
        int len2 = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        for (int i = 0; i <= s.length() - len2 * len1; i++) {
            Map<String, Integer> copiedMap = new HashMap<>();
            copiedMap.putAll(map);
            int k = i;
            int num = 0;
            while (k < i + len1 * len2) {
                String substring = s.substring(k, k + len2);
                if (copiedMap.containsKey(substring)) {
                    num++;
                    Integer l = copiedMap.get(substring);
                    if (l == 1) copiedMap.remove(substring);
                    else copiedMap.put(substring, l - 1);
                } else break;
                k += len2;
            }
            if (num == len1) {
                result.add(i);
            }
        }
        return result;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int len1 = words.length;
        int len2 = words[0].length();
        char[] c = s.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        for (int i = 0; i < len2; i++) {
            if (i + len1 * len2 > s.length()) break;
            Map<String, Integer> copiedMap = new HashMap<>();
            copiedMap.putAll(map);
            for (int j = 0; j < len1; j++) {
                String w = s.substring(i + j * len2, i + (j + 1) * len2);
                copiedMap.put(w, copiedMap.getOrDefault(w, 0) - 1);
                if (copiedMap.get(w) == 0) copiedMap.remove(w);
            }
            for (int start = i; start < s.length() - len1 * len2 + 1; start += len2) {
                if (start != i) {
                    String w1 = s.substring(start + (len1 - 1) * len2, start + len1 * len2);
                    String w2 = s.substring(start - len2, start);
                    copiedMap.put(w1, copiedMap.getOrDefault(w1, 0) - 1);
                    if (copiedMap.get(w1) == 0) copiedMap.remove(w1);
                    copiedMap.put(w2, copiedMap.getOrDefault(w2, 0) + 1);
                    if (copiedMap.get(w2) == 0) copiedMap.remove(w2);
                }
                if (copiedMap.isEmpty()) {
                    result.add(start);
                }
            }
        }
        return result;
    }

    /**
     * 76. 最小覆盖子串
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[] target = new int[128];
        int num = 0;
        for (char c : tc) {
            target[c]++;
            num++;
        }
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minl = 0, minr = 0;
        int right = 0;
        while (right < sc.length) {
            if (target[tc[right]]-- > 0) num--;
            while (left < right && num == 0) {
                if (++target[tc[left++]] > 0) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        minr = right;
                        minl = left;
                    }
                    num++;
                }
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minl, minr + 1);
    }
}

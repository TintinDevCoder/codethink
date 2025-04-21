package codethink.haxibiao;

import java.util.*;

public class zimuyiweici {
    /*242.有效的字母异位词https://leetcode.cn/problems/valid-anagram/*/
    public boolean isAnagram(String s, String t) {
        int[] hs = new int[123];
        int n = s.length();
        for (char c : s.toCharArray()) {
            hs[c]++;
        }
        for (char c : t.toCharArray()) {
            if (hs[c] > 0) {
                hs[c]--;
                n--;
            }else return false;
        }
        return n == 0 ? true : false;
    }
    /*49. 字母异位词分组https://leetcode.cn/problems/group-anagrams/description/*/
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);
            List<String> list = map.getOrDefault(sorted, new ArrayList<>());
            list.add(str);
            map.put(sorted, list);
        }
        return new ArrayList<>(map.values());
    }
    /* 383. 赎金信https://leetcode.cn/problems/ransom-note/description/*/
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] hs = new int[123];
        for (char c : magazine.toCharArray()) {
            hs[c]++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (hs[c] > 0) {
                hs[c]--;
            }else return false;
        }
        return true;
    }
    /*438. 找到字符串中所有字母异位词https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/*/
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] snum = new int[26];
        int[] pnum = new int[26];
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int plen = p.length();
        int slen = s.length();
        if (plen > slen) return result;
        for (int i = 0; i < plen; i++) {
            pnum[pc[i] - 'a']++;
            snum[sc[i] - 'a']++;
        }
        if (Anagramscheck(snum, pnum)) result.add(0);
        for (int i = plen; i < slen; i++) {
            snum[sc[i - plen] - 'a']--;
            snum[sc[i] - 'a']++;
            if (Anagramscheck(snum, pnum)) result.add(i - plen + 1);
        }
        return result;
    }
    boolean Anagramscheck(int[] snum, int[] pnum) {
        for (int i = 0; i < 26; i++) {
            if (snum[i] != pnum[i]) return false;
        }
        return true;
    }
    /*349. 两个数组的交集https://leetcode.cn/problems/intersection-of-two-arrays/*/
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] target = new int[1001];
        for (int i : nums1) {
            target[i] = 1;
        }
        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (target[i]-- == 1) {
                list.add(i);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
    /*350. 两个数组的交集 IIhttps://leetcode.cn/problems/intersection-of-two-arrays-ii/description/*/
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] target = new int[1001];
        for (int i : nums1) {
            target[i]++;
        }
        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (target[i]-- == 1) {
                list.add(i);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}

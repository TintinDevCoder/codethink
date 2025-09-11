package likou150;

import java.util.*;

public class HashTable {
    /**
     * 383. 赎金信
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] magazineCharArray = magazine.toCharArray();
        char[] ransomNoteCharArray = ransomNote.toCharArray();
        int[] targets = new int[128];
        for (char c : magazineCharArray) {
            targets[c]++;
        }
        for (char c : ransomNoteCharArray) {
            if (targets[c] <= 0) {
                return false;
            }
            targets[c]--;
        }
        return true;
    }

    /**
     * 205. 同构字符串
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[] hash1 = new int[128];
        int[] hash2 = new int[128];
        for (int i = 0; i < sc.length; i++) {
            if (hash1[sc[i]] != 0 && hash1[sc[i]] != tc[i]) {
                return false;
            }
            if (hash2[tc[i]] != 0 && hash2[tc[i]] != sc[i]) {
                return false;
            }
            hash1[sc[i]] = tc[i];
            hash2[tc[i]] = sc[i];
        }
        return true;
    }

    /**
     * 290. 单词规律
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] hash = new String[26];
        char[] patternCharArray = pattern.toCharArray();
        String[] strings = s.split(" ");
        Map<String, Integer> map = new HashMap<>();
        if (strings.length != patternCharArray.length) return false;
        for (int i = 0; i < patternCharArray.length; i++) {
            int index = patternCharArray[i] - 'a';
            Integer k = map.get(strings[i]);
            if (hash[index] != null || k != null) {
                if(hash[index] == null || k == null || !hash[index].equals(strings[i]) || k != index) return false;
            }else {
                hash[index] = strings[i];
                map.put(strings[i], index);
            }
        }
        return true;
    }

    /**
     * 242. 有效的字母异位词
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sCharArray = s.toCharArray();
        char[] cCharArray = t.toCharArray();
        int[] target = new int[26];
        for (char c : cCharArray) {
            target[c - 'a']++;
        }
        for (char c : sCharArray) {
            int idx = c - 'a';
            if (target[idx] > 0) {
                target[idx]--;
            }else return false;
        }
        return true;
    }

    /**
     * 49. 字母异位词分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String temp = new String(charArray);
            List<String> orDefault = map.getOrDefault(temp, new ArrayList<>());
            orDefault.add(str);
            map.put(temp, orDefault);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 1.两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null)
                return new int[]{i, map.get(target - nums[i])};
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 202.快乐数
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n) && n != 1) {
            set.add(n);
            n = toHappy(n);
        }
        return n == 1;
    }
    public int toHappy(int n) {
        int i = n;
        int sum = 0;
        while (i != 0) {
            int j = i % 10;
            i /= 10;
            sum += j * j;
        }
        return sum;
    }

    /**
     * 219.存在重复元素Ⅱ
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= k && i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        for (int i = k + 1; i < nums.length; i++) {
            set.remove(nums[i - k - 1]);
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }

    /**
     * 128.最长连续序列
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int result = 1;
        int now = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {
                now++;
            }else if (nums[i] != nums[i + 1]) {
                now = 1;
                continue;
            }
            if (result < now) result = now;
        }
        return result;
    }
    public static void main(String[] args) {
        HashTable h = new HashTable();
        h.longestConsecutive(new int[]{1,2,3,1,2,3});
    }
}

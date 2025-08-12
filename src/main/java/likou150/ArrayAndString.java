package likou150;

import java.util.*;

public class ArrayAndString {
    //88. 合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        while (m > 0 || n > 0) {
            if (m <= 0 || (n > 0 && nums1[m - 1] < nums2[n - 1])) {
                nums1[i--] = nums2[--n];
            }else {
                nums1[i--] = nums1[--m];
            }
        }
    }
    //26. 删除有序数组中的重复项
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int num : nums) {
            if (num != val)
                nums[j++] = num;
        }
        return j;
    }
    //26. 删除有序数组中的重复项
    public int removeDuplicates1(int[] nums) {
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
    //80. 删除有序数组中的重复项 II
    public int removeDuplicates2(int[] nums) {
        int j = 1;
        int n = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j++] = nums[i];
                n = 1;
            }else if (n >= 2){
                continue;
            }else {
                nums[j++] = nums[i];
                n++;
            }
        }
        return j;
    }
    //189. 轮转数组
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverseRotate(nums, 0, nums.length - 1);
        reverseRotate(nums, 0, k - 1);
        reverseRotate(nums, k, nums.length - 1);
    }
    public void reverseRotate(int[] nums, int left, int right) {
        while (left >=0 && right < nums.length && left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
    //55. 跳跃游戏
    public boolean canJump(int[] nums) {
        int jumpStep = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (jumpStep < i) return false;
            jumpStep = Math.max(jumpStep, i + nums[i]);
        }
        return true;
    }
    //45. 跳跃游戏 II
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int step = 0;
        int cover = 0;//记录每次跳跃能跳的最大位置
        int nmax = -1;
        for (int i = 0; i <= cover; i++) {
            nmax = Math.max(nmax, i + nums[i]);
            //到了最大能跳跃的位置，需要再跳跃
            if (i == cover) {
                cover = nmax;
                step++;
            }
            if (cover >= nums.length - 1) return step; //跳跃到了最后位置
        }
        return step;
    }
    //169. 多数元素
    public int majorityElement(int[] nums) {
        int num = 1;
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (result == nums[i]) {
                num++;
            }else num--;
            if (num == 0) {
                result = nums[i];
                num = 1;
            }
        }
        return result;
    }
    //121. 买卖股票的最佳时机
    public int maxProfit1(int[] prices) {
        int maxx = prices[prices.length - 1];
        int result = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            if (maxx < prices[i]) maxx = prices[i];
            result = Math.max(result, maxx - prices[i]);
        }
        return result;
    }
    //122. 买卖股票的最佳时机 II
    public int maxProfit2(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                result += (prices[i] - prices[i - 1]);
            }
        }
        return result;
    }
    //274. H 指数
    public int hIndex(int[] citations) {
        int len = citations.length;
        int result = 0;
        Arrays.sort(citations);
        for (int i = len - 1; i >= 0; i--) {
            int h = len - i;
            h = Math.min(h, citations[i]);
            if (result > h) break;
            result = h;
        }
        return result;
    }
    //238. 除自身以外数组的乘积
    public int[] productExceptSelf1(int[] nums) {
        int size = nums.length;
        int[] left = new int[size + 1];
        int[] right = new int[size + 1];
        left[0] = right[size - 1] = 1;
        for (int i = 0; i < size; i++) {
            left[i + 1] = left[i] * nums[i];
        }
        for (int i = size - 1; i > 0; i--) {
            right[i - 1] = right[i] * nums[i];
        }
        for (int i = 0; i < size; i++) {
            nums[i] = left[i] * right[i];
        }
        return nums;
    }
    public int[] productExceptSelf2(int[] nums) {
        int size = nums.length;
        int left = 0, right = size - 1;
        int lp = 1, rp = 1;
        int[] result = new int[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }
        while (left < size && right >= 0) {
            result[left] *= lp;
            result[right] *= rp;
            lp *= nums[left++];
            rp *= nums[right--];
        }
        return result;
    }
    //134. 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int total = 0;
        int cur = 0;
        int start = 0;
        for (int i = 0; i < len; i++) {
            cur += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (cur < 0) {
                start = i + 1;
                cur = 0;
            }
        }
        return total < 0 ? -1 : start;
    }
    //135. 分发糖果
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] result = new int[len + 1];
        result[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                result[i] = result[i - 1] + 1;
            }else result[i] = 1;
        }
        int res = result[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                result[i] = Math.max(result[i], result[i + 1] + 1);
            }
            res += result[i];
        }
        return res;
    }
    //42. 接雨水
    public int trap(int[] height) {
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }
        int result = 0;
        for (int i = 1; i < len - 1; i++) {
            int a = Math.min(left[i], right[i]) - height[i];
            result += a < 0 ? 0 : a;
        }
        return result;
    }
    //13. 罗马数字转整数
    public int romanToInt(String s) {
        char[] cs = s.toCharArray();
        int[] map = new int[90];
        map['I'] = 1;map['V'] = 5;map['X'] = 10;map['L'] = 50;map['C'] = 100;map['D'] = 500;map['M'] = 1000;
        int result = map[cs[0]];
        for (int i = 1; i < cs.length; i++) {
            result += map[cs[i]];
            if ((cs[i] == 'V' || cs[i] == 'X') && cs[i - 1] == 'I') result -= 2;
            else if ((cs[i] == 'L' || cs[i] == 'C') && cs[i - 1] == 'X') result -= 20;
            else if ((cs[i] == 'D' || cs[i] == 'M') && cs[i - 1] == 'C') result -= 200;
        }
        return result;
    }
    //12. 整数转罗马数字
    public String intToRoman1(int num) {
        StringBuilder s = new StringBuilder();
        int i = 1000;
        int j = 4;
        while (num != 0) {
            if (j > 0) {
                int n1 = num / i;
                s.append(switchTo(n1, j));
                num %= i;
            }
            i /= 10;
            j--;
        }
        return s.toString();
    }
    public String switchTo(int num, int step) {
        StringBuilder s = new StringBuilder();
        if (step == 4) {
            while (num-- != 0) s.append('M');
        }else if (step == 3) {
            if (num == 4) s.append("CD");
            else if (num == 9) s.append("CM");
            else {
                if (num >= 5) {
                    s.append('D');
                    num -= 5;
                }
                while (num >= 1) {
                    s.append('C');
                    num--;
                }
            }
        }else if (step == 2) {
            if (num == 4) s.append("XL");
            else if (num == 9) s.append("XC");
            else {
                if (num >= 5) {
                    s.append('L');
                    num -= 5;
                }
                while (num >= 1) {
                    s.append('X');
                    num--;
                }
            }
        }else {
            if (num == 4) s.append("IV");
            else if (num == 9) s.append("IX");
            else {
                if (num >= 5) {
                    s.append('V');
                    num -= 5;
                }
                while (num >= 1) {
                    s.append('I');
                    num--;
                }
            }
        }
        return s.toString();
    }
    public String intToRoman2(int num) {
        int digital[] = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
        String str[] = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        StringBuilder result = new StringBuilder();
        int index = digital.length - 1;
        while (num > 0) {
            while (digital[index] > num) {
                index--;
            }
            result.append(str[index]);
            num -= digital[index];
        }
        return result.toString();
    }
    //58. 最后一个单词的长度
    public int lengthOfLastWord(String s) {
        int len = s.length();
        char[] c = s.toCharArray();
        int i = len - 1;
        while (i >= 0 && c[i] == ' ') i--;
        int result = 0;
        while (i >= 0 && c[i] != ' ') {
            result++;
            i--;
        }
        return result;
    }
    //14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        int end = 0;
        int minsize = strs[0].length();
        for (String str : strs) {
            minsize = Math.min(str.length(), minsize);
        }
        boolean is = true;
        while (is) {
            if (end > minsize - 1) {
                break;
            }
            char sc = strs[0].charAt(end);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(end) != sc) {
                    end--;
                    is = false;
                    break;
                }
            }
            end++;
        }
        return end == 0 ? "" : strs[0].substring(0, end);
    }
    //151. 反转字符串中的单词
    public String reverseWords1(String s) {
        String[] chars = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (String aChar : chars) {
            if (!aChar.equals("")) {
                result.append(reverseString(aChar));
                result.append(' ');
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result.reverse().toString();
    }
    public char[] reverseString(String s) {
        char[] c = s.toCharArray();
        int start = 0;
        int end = c.length - 1;
        while (start < end) {
            char temp = c[start];
            c[start++] = c[end];
            c[end--] = temp;
        }
        return c;
    }
    public String reverseWords2(String s) {
        int len = s.length();
        char[] newString = new char[len + 1];
        char[] oldString = s.toCharArray();
        int newIndex = 0;
        int i = oldString.length - 1;
        while (i >= 0) {
            while (i >= 0 && oldString[i] == ' ') i--;
            int end = i;
            if (end < 0) break;
            while (i >= 0 && oldString[i] != ' ') i--;
            for (int j = i + 1; j <= end; j++) {
                newString[newIndex++] = oldString[j];
            }
            newString[newIndex++] = ' ';
        }
        return new String(newString, 0, newIndex - 1);
    }
    //6. Z 字形变换
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int len = s.length();
        char[] c = s.toCharArray();
        int tar = numRows + numRows - 2;
        int span = tar;
        StringBuilder sb = new StringBuilder();

        int start = 0;
        while (start < len) {
            sb.append(c[start]);
            start += span;
        }
        int now = tar - 2;
        for (int i = 1; i < numRows - 1; i++) {
            span = now;
            int j = i;
            while (j < len) {
                sb.append(c[j]);
                j += span;
                span = tar - span;
            }
            now -= 2;
        }

        span = numRows + numRows - 2;
        int end = numRows - 1;
        while (end < len) {
            sb.append(c[end]);
            end += span;
        }
        return sb.toString();
    }
    //28. 找出字符串中第一个匹配项的下标
    public int strStr(String haystack, String needle) {
        char[] chars1 = haystack.toCharArray();
        char[] chars2 = needle.toCharArray();
        //构造next数组
        int len = needle.length();
        int[] next = new int[len + 1];
        int j = -1;
        next[0] = j;
        for (int i = 1; i < len; i++) {
            //计算以i为结尾的最长匹配后缀缀和以j结尾的后缀的最长相等长度
            while (j >= 0 && chars2[i] != chars2[j + 1]) {
                j = next[j]; //j + 1位置不匹配找到上一个j匹配的位置,回退
            }
            if (chars2[i] == chars2[j + 1]) {// 找到相同的前后缀
                j++; //匹配下一个位置
            }
            next[i] = j;
        }
        j = -1;
        for (int i = 0; i < chars1.length; i++) {
            while (j >= 0 && chars1[i] != chars2[j + 1]) { //不匹配
                j = next[j]; // j 寻找之前匹配的位置
            }
            if (chars1[i] == chars2[j + 1]) { // 匹配，j和i同时向后移动
                j++;
            }
            if (j == len - 1) {
                return i - len + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        ArrayAndString s = new ArrayAndString();
        String theSkyIsBlue = s.convert("A", 1);
        System.out.println(theSkyIsBlue);
    }

    //380. O(1) 时间插入、删除和获取随机元素
    class RandomizedSet {
        private List<Integer> arr;
        private Random random;
        private Map<Integer, Integer> map;
        public RandomizedSet() {
            arr = new ArrayList<>();
            random = new Random();
            map = new HashMap<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            map.put(val, arr.size());
            arr.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            //删除元素，用尾部元素代替
            int lastElement = arr.get(arr.size() - 1);
            int index = map.get(val);
            arr.set(index, lastElement);
            map.put(lastElement, index);

            arr.remove(arr.size() - 1);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            // 获取一个随机整数
            int randomInt = random.nextInt(arr.size());
            return arr.get(randomInt);
        }

    }
}

package codethink.shuzu;

public class yichuyuansu {
    /*26. 删除有序数组中的重复项https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/*/
    public int removeDuplicates(int[] nums) {
        int same = nums[0];
        int index = 1;
        for (int num : nums) {
            if (num != same) {
                same = num;
                nums[index++] = num;
            }
        }
        return index;
    }
    /*27. 移除元素https://leetcode.cn/problems/remove-element/description/*/
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int num : nums) {
            if (num != val) {
                nums[index++] = num;
            }
        }
        return index;
    }
    /*283. 移动零https://leetcode.cn/problems/move-zeroes/description/*/
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }
        for (int i = index; i < nums.length; i++)
            nums[i] = 0;
    }
    /*844. 比较含退格的字符串https://leetcode.cn/problems/backspace-string-compare/description/*/
    public boolean backspaceCompare(String s, String t) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '#') {
//                if (!sb1.isEmpty()) sb1.deleteCharAt(sb1.length() - 1);
//            }else sb1.append(s.charAt(i));
//        }
//        for (int i = 0; i < t.length(); i++) {
//            if (t.charAt(i) == '#') {
//                if (!sb2.isEmpty()) sb2.deleteCharAt(sb2.length() - 1);
//            }else sb2.append(t.charAt(i));
//        }
        return sb1.toString().equals(sb2.toString());
    }
    /*977. 有序数组的平方https://leetcode.cn/problems/squares-of-a-sorted-array/description/*/
    public int[] sortedSquares(int[] nums) {
        int index = 0;
        int[] newnums = new int[nums.length];
        while (index < nums.length && nums[index] < 0) index++;
        int left = index - 1, right = index;
        index = 0;
        while (right < nums.length || left >= 0) {
            if (right >= nums.length || (left >= 0 && -nums[left] < nums[right])) {
                newnums[index++] = nums[left] * nums[left];
                left--;
            }else{
                newnums[index++] = nums[right] * nums[right];
                right++;
            }
        }
        //(right > nums.length && -nums[right] > nums[right])
        return newnums;
    }

    public static void main(String[] args) {
        yichuyuansu s = new yichuyuansu();

    }
}

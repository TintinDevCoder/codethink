package codethink.shuangzhizhen;

public class yichuyuansu {
    /*26. 删除有序数组中的重复项https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/*/
    public int removeDuplicates(int[] nums) {
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) nums[index++] = nums[i];
        }
        return index;
    }

    /*27. 移除元素https://leetcode.cn/problems/remove-element/description/*/
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) nums[index++] = nums[i];
        }
        return index;
    }

    /*283. 移动零https://leetcode.cn/problems/move-zeroes/description/*/
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[index++] = nums[i];
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
    /*844. 比较含退格的字符串https://leetcode.cn/problems/backspace-string-compare/*/
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skips = 0, skipt = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skips++;
                    i--;
                }else if (skips > 0) {
                    skips--;
                    i--;
                }else {
                    break;
                }
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipt++;
                    j--;
                }else if (skipt > 0) {
                    skipt--;
                    j--;
                }else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) return false;
            }else if (i >= 0 || j >= 0) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }

    /*977. 有序数组的平方https://leetcode.cn/problems/squares-of-a-sorted-array/description/*/
    public int[] sortedSquares(int[] nums) {
        int[] newnums = new int[nums.length];
        int end = nums.length - 1;
        int index1 = 0, index2 = nums.length - 1;
        while (index1 <= index2) {
            if (nums[index1] > 0 || Math.abs(nums[index2]) > Math.abs(nums[index1])) {
                newnums[end] = nums[index2] * nums[index2];
                index2--;
            }else {
                newnums[end] = nums[index1] * nums[index1];
                index1++;
            }
            end--;
        }
        return nums;
    }


    public static void main(String[] args) {
        yichuyuansu u = new yichuyuansu();
        u.backspaceCompare("ab#c", "ad#c");
    }
}

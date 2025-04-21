package codethink.shuzu;

public class erfenchazhao {
    /*704.二分查找https://leetcode.cn/problems/binary-search/*/
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
    /*34. 在排序数组中查找元素的第一个和最后一个位置https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/*/
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int index1 = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target && (mid - 1 < 0 || nums[mid - 1] != target)) {
                index1 = mid;
                break;
            }
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        if (index1 == -1) return new int[]{-1, -1};
        int index2 = nums.length - 1;
        int start = index1;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target && (mid + 1 > nums.length - 1 || nums[mid + 1] != target)) {
                index2 = mid;
                break;
            }else if (nums[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return new int[]{index1, index2};
    }
    /*35.搜索插入位置https://leetcode.cn/problems/search-insert-position/description/*/
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left + 1;
    }
    /*69. x 的平方根https://leetcode.cn/problems/sqrtx/description/ */
    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long n = (long)mid * mid;
            if (n == x) return mid;
            else if (n > x) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }
    /*367. 有效的完全平方数https://leetcode.cn/problems/valid-perfect-square/description/*/
    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long n = (long)mid * mid;
            if (n == num) return true;
            else if (n > num) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }

    public static void main(String[] args) {
        erfenchazhao s = new erfenchazhao();
        s.mySqrt(8);
    }
}

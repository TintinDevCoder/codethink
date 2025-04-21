package codethink.haxibiao;

import java.util.*;

public class duoshuzhihe {
    /*1. 两数之和https://leetcode.cn/problems/two-sum/description/*/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
    /*第454题.四数相加IIhttps://leetcode.cn/problems/4sum-ii/description/*/
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i1 : nums1) {
            for (int i2 : nums2) {
                map.put(i1 + i2, map.getOrDefault(i1 + i2, 0) + 1);
            }
        }
        int count = 0;
        for (int i3 : nums3) {
            for (int i4 : nums4) {
                count += map.getOrDefault(0 - (i3 + i4), 0);
            }
        }
        return count;
    }
    /*第15题. 三数之和https://leetcode.cn/problems/3sum/description/*/
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            HashSet<Integer> set = new HashSet();
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2])
                    continue;
                int c = -(nums[i] + nums[j]);
                if (set.contains(c)) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], c)));
                    set.remove(c);
                }
                else set.add(nums[j]);
            }
        }
        return result;
    }
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                }else if (sum < 0) {
                    left++;
                }else {
                    result.add(new ArrayList<>(Arrays.asList(nums[i] , nums[left] , nums[right])));
                    while (left < right && nums[left] == nums[left + 1])left++;
                    while (left < right && nums[right] == nums[right - 1])right--;
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
    /*第18题. 四数之和https://leetcode.cn/problems/4sum/description/*/
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (nums[i] > 0 && target < 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    }else if (sum < target) {
                        left++;
                    }else {
                        result.add(new ArrayList<>(Arrays.asList(nums[i] ,nums[j], nums[left] , nums[right])));
                        while (left < right && nums[left] == nums[left + 1])left++;
                        while (left < right && nums[right] == nums[right - 1])right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        duoshuzhihe d = new duoshuzhihe();
        d.fourSum(new int[]{0,0,0,0},0);
    }
}

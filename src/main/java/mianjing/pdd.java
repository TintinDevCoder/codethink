package mianjing;

import java.util.*;

public class pdd {
    /**
     * 480. 滑动窗口中位数
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int cnt = len - k + 1; // 滑窗个数
        double[] result = new double[cnt]; // 中位数
        PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // 大顶堆
        PriorityQueue<Integer> right = new PriorityQueue<>((a, b) -> Integer.compare(a, b)); // 小顶堆
        // 初始化顶堆
        for (int i = 0; i < k; i++) {
            right.add(nums[i]);
        }
        for (int i = 0; i < k / 2; i++) {
            left.add(right.poll());
        }
        // 首个中位数
        result[0] = getMid(left, right);
        for (int i = k; i < len; i++) {
            int a = nums[i]; // 加入滑动窗口
            int b = nums[i - k]; // 移除滑动窗口
            if (a >= right.peek()) right.add(a);
            else left.add(a);
            if (b >= right.peek()) right.remove(b);
            else left.remove(b);
            // 调整堆
            adjustPriorityQueue(left, right);
            result[i - k + 1] = getMid(left, right);
        }
        return result;
    }
    // 调整堆使得堆平衡
    public void adjustPriorityQueue(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        while (left.size() > right.size()) right.add(left.poll());  // 左边比右边多,左边必定不符合条件,往右边搬
        while (right.size() > left.size() + 1) left.add(right.poll());  // 右边比左边多1以上,右边必定多了,往左边搬
    }
    // 获取当前滑动窗口的中位数
    public Double getMid(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == right.size()) return left.peek() / 2.0 + right.peek() / 2.0;
        else return (double) right.peek();
    }

    /**
     * 31. 下一个排列
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 从右向左找出第一个递减的位置
        while (i >= 0) {
            if (nums[i] < nums[i + 1]) break;
            i--;
        }
        // 若存在递减的位置，则找到此元素右边第一个比他大的元素
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) j--;
            // 交换
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        // 将替换后的元素右边数组进行翻转，由递减变为递增
        int left = i + 1;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 93. 复原 IP 地址
     * @param s
     * @return
     */
    List<String> restoreIpAddressesRes = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        restoreIpAddressesDfs(s, "", -1, -1, -1);
        return restoreIpAddressesRes;
    }
    public void restoreIpAddressesDfs(String sc, String s, int positon1, int positon2, int positon3) {
        // 加完三个.
        if (positon3 != -1) {
            restoreIpAddressesRes.add(s.toString());
            return ;
        }
        // 加第三个.
        if (positon2 != -1) {
            if (sc.length() - positon2 - 1 > 6) return; // 剩下位置大于6个
            for (int i = positon2 + 1; i < Math.min(positon2 + 4, sc.length() - 1); i++) {
                if (sc.length() - i - 1 > 3) continue;
                if (checkIp(sc, positon2 + 1, i) && checkIp(sc, i + 1, sc.length() - 1)) {
                    restoreIpAddressesDfs(sc, s + sc.substring(positon2 + 1, i + 1) + "." + sc.substring(i + 1, sc.length()), positon1, positon2, i);
                }
            }
            return;
        }
        // 加第二个.
        if (positon1 != -1) {
            if (sc.length() - positon1 - 1 > 9) return; // 剩下位置大于9个
            for (int i = positon1 + 1; i < Math.min(positon1 + 4, sc.length() - 1); i++) {
                if (sc.length() - i - 1 > 6) continue;
                if (checkIp(sc, positon1 + 1, i)) {
                    restoreIpAddressesDfs(sc, s + sc.substring(positon1 + 1, i + 1) + ".", positon1, i, positon3);
                }
            }
            return;
        }
        // 加第一个.
        if (sc.length() > 12) return; // 剩下位置大于12个
        for (int i = 0; i < Math.min(sc.length() - 3, 4); i++) {
            if (sc.length() - i - 1 > 9) continue;
            if (checkIp(sc, 0, i)) {
                restoreIpAddressesDfs(sc, s + sc.substring(0, i + 1) + ".", i, positon2, positon3);
            }
        }
        return;
    }
    public boolean checkIp(String s, int start, int end) {
        if (s.charAt(start) == '0' && end - start != 0) return false;
        if (Integer.valueOf(s.substring(start, end + 1)) > 255) return false;
        return true;
    }
    public static void main(String[] args) {
        pdd pdd = new pdd();
        pdd.restoreIpAddresses("25525511135");
    }
}

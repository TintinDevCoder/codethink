package mianjing;

import java.util.Deque;
import java.util.LinkedList;

public class feishu {
    /**
     * 239. 滑动窗口最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.getLast() < nums[i])
                deque.removeLast();
            deque.add(nums[i]);
        }
        if (k >= len) return new int[]{deque.peek()};
        int[] result = new int[len -2];
        result[0] = deque.peek();
        for (int i = k; i < len; i++) {
            if (nums[i - k] == deque.peek()) deque.poll();
            while (!deque.isEmpty() && deque.getLast() < nums[i])
                deque.removeLast();
            deque.add(nums[i]);
            result[i - k + 1] = deque.peek();
        }
        return result;
    }
}

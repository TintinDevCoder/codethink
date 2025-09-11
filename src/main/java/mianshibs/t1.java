package mianshibs;

import codethink.lianbiao.ListNode;

import java.util.*;

public class t1 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[right] < 0) break;
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) right--;
                else if (sum < 0) left++;
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
    public int maxProfit1(int k, int[] prices) {
        int[] dp = new int[2 * k + 1];
        for (int i = 1; i <= 2 * k; i+=2) {
            dp[i] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= 2 * k; j += 2) {
                dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + prices[i]);
            }
        }
        return dp[2 * k];
    }
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[] dp = new int[3]; //0买入 1不持有冷冻期 2不持有不在冷冻期
        dp[0] = -prices[0];
        for (int i = 1; i < len; i++) {
            int temp = dp[1];
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
            dp[0] = Math.max(dp[0], dp[2] - prices[i]);
            dp[2] = Math.max(dp[2], temp);
        }
        return Math.max(dp[1], dp[2]);
    }
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int[] tar = new int[128];
        Arrays.fill(tar, -1);
        int result = 0;
        for (int left = 0, right = 0; right < chars.length; right++) {
            left = Math.max(left, tar[chars[right]] + 1);
            tar[chars[right]] = right;
            result = Math.max(result, right - left + 1);
        }
        return result;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        ListNode r = reverseList(slow);
        ListNode l = head;
        while (l != null && r != null) {
            if (l.val != r.val) return false;
            l = l.next;
            r = r.next;
        }
        return true;
    }
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode first = head, second = head.next;
        first.next = null;
        while (second != null) {
            ListNode temp = second.next;
            second.next = first;
            first = second;
            second = temp;
        }
        return first;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;
        int t1 = 1;
        ListNode H = null;
        ListNode first = head, end = head;
        ListNode pre = null;
        while (end != null) {
            if (t1 == k) {
                ListNode temp = end.next;
                ListNode node = reverseList(first, end);
                if (pre == null) {
                    H = node;
                }else {
                    pre.next = end;
                }
                pre = first;
                t1 = 0;
                first.next = temp;
                first = temp;
                end = temp;
            }else end = end.next;
            t1++;
        }
        return H;
    }
    public ListNode reverseList(ListNode start, ListNode end) {
        if (start == null) return null;
        ListNode first = start, second = start.next;
        first.next = null;
        while (second != null) {
            ListNode temp = second.next;
            second.next = first;
            first = second;
            second = temp;
            if (first == end) return first;
        }
        return first;
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        int[] result = new int[nums.length - k + 1];
        result[0] = deque.getFirst();
        for (int i = k; i < nums.length; i++) {
            if (deque.getFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            result[i - k + 1] = deque.getFirst();
        }
        return result;
    }

    public static void main(String[] args) {

    }
}

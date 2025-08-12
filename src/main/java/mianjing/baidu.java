package mianjing;

import codethink.erchashu.TreeNode;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class baidu {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public boolean isPalindrome1(ListNode head) {
        ListNode h = head;
        int size = 0;
        while (h != null) {
            size++;
            h = h.next;
        }
        int[] list = new int[size];
        h = head;
        int s = 0;
        while (h != null) {
            list[s++] = h.val;
            h = h.next;
        }
        for (int i = 0; i < size / 2; i++) {
            if (list[i] != list[size - i - 1]) return false;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        int num = 0;
        ListNode slow = head, fast = head;
        while (fast != null) {
            num++;
            fast = fast.next;
            slow = slow.next;
            if (fast != null) {
                num++;
                fast = fast.next;
            }
        }
        ListNode node2 = reverseList(slow);
        ListNode node1 = head;
        while (node1 != null && node2 != null) {
            if (node1.val != node2.val) return false;
            node1 = node1.next;
            node2 = node2.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head.next;
        slow.next = null;
        while (fast != null) {
            ListNode temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        return slow;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;
        // 遍历节点
        ListNode h = head;
        // k个一反转
        int n = 0;
        // k个翻转的开始位置
        ListNode first = head;
        // 前k个节点的尾部
        ListNode pre = null;
        // 记录是否是第一个翻转的k个节点
        boolean f = true;
        while (h != null) {
            n++;
            if (n == k) {
                ListNode next = h.next;
                // 翻转k个节点
                reverseList(first, h);
                // 前k个节点连接上
                if (pre != null) {
                    pre.next = h;
                }
                // 第一个k个节点翻转，记录头节点
                if (f) {
                    f = false;
                    head = h;
                }
                // 连接上下k个节点
                first.next = next;
                // 重置
                n = 0;
                h = first.next;
                pre = first;
                first = first.next;
            } else h = h.next;
        }
        return head;
    }

    /**
     * 节点first到end翻转
     * @param first
     * @param end
     * @return
     */
    public ListNode reverseList(ListNode first, ListNode end) {
        if (first == null) return null;
        ListNode slow = first, fast = first.next;
        slow.next = null;
        while (fast != null) {
            ListNode temp = fast.next;
            fast.next = slow;
            slow = fast;
            if (fast == end) return slow;
            fast = temp;
        }
        return slow;
    }

    /**
     * 1143. 最长公共子序列
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (chars1[i - 1] != chars2[j - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }else dp[i][j] = dp[i - 1][j - 1] + 1;
            }
        }
        return dp[len1][len2];
    }

    /**
     * 5. 最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        char[] c = s.toCharArray();
        int left = 0;
        int right = 0;
        int maxlen = 0;
        int len = c.length;
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < len; j++) {
                if (c[i] == c[j]) {
                    if (j - i <= 2) dp[i][j] = true;
                    else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }else dp[i][j] = false;
                if (dp[i][j] && j - i + 1 > maxlen) {
                    maxlen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }
    public String longestPalindrome2(String s) {
        char[] c = s.toCharArray();
        int left = 0, right = 0;
        int maxLen = 0;
        for (int i = 0; i < c.length; i++) {
            int n1 = getLongest(c, i, i);
            int n2 = getLongest(c, i, i + 1);
            int len = Math.max(n1, n2);
            if (maxLen < len) {
                maxLen = len;
                left = i - (maxLen - 1) / 2;
                right = i + maxLen / 2;
            }
        }
        return s.substring(left, right + 1);
    }
    public int getLongest(char[] c, int start, int end) {
        while (start >= 0 && end < c.length && c[start] == c[end]) {
            start--;
            end++;
        }
        return end - start - 1;
    }

    /**
     * 142. 环形链表 II
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        HashSet<ListNode> hash = new HashSet<>();
        while (head != null) {
            if (hash.contains(head)) return head;
            hash.add(head);
            head = head.next;
        }
        return null;
    }
    public ListNode detectCycle2(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) return null;
            fast = fast.next;
            if (slow == fast) {
                ListNode t = head;
                while (t != fast) {
                    t = t.next;
                    fast = fast.next;
                }
                return t;
            }
        }
        return null;
    }

    /**
     * 53. 最大子数组和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int n = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > n + nums[i]) {
                n = nums[i];
            }else n += nums[i];
            result = Math.max(result, n);
        }
        return result;
    }
    public static void main(String[] args) {
        baidu m = new baidu();
    }
}
class CustomHashMap<K, V> {
    private HashMap<K, V> map;
    private V allValue;

    public CustomHashMap() {
        this.map = new HashMap<>();
        this.allValue = null; // 初始值可以为null或其他默认值
    }

    // 设置所有键的值
    public void setAll(V value) {
        this.allValue = value;
    }



}
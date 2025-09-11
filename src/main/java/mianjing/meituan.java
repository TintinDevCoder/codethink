package mianjing;
import java.text.ParseException;
import java.util.*;

public class meituan {
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
    public ListNode reverseList(ListNode head) {
        ListNode pre = head, back = head.next;
        pre.next = null;
        ListNode temp;
        while (back != null) {
            temp = back.next;
            back.next = pre;
            pre = back;
            back = temp;
        }
        return pre;
    }
    public void reorderList(ListNode head) {
        ListNode slow = new ListNode(-1, head);
        ListNode fast = slow;
        int len = 0;
        while (fast != null) {
            len++;
            fast = fast.next;
            slow = slow.next;
            if (fast != null) {
                len++;
                fast = fast.next;
            }
        }
        if (len == 0) return;
        ListNode n2 = reverseList(slow);
        ListNode n1 = head;
        boolean n = true;
        len--;
        while (len-- != 0 && n1 != n2) {
            if (n) {
                ListNode temp = n1.next;
                n1.next = n2;
                n1 = temp;
            }else {
                ListNode temp = n2.next;
                n2.next = n1;
                n2 = temp;
            }
            n = !n;
        }
    }
    public int lengthOfLongestSubstring(String s) {
        int[] target = new int[128];
        char[] charArray = s.toCharArray();
        Arrays.fill(target, -1);
        int result = 0;
        int left = 0;
        for (int i = 0; i < charArray.length; i++) {
            left = Math.max(left, target[charArray[i]] + 1);
            result = Math.max(result, i - left);
            target[charArray[i]] = i;
        }
        return result;
    }

    /**
     * 165. 比较版本号
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            System.out.println("Input versions cannot be null");
            throw new IllegalArgumentException("Version strings cannot be null");
        }

        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int i = 0, j = 0;

        while (i < split1.length && j < split2.length) {
            String s1 = split1[i];
            String s2 = split2[j];
            int comparisonResult = compareInt(s1, s2);
            if (comparisonResult != 0) {
                return comparisonResult;
            }
            i++;
            j++;
        }

        // Process remaining parts of version1
        while (i < split1.length) {
            String s1 = split1[i];
            int i1 = Integer.parseInt(s1);
            if (i1 != 0) return 1;
            i++;
        }

        // Process remaining parts of version2
        while (j < split2.length) {
            String s2 = split2[j];
            int i2 = Integer.parseInt(s2);
            if (i2 != 0) return -1;
            j++;
        }

        return 0; // Versions are equal
    }

    private int compareInt(String s1, String s2) {
        try {
            int i1 = Integer.parseInt(s1);
            int i2 = Integer.parseInt(s2);
            if (i1 > i2) return 1;
            else if (i1 < i2) return -1;
            return 0;
        } catch (NumberFormatException e) {
            System.out.println("Invalid version segment: " + s1 + " or " + s2);
            throw new IllegalArgumentException("Invalid version segment");
        }
    }

    public void reorderList(ListNode list, int t1, int t2) {
        ListNode temp = list;
        while (temp != null && temp.val != t1) {
            temp = temp.next;
        }
        if (temp == null) return;
        ListNode left = temp;
        while (temp != null && temp.val != t2) {
            temp = temp.next;
        }
        if (temp == null) return;
        ListNode right = temp;
        reorderList(left, right);
    }
    public void reorderList(ListNode t1, ListNode t2) {
        ListNode slow = t1;
        ListNode fast = t1.next;
        while (fast != null) {
            ListNode temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
            if (slow == t2) break;
        }
    }

    /**
     * 64. 最小路径和
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int len1 = grid.length;
        int len2 = grid[0].length;
        int[] dp = new int[len2];
        dp[0] = grid[0][0];
        for (int i = 1; i < len2; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i = 1; i < len1; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < len2; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[len2 - 1];
    }
    public int rand5() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }
    public int rand7() {
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            sum += rand5();
        }
        return sum % 7 + 1;
    }

    /**
     * 56. 合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new LinkedList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > right) {
                list.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }else {
                right = Math.max(right, intervals[i][1]);
            }
        }
        int[][] result = new int[list.size() + 1][2];
        for (int i = 0; i < result.length - 1; i++) {
            int[] ints = list.get(i);
            result[i] = new int[]{ints[0], ints[1]};
        }
        result[result.length - 1] = new int[]{left, right};
        return result;
    }

    /**
     * 124. 二叉树中的最大路径和
     * @param root
     * @return
     */
    int maxPathSumRes = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumDfs(root);
        return maxPathSumRes;
    }
    public int maxPathSumDfs(TreeNode root) {
        if (root == null) return 0;
        int left = maxPathSumDfs(root.left);
        left = left < 0 ? 0 : left;
        int right = maxPathSumDfs(root.right);
        right = right < 0 ? 0 : right;
        if (maxPathSumRes < root.val + left + right) {
            maxPathSumRes = root.val + left + right;
        }
        int t = left < 0 && right < 0 ? 0 : Math.max(left, right);
        return root.val + t;
    }

    /**
     * 76. 最小覆盖子串
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int num = 0;
        int[] target = new int[128];
        for (char c : tc) {
            target[c]++;
            num++;
        }
        int left = 0, right = 0;
        int minStart = 0, minEnd = 0, minLen = Integer.MAX_VALUE;
        while (right < sc.length) {
            if (target[sc[right]]-- > 0) num--;
            while (left <= right && num == 0) {
                if (++target[sc[left++]] > 0) num++;
                if (minLen > right - left + 1) {
                    minLen = right - left + 1;
                    minStart = left - 1;
                    minEnd = right;
                }
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minEnd + 1);
    }
    public static void main(String[] args) throws ParseException {
        meituan m = new meituan();
//        ListNode input = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        m.reorderList(input, 3, 5);
        m.merge(new int[][]{
                {1,3},{2,6},{8,10},{15,18}
        });
    }
}

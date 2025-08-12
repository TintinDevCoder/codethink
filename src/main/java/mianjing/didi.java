package mianjing;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class didi {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) return root;
        if (root == null) return null;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        else if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        else return root;
    }
    // 手撕：10万个数里找最小的10个 小顶堆
    public static int[] findTopKSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int num : nums) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            }else if (maxHeap.peek() > num) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    /**
     * 43. 字符串相乘
     * @param num1
     * @param num2
     * @return
     */
    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        char[] n1 = num1.toCharArray();
        int n = num1.length(), m = num2.length();
        StringBuilder result = new StringBuilder("0");
        for (int i = m - 1; i >= 0; i--) {
            StringBuilder s = new StringBuilder();
            for (int j = m - 1; j > i; j--) {
                s.append(0);
            }
            int y = num2.charAt(i) - '0';
            int add = 0;
            for (int j = n - 1; j >= 0; j--) {
                int x = (n1[j] - '0') * y + add;
                s.append(x % 10);
                add = x / 10;
            }
            if (add != 0) {
                s.append(add);
            }
            result = addStringBuilder(result, s.reverse());
        }
        return result.toString();
    }
    public StringBuilder addStringBuilder(StringBuilder s1, StringBuilder s2) {
        int i = s1.length() - 1, j = s2.length() - 1, add = 0;
        StringBuilder result = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? s1.charAt(i) - '0' : 0;
            int y = j >= 0 ? s2.charAt(j) - '0' : 0;
            int n = x + y + add;
            add = n / 10;
            result.append(n % 10);
            i--;
            j--;
        }
        if (add != 0) result.append(add);
        return result.reverse();
    }
    public String multiply2(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)) return "0";
        int n = num1.length();
        int m = num2.length();
        int[] nums = new int[n + m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                nums[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        for (int i = n + m - 1; i >= 1; i--) {
            nums[i - 1] += nums[i] / 10;
            nums[i] = nums[i] % 10;
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n + m; i++) {
            if (i == 0 && nums[i] == 0) continue;
            s.append(nums[i]);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        didi d = new didi();
        d.multiply2("123", "456");
    }
}

package likou150;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    static final int NULL = Integer.MIN_VALUE;
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public static TreeNode initTreeNode(int[] initData) {
        TreeNode root = new TreeNode(initData[0]);
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int i = 1;
        while (i < initData.length) {
            TreeNode node = queue.poll();
            int left = initData[i++];
            if (i >= initData.length) break;
            int right = initData[i++];
            if (left == NULL) node.left = null;
            else {
                TreeNode le = new TreeNode(left);
                queue.add(le);
                node.left = le;
            }
            if (right == NULL) node.right = null;
            else {
                TreeNode re = new TreeNode(right);
                queue.add(re);
                node.right = re;
            }
        }
        return root;
    }
}

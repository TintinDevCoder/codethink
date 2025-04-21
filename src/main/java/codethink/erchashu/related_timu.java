package codethink.erchashu;


import java.util.ArrayList;
import java.util.List;

public class related_timu {
    //226. 翻转二叉树https://leetcode.cn/problems/invert-binary-tree/description/
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        if (root.left == null) {
            root.left = root.right;
            root.right = null;
        }else if (root.right == null) {
            root.right = root.left;
            root.left = null;
        }else {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    //101. 对称二叉树https://leetcode.cn/problems/symmetric-tree/description/
    public boolean isSymmetricDfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isSymmetricDfs(left.left, right.right) && isSymmetricDfs(right.left, left.right);
    }
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricDfs(root.left, root.right);
    }
    //222. 完全二叉树的节点个数https://leetcode.cn/problems/count-complete-tree-nodes/description/
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftnums = 0;
        int rightnums = 0;
        while (left != null) {
            leftnums++;
            left = left.left;
        }
        while (right != null) {
            rightnums++;
            right = right.right;
        }
        if (leftnums == rightnums) {
            return (2 << leftnums) - 1;
        }
        return countNodes(root.left) + countNodes(root.right);
    }
    //110. 平衡二叉树https://leetcode.cn/problems/balanced-binary-tree/description/
    boolean isBalancedResult = true;
    public boolean isBalanced(TreeNode root) {
        isBalancedDfs(root);
        return isBalancedResult;
    }
    public int isBalancedDfs(TreeNode root) {
        if (!isBalancedResult) return -1;
        if (root == null) return 0;
        int leftnums = isBalancedDfs(root.left);
        int rightnums = isBalancedDfs(root.right);
        if (Math.abs(leftnums - rightnums) > 1) {
            isBalancedResult = false;
            return -1;
        }
        return Math.max(leftnums, rightnums) + 1;
    }
    //257. 二叉树的所有路径https://leetcode.cn/problems/binary-tree-paths/description/
    List<String> binaryTreePathsResult = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return binaryTreePathsResult;
        StringBuilder s = new StringBuilder();
        s.append(root.val);
        binaryTreePaths(root, s);
        return binaryTreePathsResult;
    }
    public void binaryTreePaths(TreeNode root, StringBuilder sb) {
        if (root.left == null && root.right == null) {
            binaryTreePathsResult.add(sb.toString());
            return;
        }
        if (root.left != null) {
            sb.append("->");
            sb.append(root.left.val);
            binaryTreePaths(root.left, sb);
            sb.delete(sb.lastIndexOf("->") + 1, sb.length());
            sb.delete(sb.length() - 2, sb.length());
        }
        if (root.right != null) {
            sb.append("->");
            sb.append(root.right.val);
            binaryTreePaths(root.right, sb);
            sb.delete(sb.lastIndexOf("->") + 1, sb.length());
            sb.delete(sb.length() - 2, sb.length());
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, null, new TreeNode(5));
        root.right = new TreeNode(3);
        related_timu related_timu = new related_timu();
        related_timu.binaryTreePaths(root);
    }
}

package codethink.erchashu;

import java.util.*;

public class related_questions {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        if (root.left != null) invertTree(root.left);
        if (root.right != null) invertTree(root.right);
        return root;
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricDfs(root.left, root.right);
    }
    public boolean isSymmetricDfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return isSymmetricDfs(left.left, right.right) && isSymmetricDfs(left.right, right.left);
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    List<Integer> postorderResult;
    public List<Integer> postorderTraversal(TreeNode root) {
        postorderResult = new ArrayList<>();
        postorderTraversalDg(root);
        return postorderResult;
    }
    public void postorderTraversalDg(TreeNode root) {
        if (root == null) return;
        postorderTraversalDg(root.left);
        postorderTraversalDg(root.right);
        postorderResult.add(root.val);
    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null)return false;
        return isSubtreeDfs(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    public boolean isSubtreeDfs(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.val != subRoot.val) return false;
        List<Integer> t1 = postorderTraversal(root);
        List<Integer> t2 = postorderTraversal(subRoot);
        int n = 0;
        if (t1.size() != t2.size()) return false;
        for (int i = 0; i < t1.size(); i++) {
            if (t1.get(i) != t2.get(n)) {
                return false;
            }else n++;
        }
        return true;
    }
    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null)return false;
        return isSubTree(root, subRoot) || isSubtree2(root.left, subRoot) || isSubtree2(root.right, subRoot);
    }
    public boolean isSubTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        if (root.val != subRoot.val) return false;
        return isSubTree(root.left, subRoot.left) && isSubTree(root.right, subRoot.right);
    }
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftnum = 0;
        int rightnum = 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        while (left != null) {
            leftnum++;
            left = left.left;
        }
        while (right != null) {
            rightnum++;
            right = right.right;
        }
        if (leftnum == rightnum) {
            return (2 << leftnum) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    boolean isBalancedRes = true;
    public boolean isBalanced(TreeNode root) {
        isBalancedDfs(root);
        return isBalancedRes;
    }
    public int isBalancedDfs(TreeNode root) {
        if (root == null || !isBalancedRes) return 0;
        int leftDepth = isBalancedDfs(root.left);
        int rightDepth = isBalancedDfs(root.right);
        if (Math.abs(rightDepth - leftDepth) > 1) {
            isBalancedRes = false;
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
    List<String> binaryTreePathsRes = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        binaryTreePathsDfs(root, "");
        return binaryTreePathsRes;
    }
    public void binaryTreePathsDfs(TreeNode root, String s) {
        if (root == null) return;
        s += root.val;
        if (root.left == null && root.right == null) {
            binaryTreePathsRes.add(s);
            return;
        }
        if (root.left != null) {
            binaryTreePathsDfs(root.left, s + "->");
        }
        if (root.right != null) {
            binaryTreePathsDfs(root.right, s + "->");
        }
    }
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int result = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) result += root.left.val;
        result += sumOfLeftLeaves(root.left);
        result += sumOfLeftLeaves(root.right);
        return result;
    }
    List<Integer> findBottomLeftValueList = new ArrayList<>();
    public int findBottomLeftValue(TreeNode root) {
        findBottomLeftValueDfs(root, 0);
        return findBottomLeftValueList.get(findBottomLeftValueList.size() - 1);
    }
    public void findBottomLeftValueDfs(TreeNode root, int depth) {
        if (findBottomLeftValueList.size() == depth) {
            findBottomLeftValueList.add(root.val);
        }
        if (root.left != null) findBottomLeftValueDfs(root.left, depth + 1);
        if (root.right != null) findBottomLeftValueDfs(root.right, depth + 1);
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return hasPathSum(root, targetSum, 0);
    }
    public boolean hasPathSum(TreeNode root, int targetSum, int now) {
        if (root == null) return false;
        now += root.val;
        if (root.left == null && root.right == null) {
            if (now == targetSum) return true;
            return false;
        }
        boolean l = hasPathSum(root.left, targetSum, now);
        boolean r = hasPathSum(root.right, targetSum, now);
        return l || r;
    }
    //106.从中序与后序遍历序列构造二叉树
    Map<Integer, Integer> buildTreeMap;
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        buildTreeMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            buildTreeMap.put(inorder[i], i);
        }
        return buildTreeDg(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }
    //左闭右开
    public TreeNode buildTreeDg(int[] inorder, int inbegin, int inend, int[] postorder, int postbegin, int postend) {
        if (inbegin >= inend || postbegin >= postend) return null;
        int node = postorder[postend - 1];
        TreeNode root = new TreeNode(node);
        int index = buildTreeMap.get(node);
        int lenOfLeft = index - inbegin;
        root.left = buildTreeDg(inorder, inbegin, index, postorder, postbegin, postbegin + lenOfLeft);
        root.right = buildTreeDg(inorder, index + 1, inend, postorder, postbegin + lenOfLeft, postend - 1);
        return root;
    }
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Stack<TreeNode> stack = new Stack<>();
        int index = inorder.length - 1;
        stack.add(root);
        for (int i = inorder.length - 2; i >= 0; i--) {
            TreeNode node = stack.peek();
            if (inorder[index] != node.val) {
                node.right = new TreeNode(postorder[i]);
                stack.add(node.right);
            }else {
                while (!stack.isEmpty() && stack.peek().val == inorder[index]) {
                    node = stack.pop();
                    index--;
                }
                node.left = new TreeNode(postorder[i]);
                stack.add(node.left);
            }
        }
        return root;
    }
    //105. 从前序与中序遍历序列构造二叉树
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.add(root);
        int index = 0;
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = stack.peek();
            if (node.val != inorder[index]) {
                node.left = new TreeNode(preorder[i]);
                stack.add(node.left);
            }else {
                while (!stack.isEmpty() && stack.peek().val == inorder[index]) {
                    index++;
                    node = stack.pop();
                }
                node.right = new TreeNode(preorder[i]);
                stack.add(node.right);
            }
        }
        return root;
    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTreeDg(nums, 0, nums.length);
    }
    public TreeNode constructMaximumBinaryTreeDg(int[] nums, int left, int right) {
        if (left >= right) return null;
        int minn = nums[left];
        int position = left;
        for (int i = left + 1; i < right; i++) {
            if (minn < nums[i]) {
                minn = nums[i];
                position = i;
            }
        }
        TreeNode root = new TreeNode(minn);
        root.left = constructMaximumBinaryTreeDg(nums, left, position);
        root.right = constructMaximumBinaryTreeDg(nums, position + 1, right);
        return root;
    }
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null) {
            root1.val += root2.val;
            root1.left = mergeTrees(root1.left, root2.left);
            root1.right = mergeTrees(root1.right, root2.right);
            return root1;
        }
        if (root1 != null) return root1;
        if (root2 != null) return root2;
        return null;
    }
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        else if (root.val > val) return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }
    public boolean isValidBST1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if (pre != null && pre.val >= node.val) return false;
            if (node.right != null)
                root = node.right;
            pre = node;
        }
        return true;
    }
    public boolean isValidBST2(TreeNode root) {
        return isValidBSTDg(root, null, null);
    }
    public boolean isValidBSTDg(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        return isValidBSTDg(root.left, min, root) && isValidBSTDg(root.right, root, max);
    }
    int getMinimumDifferenceRes = Integer.MAX_VALUE;
    TreeNode getMinimumDifferencePre = null;
    public int getMinimumDifference(TreeNode root) {
        getMinimumDifferenceDfs(root);
        return getMinimumDifferenceRes;
    }
    public void getMinimumDifferenceDfs(TreeNode root) {
        if (root == null) return;
        getMinimumDifferenceDfs(root.left);
        if (getMinimumDifferencePre != null) getMinimumDifferenceRes = Math.min(root.val - getMinimumDifferencePre.val, getMinimumDifferenceRes);
        getMinimumDifferencePre = root;
        getMinimumDifferenceDfs(root.right);
    }
    public int[] findMode(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> publicNum = new ArrayList<>();
        int num = 0;
        TreeNode pre = null;
        int now = 0;
        publicNum.add(root.val);
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if (pre == null) now = 1;
            else if (pre.val == node.val) now++;
            else now = 1;
            if (now == num) {
                publicNum.add(node.val);
            }else if (now > num) {
                num = now;
                publicNum.clear();
                publicNum.add(node.val);
            }
            pre = node;
            if (node.right != null) root = node.right;
        }
        return publicNum.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        else if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        return root;
    }
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        insertIntoBSTDg(root, val);
        return root;
    }
    public void insertIntoBSTDg(TreeNode root, int val) {
        if (root.val > val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
                return;
            }else {
                insertIntoBST(root.left, val);
            }
        }else {
            if (root.right == null) {
                root.right = new TreeNode(val);
                return;
            }else {
                insertIntoBST(root.right, val);
            }
        }
    }
    TreeNode deleteNodePre = null;
    public TreeNode deleteNode1(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val == key) {
            TreeNode shift;
            if (root.left == null && root.right == null) shift = null;
            else if (root.left != null && root.right != null) {
                TreeNode node = root.left;
                while (node != null) {
                    node = node.right;
                }
                node.right = root.right;
                shift = root.left;
            }else if (root.left == null) shift = root.right;
            else shift = root.left;
            return shift;
        }
        deleteNodeDg(root, key);
        return root;
    }
    public void deleteNodeDg(TreeNode root, int key) {
        if (root.val == key) {
            TreeNode shift;
            if (root.left == null && root.right == null) shift = null;
            else if (root.left != null && root.right != null) {
                TreeNode node = root.left;
                while (node.right != null) {
                    node = node.right;
                }
                node.right = root.right;
                shift = root.left;
            }else if (root.left == null) shift = root.right;
            else shift = root.left;
            if (key > deleteNodePre.val) {
                deleteNodePre.right = shift;
            }else deleteNodePre.left = shift;
        }
        deleteNodePre = root;
        if (root.val > key && root.left != null) deleteNodeDg(root.left, key);
        if (root.val < key && root.right != null) deleteNodeDg(root.right, key);
    }
    public TreeNode deleteNode2(TreeNode root, int key) {
        TreeNode n = root;
        TreeNode pre = null;
        while (root != null) {
            if (root.val == key) {
                TreeNode shift;
                if (root.left == null && root.right == null) shift = null;
                else if (root.left != null && root.right != null) {
                    TreeNode node = root.left;
                    while (node.right != null) {
                        node = node.right;
                    }
                    node.right = root.right;
                    shift = root.left;
                }else if (root.left != null) shift = root.left;
                else shift = root.right;
                if (pre == null) return shift;
                else {
                    if (pre.val > key) pre.left = shift;
                    else pre.right = shift;
                }
            }
            pre = root;
            if (key > root.val) root = root.right;
            else root = root.left;
        }
        return n;
    }

    public static void main(String[] args) {
        related_questions r = new related_questions();
        r.findMode(TreeNode.initTreeNode(new int[]{1,0,1,0,0,1,1,0}));
    }

}

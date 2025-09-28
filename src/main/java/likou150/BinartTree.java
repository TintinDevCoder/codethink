package likou150;

import java.util.*;

public class BinartTree {
    /**
     * 104. 二叉树的最大深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 226. 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 101. 对称二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricDg(root.left, root.right);
    }
    public boolean isSymmetricDg(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (right == null || left == null || left.val != right.val) return false;
        return isSymmetricDg(left.left, right.right) && isSymmetricDg(left.right, right.left);
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    private Map<Integer, Integer> indexMap;
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        indexMap = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeDg(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }
    public TreeNode buildTreeDg(int[] preorder, int[] inorder, int prel, int prer, int inl, int inr) {
        if (prel > prer || inl > inr) return null;
        int po = preorder[prel];
        TreeNode mid = new TreeNode(po);
        int i = indexMap.get(po);
        int leftnum = i - inl;
        mid.left = buildTreeDg(preorder, inorder, prel + 1, prel + leftnum, inl, i - 1);
        mid.right = buildTreeDg(preorder, inorder, prel + leftnum + 1, prer, i + 1, inr);
        return mid;
    }
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        Stack<TreeNode> stack = new Stack<>();
        int index = 0;
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = stack.peek();
            if (node.val != inorder[index]) {
                node.left = new TreeNode(preorder[i]);
                stack.push(node.left);
            }else {
                while (index < inorder.length && stack.peek().val == inorder[index]) {
                    index++;
                    node = stack.pop();
                }
                node.right = new TreeNode(preorder[i]);
                stack.push(node.right);
            }
        }
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree3(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeDg2(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }
    public TreeNode buildTreeDg2(int[] inorder, int[] postorder, int inL, int inR, int poL, int poR) {
        if (inL > inR || poL > poR) return null;
        int index = indexMap.get(postorder[poR]);
        TreeNode node = new TreeNode(postorder[poR]);
        int num = index - inL;
        node.left = buildTreeDg2(inorder, postorder, inL, index - 1, poL, poL + num - 1);
        node.right = buildTreeDg2(inorder, postorder, index + 1, inR, poL + num, poR - 1);
        return node;
    }
    public TreeNode buildTree4(int[] inorder, int[] postorder) {
        Stack<TreeNode> stack = new Stack<>();
        int index = inorder.length - 1;
        TreeNode H = new TreeNode(postorder[postorder.length - 1]);
        stack.push(H);
        for (int i = postorder.length - 2; i >= 0; i--) {
            TreeNode node = stack.peek();
            if (node.val != inorder[index]) {
                node.right = new TreeNode(postorder[i]);
                stack.push(node.right);
            }else {
                while (!stack.isEmpty() && stack.peek().val == inorder[index]) {
                    node = stack.pop();
                    index--;
                }
                node.left = new TreeNode(postorder[i]);
                stack.push(node.left);
            }
        }
        return H;
    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    /**
     * 117. 填充每个节点的下一个右侧节点指针 II
     * @param root
     * @return
     */
    Map<Integer, Node> connectMap = new HashMap<>();
    public Node connect1(Node root) {
        connectDg(root, 1);
        return root;
    }
    public void connectDg(Node root, int step) {
        if (root == null) return;
        Node node = connectMap.get(step);
        if (node != null) {
            node.next = root;
        }
        connectMap.put(step, root);
        connectDg(root.left, step + 1);
        connectDg(root.right, step + 1);
    }
    public Node connect2(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int num = 1;
        while (!queue.isEmpty()) {
            Node pre = new Node();
            int n = 0;
            while (num-- != 0) {
                Node node = queue.poll();
                if (node.left != null) {
                    pre.next = node.left;
                    pre = node.left;
                    queue.add(node.left);
                    n++;
                }
                if (node.right != null) {
                    pre.next = node.right;
                    pre = node.right;
                    queue.add(node.right);
                    n++;
                }
            }
            num = n;
        }
        return root;
    }

    /**
     * 112. 路径总和
     * @param root
     * @param targetSum
     * @return
     */
    int haspathsum = 0;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        haspathsum += root.val;
        if (haspathsum == targetSum && root.left == null && root.right == null) return true;
        boolean l = hasPathSum(root.left, targetSum);
        if (l) return true;
        boolean r = hasPathSum(root.right, targetSum);
        haspathsum -= root.val;
        return r;
    }

    /**
     * 129. 求根节点到叶节点数字之和
     * @param root
     * @return
     */
    int sumNumbersRes = 0;
    public int sumNumbers(TreeNode root) {
        sumNumbersDg(root, 0);
        return sumNumbersRes;
    }
    public void sumNumbersDg(TreeNode root, int now) {
        if (root == null) return;
        now += root.val;
        if (root.left == null && root.right == null) {
            sumNumbersRes += now;
            return;
        }
        now *= 10;
        sumNumbersDg(root.left, now);
        sumNumbersDg(root.right, now);
    }

    /**
     * 124. 二叉树中的最大路径和
     * @param root
     * @return
     */
    int maxPathSumRes = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumDg(root);
        return maxPathSumRes;
    }
    public int maxPathSumDg(TreeNode root) {
        if (root == null) return 0;
        int left = maxPathSumDg(root.left);
        left = left < 0 ? 0 : left;
        int right = maxPathSumDg(root.right);
        right = right < 0 ? 0 : right;
        if (maxPathSumRes < root.val + left + right) {
            maxPathSumRes = root.val + left + right;
        }
        return Math.max(left, right) + root.val;
    }

    /**
     * 222. 完全二叉树的节点个数
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int countL = 0;
        int countR = 0;
        TreeNode l = root.left;
        while (l != null) {
            countL++;
            l = l.left;
        }
        l = root.right;
        while (l != null) {
            countR++;
            l = l.right;
        }
        if (countL == countR) {
            return (2 << countL) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 236. 二叉树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        else if (right == null) return left;
        return root;
    }

    /**
     * 199. 二叉树的右视图
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int n = 1;
        result.add(root.val);
        while (!queue.isEmpty()) {
            int now = 0;
            int r = -200;
            while (n-- != 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    r = node.left.val;
                    queue.add(node.left);
                    now++;
                }
                if (node.right != null) {
                    r = node.right.val;
                    queue.add(node.right);
                    now++;
                }
            }
            n = now;
            if (r != -200) result.add(r);
        }
        return result;
    }

    /**
     * 637. 二叉树的层平均值
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int n = 1;
        while (!queue.isEmpty()) {
            int nx = n;
            int now = 0;
            int sum = 0;
            while (nx-- != 0) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                    now++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    now++;
                }
            }
            result.add(sum / n * 1.0);
            n = now;
        }
        return result;
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int n = 1;
        while (!queue.isEmpty()) {
            List<Integer> r = new ArrayList<>();
            int now = 0;
            while (n-- != 0) {
                TreeNode node = queue.poll();
                r.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                    now++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    now++;
                }
            }
            n = now;
            result.add(r);
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> deque = new LinkedList<>();
        int n = 1;
        boolean is = true;
        deque.add(root);
        while (!deque.isEmpty()) {
            List<Integer> r = new ArrayList<>();
            int now = 0;
            while (n-- != 0) {
                TreeNode node = deque.poll();
                r.add(node.val);
                if (node.left != null) {
                    deque.add(node.left);
                    now++;
                }
                if (node.right != null) {
                    deque.add(node.right);
                    now++;
                }
            }
            n = now;
            if (!is) {
                Collections.reverse(r);
            }
            result.add(r);
            is = !is;
        }
        return result;
    }

    /**
     * 530. 二叉搜索树的最小绝对差
     * @param root
     * @return
     */
    int MinimumDifferencePre = -1;
    int MinimumDifferenceRes = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        getMinimumDifferenceDfs(root);
        return MinimumDifferenceRes;
    }
    public void getMinimumDifferenceDfs(TreeNode root) {
        if (root == null) return;
        getMinimumDifferenceDfs(root.left);
        if (MinimumDifferencePre != -1) {
            MinimumDifferenceRes = Math.min(MinimumDifferenceRes, root.val - MinimumDifferencePre);
        }
        MinimumDifferencePre = root.val;
        getMinimumDifferenceDfs(root.right);
    }

    /**
     * 230. 二叉搜索树中第 K 小的元素
     * @param root
     * @param k
     * @return
     */
    int kthSmallestRes = -1;
    int now = 0;
    public int kthSmallest(TreeNode root, int k) {
        if (kthSmallestRes != -1) return -1;
        if (root.left != null) kthSmallest(root.left, k);
        now++;
        if (now == k) kthSmallestRes = root.val;
        if (root.right != null) kthSmallest(root.right, k);
        return kthSmallestRes;
    }

    /**
     * 98. 验证二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTDg(root, null, null);
    }
    public boolean isValidBSTDg(TreeNode root, Integer left, Integer right) {
        if (root == null) return true;
        if (left != null && left >= root.val) return false;
        if (right != null && right <= root.val) return false;
        return isValidBSTDg(root.left, left, root.val) && isValidBSTDg(root.right, root.val, right);
    }


    public static void main(String[] args) {
        BinartTree bt = new BinartTree();
    }
}

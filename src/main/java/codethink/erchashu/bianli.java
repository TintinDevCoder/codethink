package codethink.erchashu;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class bianli {
    //先序遍历
        //递归
    List<Integer> preresult = new ArrayList<>();
    public void preTraversal(TreeNode root) {
        if (root == null) return;
        preresult.add(root.val);
        preTraversal(root.left);
        preTraversal(root.right);
    }
    public List<Integer> preorderTraversalDg(TreeNode root) {
        preTraversal(root);
        return preresult;
    }
        //迭代
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }
    //中序遍历
        //递归
    List<Integer> inresult = new ArrayList<>();
    public void inTraversal(TreeNode root) {
        if (root == null) return;
        inTraversal(root.left);
        inresult.add(root.val);
        inTraversal(root.right);
    }
    public List<Integer> inorderTraversalDg(TreeNode root) {
        inTraversal(root);
        return inresult;
    }
        //迭代
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) root = node.right;
        }
        return result;
    }
    //后序遍历
        //递归
    List<Integer> postresult = new ArrayList<>();
    public void postTraversal(TreeNode root) {
        if (root == null) return;
        postTraversal(root.left);
        postTraversal(root.right);
        postresult.add(root.val);
    }
    public List<Integer> postorderTraversalDg(TreeNode root) {
        postTraversal(root);
        return postresult;
    }
        //迭代
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode pre = root;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == pre) {
                result.add(root.val);
                pre = root;
                root = null;
            }else {
                stack.push(root);
                root = root.right;
            }
        }
        return result;
    }
    //层序遍历
      //递归
    List<List<Integer>> levelOrderResult = new ArrayList<>();
    public List<List<Integer>> levelOrder1(TreeNode root) {
        levelOrderDg(root, 0);
        return levelOrderResult;
    }
    public void levelOrderDg(TreeNode root, int level) {
        if (root == null)return;
        if (levelOrderResult.size() <= level) {
            levelOrderResult.add(new ArrayList<>());
        }
        levelOrderResult.get(level).add(root.val);
        if (root.left != null) levelOrderDg(root.left, level + 1);
        if (root.right != null) levelOrderDg(root.right, level + 1);
    }
    //102.二叉树的层序遍历https://leetcode.cn/problems/binary-tree-level-order-traversal/description/
      //迭代
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int nums = 1;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int newnums = 0;
            while (nums-- != 0) {
                TreeNode poll = queue.poll();
                level.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                    newnums++;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    newnums++;
                }
            }
            result.add(level);
            nums = newnums;
        }
        return result;
    }
    //107. 二叉树的层序遍历 IIhttps://leetcode.cn/problems/binary-tree-level-order-traversal-ii/submissions/589625404/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int nums = 1;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int newnums = 0;
            while (nums-- != 0) {
                TreeNode poll = queue.poll();
                level.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                    newnums++;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    newnums++;
                }
            }
            result.add(level);
            nums = newnums;
        }
        List<List<Integer>> newresult = new ArrayList<>();
        int len = result.size() - 1;
        for (int i = 0; i <= len; i++) {
            newresult.add(result.get(len - i));
        }
        return newresult;
    }
    //199.二叉树的右视图https://leetcode.cn/problems/binary-tree-right-side-view/description/
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        result.add(root.val);
        int nums = 1;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int newnums = 0;
            int now = -101;
            while (nums-- != 0) {
                TreeNode poll = queue.poll();
                level.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                    newnums++;
                    now = poll.left.val;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    newnums++;
                    now = poll.right.val;
                }
            }
            if (now == -101) break;
            result.add(now);
            nums = newnums;
        }
        return result;
    }
    List<Integer> rightSideViewResult = new ArrayList<>();
    public List<Integer> rightSideView2(TreeNode root) {
        rightSideViewDfs(root, 0);
        return rightSideViewResult;
    }
    public void rightSideViewDfs(TreeNode root, int level) {
        if (root == null) return;
        if (rightSideViewResult.size() ==  level) rightSideViewResult.add(root.val);
        if (root.right != null) {
            rightSideViewDfs(root.right, level + 1);
        }
        if (root.left != null) {
            rightSideViewDfs(root.left, level + 1);
        }
    }
    //637.二叉树的层平均值https://leetcode.cn/problems/average-of-levels-in-binary-tree/description/
    List<Long> averageOfLevelsResult = new ArrayList<>();
    List<Integer> averageOfLevelsnums = new ArrayList<>();
    public List<Double> averageOfLevels1(TreeNode root) {
        averageOfLevelsDfs(root, 0);
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < averageOfLevelsResult.size(); i++) {
            result.add((averageOfLevelsResult.get(i)  * 1.0 / averageOfLevelsnums.get(i)));
        }
        return result;
    }
    public void averageOfLevelsDfs(TreeNode root, int level) {
        if (root == null) return;
        if (averageOfLevelsResult.size() <= level) {
            averageOfLevelsResult.add(0l);
            averageOfLevelsnums.add(0);
        }
        Long n = averageOfLevelsResult.get(level);
        n += root.val;
        averageOfLevelsResult.set(level, n);
        averageOfLevelsnums.set(level,averageOfLevelsnums.get(level) + 1);
        if (root.left != null) averageOfLevelsDfs(root.left, level + 1);
        if (root.right != null) averageOfLevelsDfs(root.right, level + 1);
    }
    public List<Double> averageOfLevels2(TreeNode root) {
        if (root == null) return null;
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int nums = 1;
        while (!queue.isEmpty()) {
            int newnums = 0;
            Long sum = 0l;
            for (int i = 0; i < nums; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                    newnums++;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    newnums++;
                }
                sum += poll.val;
            }
            result.add(sum * 1.0 / nums);
            nums = newnums;
        }
        return result;
    }
    //429. N 叉树的层序遍历https://leetcode.cn/problems/n-ary-tree-level-order-traversal/description/
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int nums = 1;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int newnums = 0;
            while (nums-- != 0) {
                Node poll = queue.poll();
                level.add(poll.val);
                List<Node> children = poll.children;
                for (Node child : children) {
                    newnums++;
                    queue.add(child);
                }
            }
            result.add(level);
            nums = newnums;
        }
        return result;
    }
    //515. 在每个树行中找最大值https://leetcode.cn/problems/find-largest-value-in-each-tree-row/description/
    List<Integer> largestValuesResult = new ArrayList<>();
    public void largestDg(TreeNode root, int level) {
        if (largestValuesResult.size() <= level) largestValuesResult.add(root.val);
        else {
            if (root.val > largestValuesResult.get(level)) {
                largestValuesResult.remove(level);
                largestValuesResult.add(level, root.val);
            }
        }
        if (root.left != null) largestDg(root.left, level + 1);
        if (root.right != null) largestDg(root.right, level + 1);
    }
    public List<Integer> largestValues1(TreeNode root) {
        if (root == null) return null;
        largestDg(root, 0);
        return largestValuesResult;
    }
    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int nums = 1;
        while (!queue.isEmpty()) {
            int maxx = Integer.MIN_VALUE;
            int newnums = 0;
            while (nums-- != 0) {
                TreeNode poll = queue.poll();
                if (poll.val > maxx) maxx = poll.val;
                if (poll.left != null) {
                    queue.add(poll.left);
                    newnums++;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    newnums++;
                }
            }
            result.add(maxx);
            nums = newnums;
        }
        return result;
    }
    //116. 填充每个节点的下一个右侧节点指针https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/description/
    public Node2 connect(Node2 root) {
        if (root == null) return root;
        Queue<Node2> queue = new LinkedList<>();
        queue.add(root);
        int nums = 1;
        while (!queue.isEmpty()) {
            Node2 pre = null;
            int newnums = 0;
            while (nums-- != 0) {
                Node2 poll = queue.poll();
                if (pre == null) pre = poll;
                else {
                    pre.next = poll;
                    pre = poll;
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                    newnums++;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    newnums++;
                }
            }
            pre.next = null;
            nums = newnums;
        }
        return root;
    }
    //104.二叉树的最大深度https://leetcode.cn/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    // 111.二叉树的最小深度https://leetcode.cn/problems/minimum-depth-of-binary-tree/
    int minDepthResult = Integer.MAX_VALUE;
    public void minDepthDfs(TreeNode root, int level) {
        if (root == null || level >= minDepthResult) return;
        if (root.left == null && root.right == null) {
            if (level < minDepthResult) minDepthResult = level;
            return;
        }
        if (root.left != null) minDepthDfs(root.left, level + 1);
        if (root.right != null) minDepthDfs(root.right, level + 1);
    }
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        minDepthDfs(root, 1);
        return minDepthResult;
    }
    
    public static void main(String[] args) throws IOException {
        bianli b = new bianli();
        b.averageOfLevels1(new TreeNode(3, new TreeNode(9, null, null), new TreeNode(20, new TreeNode(15), new TreeNode(7))));
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Node2 {
    public int val;
    public Node2 left;
    public Node2 right;
    public Node2 next;

    public Node2() {}

    public Node2(int _val) {
        val = _val;
    }

    public Node2(int _val, Node2 _left, Node2 _right, Node2 _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
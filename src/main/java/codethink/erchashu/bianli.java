package codethink.erchashu;

import java.util.*;

public class bianli {
    List<Integer> preorderResult = new ArrayList<>();
    public List<Integer> preorderTraversal1(TreeNode root) {
        preorderTraversalDg(root);
        return preorderResult;
    }
    public void preorderTraversalDg(TreeNode root) {
        if (root == null) return;
        preorderResult.add(root.val);
        preorderTraversalDg(root.left);
        preorderTraversalDg(root.right);
    }
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) return preorderResult;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            preorderResult.add(node.val);
            if (node.right != null) stack.add(node.right);
            if (node.left != null) stack.add(node.left);
        }
        return preorderResult;
    }
    List<Integer> inorderResult = new ArrayList<>();
    public List<Integer> inorderTraversal1(TreeNode root) {
        inorderTraversalDg(root);
        return inorderResult;
    }
    public void inorderTraversalDg(TreeNode root) {
        if (root == null) return;
        inorderTraversalDg(root.left);
        inorderResult.add(root.val);
        inorderTraversalDg(root.right);
    }
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) return inorderResult;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            inorderResult.add(node.val);
            if (node.right != null) root = node.right;
        }
        return inorderResult;
    }
    List<Integer> postorderResult = new ArrayList<>();
    public List<Integer> postorderTraversal1(TreeNode root) {
        postorderTraversalDg(root);
        return postorderResult;
    }
    public void postorderTraversalDg(TreeNode root) {
        if (root == null) return;
        postorderTraversalDg(root.left);
        postorderTraversalDg(root.right);
        postorderResult.add(root.val);
    }
    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) return postorderResult;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = new TreeNode();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                pre = root;
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || pre == root.right) {
                postorderResult.add(root.val);
                pre = root;
                root = null;
            }else {
                stack.add(root.right);
                root = root.right;
            }
        }
        return postorderResult;
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int num = 1;
        while (!queue.isEmpty()) {
            List<Integer> floor = new ArrayList<>();
            num = queue.size();
            while (!queue.isEmpty() && num-- != 0) {
                TreeNode node = queue.poll();
                floor.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(floor);
        }
        return result;
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int num = 1;
        while (!queue.isEmpty()) {
            List<Integer> floor = new ArrayList<>();
            num = queue.size();
            while (!queue.isEmpty() && num-- != 0) {
                TreeNode node = queue.poll();
                floor.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(floor);
        }
        int left = 0;
        int right = result.size() - 1;
        while (left < right) {
            List<Integer> temp = result.get(left);
            result.set(left, result.get(right));
            result.set(right, temp);
            left++;
            right--;
        }
        return result;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int num = 1;
        while (!queue.isEmpty()) {
            List<Integer> floor = new ArrayList<>();
            num = queue.size();
            while (!queue.isEmpty() && num-- != 0) {
                TreeNode node = queue.poll();
                floor.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(floor.get(floor.size() - 1));
        }
        return result;
    }
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int num = 1;
        while (!queue.isEmpty()) {
            double s = 0.0;
            num = queue.size();
            int n = num;
            while (!queue.isEmpty() && num-- != 0) {
                TreeNode node = queue.poll();
                s += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(s / n);
        }
        return result;
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
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int num = 1;
        while (!queue.isEmpty()) {
            List<Integer> floor = new ArrayList<>();
            num = queue.size();
            while (!queue.isEmpty() && num-- != 0) {
                Node node = queue.poll();
                floor.add(node.val);
                List<Node> children = node.children;
                for (Node child : children) {
                    queue.add(child);
                }
            }
            result.add(floor);
        }
        return result;
    }
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int num;
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            num = queue.size();
            while (!queue.isEmpty() && num-- != 0) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(max);
        }
        return result;
    }
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    int minDepthResult = Integer.MAX_VALUE;
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;
        minDepthDfs(root, 1);
        return minDepthResult;
    }
    public void minDepthDfs(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            minDepthResult = Math.min(minDepthResult, depth);
            return;
        }
        if (root.left != null) minDepthDfs(root.left, depth + 1);
        if (root.right != null) minDepthDfs(root.right, depth + 1);
    }
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int num = 1;
        int depth = 1;
        while (!queue.isEmpty()) {
            num = queue.size();
            while (!queue.isEmpty() && num-- != 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
    public static void main(String[] args) {

    }
}

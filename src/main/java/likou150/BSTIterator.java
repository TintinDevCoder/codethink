package likou150;

import java.util.*;

/**
 * 173. 二叉搜索树迭代器
 */
class BSTIterator {
    TreeNode cur;
    Deque<TreeNode> stack = new ArrayDeque<>();
    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new ArrayDeque<>();
    }
    
    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int re = cur.val;
        cur = cur.right;
        return re;
    }
    
    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}
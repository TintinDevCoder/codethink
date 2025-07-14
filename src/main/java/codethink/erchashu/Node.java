package codethink.erchashu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public Node connect1(Node root) {
        if (root == null) return root;
        Node left = root;
        while (left.left != null) {
            Node node = left;
            while (node != null) {
                if (node.left != null) {
                    if (node.right != null) {
                        node.left.next = node.right;
                    } else node.left.next = null;
                }
                if (node.next != null) {
                    node.right.next = node.next.left;
                }
                node = node.next;
            }
            left = left.left;
        }
        return root;
    }
    List<Node> connectList = new ArrayList<>();
    public Node connect2(Node root) {
        connectDfs(root, 0);
        return root;
    }
    public void connectDfs(Node node, int depth) {
        if (node == null) return;
        if (depth == connectList.size()) {
            connectList.add(node);
        }else {
            connectList.get(depth).next = node;
            connectList.set(depth, node);
        }
        connectDfs(node.left, depth + 1);
        connectDfs(node.right, depth + 1);
    }
};
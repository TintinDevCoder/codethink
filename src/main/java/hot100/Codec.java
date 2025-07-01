package hot100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Codec {
    String BlankNode = "null";
    String Split = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preserialize(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    public void preserialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(BlankNode + Split);
        }else {
            sb.append(root.val + Split);
            preserialize(root.left,sb);
            preserialize(root.right, sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] da = data.split(Split);
        Queue<String> stack = new LinkedList<>();
        for (String s : da) {
            stack.add(s);
        }
        TreeNode root = predeserialize(stack);
        return root;
    }
    public TreeNode predeserialize(Queue<String> stack) {
        String poll = stack.poll();
        if (poll.equals(BlankNode)) {
            return null;
        }
        TreeNode root = new TreeNode();
        root.val = Integer.parseInt(poll);
        root.left = predeserialize(stack);
        root.right = predeserialize(stack);
        return root;
    }
}
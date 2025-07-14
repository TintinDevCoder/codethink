package codethink.erchashu;

public class leetcode572 {
    static final int MAX_N = 1005; //最多节点个数
    static final int MOD = 1000000007; //模数
    int[] p = new int[MAX_N]; //素数集合
    int subcode = Integer.MIN_VALUE;
    boolean result = false;
    int h = 0;
    public void getPrime() {
        boolean[] vis = new boolean[MAX_N];
        vis[0] = vis[1] = true;
        int n = 0;
        for (int i = 2; i < MAX_N; i++) {
            p[++n] = i;
            for (int j = 1; j <= n && i * p[j] < MAX_N ; j++) {
                vis[i * p[j]] = true;
                if (i % p[j] == 0) break;
            }
        }
    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        getPrime();
        subcode = dfs(subRoot)[0];
        int d = dfs(root)[0];
        if (d == subcode) return true;
        return result;
    }
    public int[] dfs(TreeNode root) {
        int num = 1;
        int hash = root.val;
        if (root.left != null) {
            int[] left = dfs(root.left);
            if (left[0] == subcode)
                result = true;
            num += left[1];
            hash = (int)(hash + (31L * left[0] * p[num]) % MOD) % MOD;
        }
        if (root.right != null) {
            int[] right = dfs(root.right);
            if (right[0] == subcode)
                result = true;
            num += right[1];
            hash = (int)(hash + (179L * right[0] * p[num]) % MOD) % MOD;
        }
        return new int[]{hash, num};
    }

    public static void main(String[] args) {
        leetcode572 s = new leetcode572();
        TreeNode subRoot = new TreeNode(9, new TreeNode(4,new TreeNode(4487, new TreeNode(0, new TreeNode(-1), null), null), new TreeNode(6)), null);
        TreeNode root = new TreeNode(-3, new TreeNode(-5, new TreeNode(-8, null, new TreeNode(-7)), null), new TreeNode(9, new TreeNode(4,new TreeNode(2, new TreeNode(0, new TreeNode(-1), null), null), new TreeNode(6)), null));
        s.isSubtree(root, subRoot);
    }
}

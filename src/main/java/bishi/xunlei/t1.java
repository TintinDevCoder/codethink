package bishi.xunlei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class t1 {
    class Node {
        int v;
        int node_bandwidth;
        int link_bandwidth;
        int delay;
        public Node(int v, int n, int l, int d) {
            this.v = v;
            this.node_bandwidth = n;
            this.link_bandwidth = l;
            this.delay = d;
        }
    }
    static Map<Integer, List<Node>> map = new HashMap<>();
    int result = Integer.MAX_VALUE;
    public int find_min_delay_path (int n, int s, int t, int[] node_bandwidths, int[][] edges, int d) {
        // write code here
        for (int[] edge : edges) {
            List<Node> orDefault = map.getOrDefault(edge[0], new ArrayList<>());
            orDefault.add(new Node(edge[1], node_bandwidths[edge[1]], edge[2], edge[3]));
            map.put(edge[0], orDefault);
        }
        dfs(s, t, s, 0, d);
        return result == Long.MAX_VALUE ? -1 : result;
    }
    public void dfs(int s, int t, int index, int now, int d) {
        if (index == t) {
            result = Math.min(result, now);
            return;
        }
        List<Node> nodes = map.get(index);
        if (nodes == null || nodes.isEmpty()) return;
        for (Node node : nodes) {
            if (node.node_bandwidth >= d && node.link_bandwidth >= d) {
                dfs(s, t, node.v, now + node.delay, d);
            }
        }
    }

    public int[] find_palindrome_subarrays (int[] nums, int k) {
        // write code here
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i <= n - k; i++) {
            int left = i, right = i + k - 1;
            boolean is = true;
            while (left < right) {
                if (nums[left] != nums[right]) {
                    is = false;
                    break;
                }
                left++;
                right--;
            }
            if (is) result.add(i);
        }
        int[] r = new int[result.size()];
        for (int i = 0; i < r.length; i++) {
            r[i] = result.get(i);
        }
        return r;
    }
    public boolean isMatch (String s, String p) {
        // write code here
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int m = sc.length;
        int n = pc.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (pc[i - 1] == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pc[j - 1] == '.' || pc[j - 1] == sc[i -1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else if (pc[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (pc[j - 2] == '.' || pc[j - 2] == sc[i - 1]) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        t1 t = new t1();
        System.out.println(t.isMatch("aa","a*"));
    }
}

package likou150;

import java.util.*;

public class Graph {
    /**
     * 200. 岛屿数量
     * @param grid
     * @return
     */
    int numIslandsRes = 0;
    int[][] target = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean[][] isVisited;
    public int numIslands(char[][] grid) {
        int len1 = grid.length, len2 = grid[0].length;
        isVisited = new boolean[len1][len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (grid[i][j] == '1' && !isVisited[i][j]) {
                    numIslandsRes++;
                    numIslandsDfs(grid, i, j);
                }
            }
        }
        return numIslandsRes;
    }
    public void numIslandsDfs(char[][] grid, int i, int j) {
        isVisited[i][j] = true;
        for (int[] ints : target) {
            int x = i + ints[0];
            int y = j + ints[1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0' || isVisited[x][y]) continue;
            numIslandsDfs(grid, x, y);
        }
    }

    /**
     * 130. 被围绕的区域
     * @param board
     */
    public void solve(char[][] board) {
        int len1 = board.length;
        int len2 = board[0].length;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if ((i == 0 || j == 0 || i == len1 - 1 || j == len2 - 1) && board[i][j] == 'O') {
                    solveDfs(board, i, j);
                }
            }
        }
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (board[i][j] == 'E') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }
    public void solveDfs(char[][] board, int i, int j) {
        board[i][j] = 'E';
        for (int[] ints : target) {
            int x = i + ints[0];
            int y = j + ints[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                continue;
            }
            if (board[x][y] == 'O') solveDfs(board, x, y);
        }
    }
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    /**
     * 133. 克隆图
     * @param node
     * @return
     */
    Map<Integer, Node> cloneGraphMap = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Node newnode = new Node();
        newnode.val = node.val;
        cloneGraphMap.put(node.val, newnode);
        for (Node neighbor : node.neighbors) {
            Node node1 = null;
            if (cloneGraphMap.containsKey(neighbor.val)) {
                node1 = cloneGraphMap.get(neighbor.val);
            }else {
                node1= cloneGraph(neighbor);
            }
            newnode.neighbors.add(node1);
        }
        return newnode;
    }
    class UnionFind{
        int[] parent;
        double[] weight;
        public UnionFind(int n) {
            parent = new int[n];
            weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0;
            }
        }
        public int find(int x) {
            if (x != parent[x]) {
                int parentx = parent[x];
                parent[x] = find(parent[x]);
                weight[x] = weight[x] * weight[parentx];
            }
            return parent[x];
        }
        public void union(int x, int y, double value) {
            int findx = find(x);
            int findy = find(y);
            if (findx == findy) return;
            parent[findx] = findy;
            weight[findx] = weight[y] * value / weight[x];
        }
        public double connectedWeight(int x, int y) {
            int findx = find(x);
            int findy = find(y);
            if (findx == findy) {
                return weight[x] / weight[y];
            }else return -1.0d;
        }
    }

    /**
     * 399. 除法求值
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        UnionFind unionFind = new UnionFind(equations.size() * 2);
        int index = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String s1 = equation.get(0);
            String s2 = equation.get(1);
            if (!map.containsKey(s1)) {
                map.put(s1, index++);
            }
            if (!map.containsKey(s2)) {
                map.put(s2, index++);
            }
            unionFind.union(map.get(s1), map.get(s2), values[i]);
        }
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> equation = queries.get(i);
            String s1 = equation.get(0);
            String s2 = equation.get(1);
            int key1 = map.getOrDefault(s1, -1);
            int key2 = map.getOrDefault(s2, -1);
            if (key1 == -1 || key2 == -1) {
                result[i] = -1;
                continue;
            }
            result[i] = unionFind.connectedWeight(key1, key2);
        }
        return result;
    }

    /**
     * 207. 课程表
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] courseNum = new int[numCourses];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            courseNum[prerequisite[0]]++;
            list.get(prerequisite[1]).add(prerequisite[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int result = 0;
        for (int i = 0; i < courseNum.length; i++) {
            if (courseNum[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            result++;
            Integer poll = queue.poll();
            List<Integer> integerList = list.get(poll);
            for (Integer i : integerList) {
                if (--courseNum[i] == 0) {
                    queue.add(i);
                }
            }
        }
        return result == numCourses;
    }

    /**
     * 210. 课程表 II
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] courseNum = new int[numCourses];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            courseNum[prerequisite[0]]++;
            list.get(prerequisite[1]).add(prerequisite[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[numCourses];
        int j = 0;
        for (int i = 0; i < courseNum.length; i++) {
            if (courseNum[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            result[j++] = poll;
            List<Integer> integerList = list.get(poll);
            for (Integer i : integerList) {
                if (--courseNum[i] == 0) {
                    queue.add(i);
                }
            }
        }
        return j == numCourses ? result : new int[]{};
    }

    /**
     * 433. 最小基因变化
     * @param startGene
     * @param endGene
     * @param bank
     * @return
     */

    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> minMutationset = new HashSet<>();
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int minMutationRes = 9;
        char[] minMutationtar = {'A', 'C', 'G', 'T'};
        for (String s : bank) {
            minMutationset.add(s);
        }
        set.add(startGene);
        queue.add(startGene);
        int step = 1;
        int cnt = 1;
        while (!queue.isEmpty()) {
            cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                String node = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        StringBuffer sb = new StringBuffer(node);
                        sb.setCharAt(j, minMutationtar[k]);
                        String next = sb.toString();
                        if (minMutationset.contains(next) && !set.contains(next)) {
                            if (next.equals(endGene)) {
                                return step;
                            }
                            queue.offer(next);
                            set.add(next);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Graph g = new Graph();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        g.cloneGraph(node1);
    }
}

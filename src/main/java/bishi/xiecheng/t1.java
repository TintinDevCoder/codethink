package bishi.xiecheng;

import java.util.*;

public class t1 {
    public static void carType() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- != 0) {
            int v = sc.nextInt();
            if (v == 250) System.out.println("G D C");
            else if (v > 350 || v < 160) System.out.println("other");
            else if (v > 250 && v <= 300) System.out.println("G C");
            else if (v > 300 && v <= 350) System.out.println("G");
            else if (v >= 200 && v < 250) System.out.println("D C");
            else System.out.println("D");
        }
    }
    public static void pailieMin() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            max = (max >= temp) ? max : temp;
            System.out.print(max - i - 1);
            if (i != n - 1) System.out.print(" ");
        }
    }
    public static long count(long n, long k, long x) {
        if (x > n) return 0;
        return (n - x) / k + 1;
    }
    public static void shuziNum() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- != 0) {
            long l = sc.nextInt();
            long r = sc.nextInt();
            long k = sc.nextInt();
            long x = sc.nextInt();
            long result = count(r, k, x) - count(l - 1, k, x);
            System.out.println(result);
        }
    }
    static int n, m, k;
    static List<int[]>[] adj;
    static int[][] targets;
    static Set<Integer> redNodes;
    public static void kMin2() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        redNodes = new HashSet<>();
        for (int i = 0; i < m; i++) {
            redNodes.add(sc.nextInt());
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }
        targets = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int[] ints : adj[i]) {
                targets[i][ints[0]] = ints[1];
                dfs(i, ints[0], ints[1]);
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!redNodes.contains(j)) targets[i][j] = 0;
            }
        }
        for (int i = 1; i <= n; i++) {
            Arrays.sort(targets[i]);
            int K = k;
            int sum = 0;
            if (redNodes.contains(i)) K--;
            for (int j : targets[i]) {
                if (K == 0) {
                    System.out.print(sum);
                    if (i != n) System.out.print(" ");
                    break;
                }
                if (j != 0) {
                    sum += j;
                    K--;
                }
            }
        }
    }
    public static void dfs(int index, int i, int now) {
        if (i == index) return;
        for (int[] ints : adj[i]) {
            if (targets[index][ints[0]] == 0 || targets[index][ints[0]] > now + ints[1]) {
                targets[index][ints[0]] = now + ints[1];
                dfs(index, ints[0], now + ints[1]);
            }
        }
    }
    public static void main(String[] args) {
        kMin2();
    }
}

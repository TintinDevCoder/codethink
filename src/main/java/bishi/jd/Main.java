package bishi.jd;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //定点数
        int m = sc.nextInt(); //边数
        int[][] edges = new int[m + 1][2]; //记录边
        for (int i = 1; i <= m; i++) {
            edges[i][0] = sc.nextInt();
        }
        for (int i = 1; i <= m; i++) {
            edges[i][1] = sc.nextInt();
        }
        int q = sc.nextInt(); //询问次数

        for (int i = 0; i < q; i++) {
            int c = sc.nextInt();
            List<Integer> queryEdges = new ArrayList<>();
            for (int j = 0; j < c; j++) {
                int c1 = sc.nextInt();
                queryEdges.add(c1);
            }
            //验证是否为匹配
            Set<Integer> vertices = new HashSet<>();
            //验证非边集边是否指多于一条边集的边共享端点
            Map<Integer, Integer> vertexCount = new HashMap<>();
            boolean isMatching = true;
            for (Integer queryEdge : queryEdges) {
                int u = edges[queryEdge][0];
                int v = edges[queryEdge][1];
                if (vertices.contains(u) || vertices.contains(v)) {
                    isMatching = false;
                    break;
                }
                vertices.add(u);
                vertices.add(v);
                vertexCount.put(u, vertexCount.getOrDefault(u, 0) + 1);
                vertexCount.put(v, vertexCount.getOrDefault(v, 0) + 1);
            }
            if (!isMatching) {
                System.out.println("No");
                continue;
            }
            for (int k = 1; k <= m; k++) {
                if (queryEdges.contains(k)) {
                    continue;//跳过边集内的边
                }
                int u = edges[k][0];
                int v = edges[k][1];
                //计算两个端点在边集中的出现次数之和
                int countU = vertexCount.getOrDefault(u, 0);
                int countV = vertexCount.getOrDefault(v, 0);
                if (countU + countV >= 2) {
                    isMatching = false;
                    break;
                }
            }
            if (!isMatching) {
                System.out.println("No");
            }else {
                System.out.println("Yes");
            }
        }
    }
}

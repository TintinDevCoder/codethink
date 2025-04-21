package codethink.shuzu;

import java.util.ArrayList;
import java.util.List;

public class luoxuanjuzhen {
    /*59.螺旋矩阵IIhttps://leetcode.cn/problems/spiral-matrix-ii/description/*/
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[0][i] = i + 1;
        }
        int i = n + 1;
        int k = 2;
        int lie = n - 1;
        int hang = 0;
        int now = n - 1;
        int s = 0;
        while(i <= n * n){
            if (k == 1) {
                for (int j = 0; j < now; j++)
                    matrix[hang][++lie] = i++;
            }else if (k == 2) {
                for (int j = 0; j < now; j++)
                    matrix[++hang][lie] = i++;
            }else if (k == 3) {
                for (int j = 0; j < now; j++)
                    matrix[hang][--lie] = i++;
            }else {
                for (int j = 0; j < now; j++)
                    matrix[--hang][lie] = i++;
            }
            if (++s == 2) {
                now--;
                s = 0;
            }
            k = k >= 4 ? 1 : k + 1;
        }
        return matrix;
    }
    /*54.螺旋矩阵https://leetcode.cn/problems/spiral-matrix/description/*/
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> list = new ArrayList<Integer>();
        boolean[][] visited = new boolean[m][n];
        int hang = 0, lie = -1;
        int k = 1;
        int i = 0;
        while (i < m * n) {
            if (k == 1) {
                for (lie = lie + 1;lie < n; lie++){
                    if (visited[hang][lie]) break;
                    list.add(matrix[hang][lie]);
                    visited[hang][lie] = true;
                    i++;
                }
                lie--;
            }else if (k == 2) {
                for (hang = hang + 1; hang < m; hang++){
                    if (visited[hang][lie]) break;
                    list.add(matrix[hang][lie]);
                    visited[hang][lie] = true;
                    i++;
                }
                hang--;
            }else if (k == 3) {
                for (lie = lie - 1; lie >= 0; lie--) {
                    if (visited[hang][lie]) break;
                    list.add(matrix[hang][lie]);
                    visited[hang][lie] = true;
                    i++;
                }
                lie++;
            }else {
                for (hang = hang - 1; hang >= 0; hang--) {
                    if (visited[hang][lie]) break;
                    list.add(matrix[hang][lie]);
                    visited[hang][lie] = true;
                    i++;
                }
                hang++;
            }
            k = k >= 4 ? 1 : k + 1;
        }
        return list;
    }

    public static void main(String[] args) {

    }
}

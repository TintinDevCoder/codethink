package likou150;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Matrix {
    /**
     * 36. 有效的数独
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        List<List<Character>> hang = new ArrayList<>();
        List<List<Character>> lie = new ArrayList<>();
        List<List<Character>> kuai = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hang.add(new ArrayList<>());
            lie.add(new ArrayList<>());
            kuai.add(new ArrayList<>());
        }
        for (int i = 0; i < board.length; i++) {
            List<Character> l1 = hang.get(i);
            for (int j = 0; j < board[i].length; j++) {
                List<Character> l2 = lie.get(j); // 修正这里
                List<Character> l3 = kuai.get(i / 3 * 3 + j / 3);
                if (board[i][j] == '.') continue;
                if (l1.contains(board[i][j]) || l2.contains(board[i][j]) || l3.contains(board[i][j])) return false;
                l1.add(board[i][j]);
                l2.add(board[i][j]);
                l3.add(board[i][j]);
            }
        }
        return true;
    }

    /**
     * 54. 螺旋矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int nums = m * n;
        boolean[][] isVisited = new boolean[m][n];
        List<Integer> result = new ArrayList<>();
        int[][] targets = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int target = 0;
        int i = 0, j = 0;
        while (nums-- != 0) {
            result.add(matrix[i][j]);
            isVisited[i][j] = true;
            int x = i + targets[target][0];
            int y = j + targets[target][1];
            if ((x < 0 || x >= m || y < 0 || y >= n) || isVisited[x][y]) {
                target = (target + 1) % 4;
                x = i + targets[target][0];
                y = j + targets[target][1];
            }
            i = x;
            j = y;
        }
        long l = 0l;
        return result;
    }

    /**
     * 48. 旋转图像
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 73.矩阵置零
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        Set<Integer> hang = new HashSet<>();
        Set<Integer> lie = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    hang.add(i);
                    lie.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (hang.contains(i)) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (lie.contains(i)) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    /**
     * 289.生命游戏
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int[][] targets = {{-1, -1},{-1, 0},{-1, 1},{0, -1},{0, 1},{1, -1},{1, 0},{1, 1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int sum = 0;
                for (int[] target : targets) {
                    int x = i + target[0];
                    int y = j + target[1];
                    if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && (board[x][y] == 1 || board[x][y] == -1)) {
                        sum++;
                    }
                    if (sum > 3) break;
                }
                if (sum < 2 || sum > 3) {
                    if (board[i][j] == 1) {
                        board[i][j] = -1;
                    }
                }
                else if (sum == 3) {
                    if (board[i][j] == 0) board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] == -1 ? 0 : board[i][j];
                board[i][j] = board[i][j] == 2 ? 1 : board[i][j];
            }
        }
    }
    public static void main(String[] args) {
        Matrix m = new Matrix();
        m.gameOfLife(new int[][] {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        });
    }

}

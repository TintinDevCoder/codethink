package mianjing;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class elsemj {
    boolean[][] solveSudokuRow = new boolean[9][10];
    boolean[][] solveSudokuCol = new boolean[9][10];
    boolean[][] solveSudokuBlock = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    solveSudokuRow[i][num] = true;
                    solveSudokuCol[j][num] = true;
                    solveSudokuBlock[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        solveSudokuBack(board);
    }

    public boolean solveSudokuBack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (int k = 1; k <= 9; k++) {
                        if (!solveSudokuRow[i][k] && !solveSudokuCol[j][k] && !solveSudokuBlock[i / 3 * 3 + j / 3][k]) {
                            // 尝试填入数字
                            board[i][j] = (char) ('0' + k);
                            solveSudokuRow[i][k] = true;
                            solveSudokuCol[j][k] = true;
                            solveSudokuBlock[i / 3 * 3 + j / 3][k] = true;

                            // 递归调用
                            if (solveSudokuBack(board)) {
                                return true;
                            }

                            // 回溯
                            board[i][j] = '.';
                            solveSudokuRow[i][k] = false;
                            solveSudokuCol[j][k] = false;
                            solveSudokuBlock[i / 3 * 3 + j / 3][k] = false;
                        }
                    }
                    return false; // 如果没有可填入的数字，返回 false
                }
            }
        }
        return true; // 如果完成了所有的填充，返回 true
    }

    public static void main(String[] args) {
        Set set = new HashSet();
        Queue queue = new LinkedList();
    }
}

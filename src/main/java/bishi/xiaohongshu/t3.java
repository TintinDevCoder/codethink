package bishi.xiaohongshu;

import java.util.*;

public class t3 {
    public static boolean isPerfect(int n) {
        if (n < 6) return false;
        int now = 1 * 2 * 3;
        for (int a = 4; a < n; a++) {
            if (now == n) return true;
            else if (now > n) break;
            now /= a - 3;
            now *= a;
        }
        now = 1 * 2 * 3 * 4;
        for (int a = 5; a < n; a++) {
            if (now == n) return true;
            else if (now > n) break;
            now /= a - 4;
            now *= a;
        }
        now = 1 * 2 * 3 * 4 * 5;
        for (int a = 6; a < n; a++) {
            if (now == n) return true;
            else if (now > n) break;
            now /= a - 5;
            now *= a;
        }
        now = 1 * 2 * 3 * 4 * 5 * 6;
        for (int a = 7; a < n; a++) {
            if (now == n) return true;
            else if (now > n) break;
            now /= a - 6;
            now *= a;
        }
        now = 1 * 2 * 3 * 4 * 5 * 6 * 7;
        for (int a = 8; a < n; a++) {
            if (now == n) return true;
            else if (now > n) break;
            now /= a - 7;
            now *= a;
        }
        now = 1 * 2 * 3 * 4 * 5 * 6 * 7* 8;
        for (int a = 9; a < n; a++) {
            if (now == n) return true;
            else if (now > n) break;
            now /= a - 8;
            now *= a;
        }
        now = 1 * 2 * 3 * 4 * 5 * 6 * 7*8*9;
        for (int a = 10; a < n; a++) {
            if (now == n) return true;
            else if (now > n) break;
            now /= a - 9;
            now *= a;
        }
        return false;
    }
    public static void pefectNum() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- != 0) {
            int x = sc.nextInt();
            boolean isPerfect = isPerfect(x);
            if (isPerfect) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static int findPo(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int count = 0;
        int lastPoint = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[0] > lastPoint) {
                count++;
                lastPoint = interval[1];
            }
        }
        return count;
    }

    public static void fuwujiedianbuju() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- != 0) {
            int n = sc.nextInt();
            int[][] input = new int[n][2];
            for (int i = 0; i < n; i++) {
                int k = sc.nextInt();
                int left = i - k < 1 ? 1 : i - k;
                int right = i + k > n ? n : i + k;
                input[i] = new int[]{left, right};
            }
            int pointNeed = findPo(input);
            System.out.println(pointNeed);
        }
    }

    public static void main(String[] args) {
        fuwujiedianbuju();
    }
}

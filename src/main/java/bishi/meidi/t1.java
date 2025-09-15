package bishi.meidi;
import java.util.*;

public class t1 {
    public static void dataProcess() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] a = input.substring(1, input.length() - 1).split(",");
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < a.length; i++) {
            int n = Integer.parseInt(a[i]);
            set.add(n);
        }
        int size = set.size();
        int i = 0;
        for (Integer integer : set) {
            System.out.print(integer);
            if (i != size - 1) {
                System.out.print(",");
            }
            i++;
        }
    }
    public static void box() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] heights = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            heights[i] = sc.nextInt();
        }
        int[][] minHeight = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            minHeight[i][i] = heights[i];
            for (int j = i + 1; j <= n; j++) {
                minHeight[i][j] = Math.min(minHeight[i][j - 1],heights[j]);
            }
        }
        double[][] dp = new double[n + 1][x + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = minHeight[1][i] * Math.sqrt(i);
        }
        for (int i = 2; i <= x; i++) {
            for (int j = i; j <= n; j++) {
                for (int k = i - 1; k < j; k++) {
                    dp[j][i] = Math.max(dp[j][i], dp[k][i - 1] + minHeight[k + 1][j] * Math.sqrt(j - k));
                }
            }
        }
        System.out.println(String.format("%.6f", dp[n][x]));
    }
    public static void arraySort() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<Integer> set1 = new TreeSet<>();
        Set<Integer> set2 = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            if (temp % 2 == 0) set1.add(temp);
            else set2.add(temp);
        }
        int size1 = set1.size();
        int size2 = set2.size();
        int j = 0;
        for (Integer i : set2) {
            if (j != size2 - 1) {
                System.out.print(i + " ");
            }else {
                System.out.print(i);
                if (size1 != 0) System.out.print(" ");
            }
            j++;
        }
        j = 0;
        for (Integer i : set1) {
            System.out.print(i);
            if (j != size1 - 1) {
                System.out.print(" ");
            }
            j++;
        }
    }
    public static void main(String[] args) {
        arraySort();
    }
}

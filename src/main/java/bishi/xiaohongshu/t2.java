package bishi.xiaohongshu;

import java.util.*;

public class t2 {
    public static void superSort() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] count = new int[10]; //记录每个数字的个数
        int[] wei = new int[10];
        int maxxwei = 0;
        for (int i = 0; i < n; i++) {
            int w = 0;
            int t = sc.nextInt();
            while (t != 0) {
                count[t % 10]++;
                w++;
                wei[w]++;
                t /= 10;
            }
            maxxwei = Math.max(maxxwei, w);
        }
        long result = 0;
        int j = maxxwei, k = 9;
        while (j > 0 && k >= 0) {
            if (wei[j] <= 0) {
                j--;
                continue;
            }
            if (count[k] > 0) {
                result += Math.pow(10, j - 1) * k;
                count[k]--;
                wei[j]--;
            }else {
                k--;
                continue;
            }
        }
        System.out.println(result);
    }
    public static void behaviorWeight() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int a[] = new int[n];
            long[] preSum = new long[n + 1];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                preSum[i + 1] = preSum[i] + a[i];
            }
            for (int m = 1; m <= n; m++) {
                int deleteNum = n - m;
                int start = 0, end = m;
                long minn = preSum[end] - preSum[start];
                if (deleteNum == 0) {
                    System.out.print(minn);
                    break;
                }
                int j = 1;
                int temp = 1;
                end++;
                start++;
                while (end <= n && deleteNum > 0) {
                    minn = Math.min(minn, preSum[end] - preSum[start] + temp * k);
                    deleteNum--;
                    end++;
                    start++;
                    temp++;
                }
                System.out.print(minn);
                if (m != n) System.out.print(" ");
            }
            System.out.println();
        }
    }
    static final int MOD = (int) 1e9 + 7;
    public static void historyContribution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        long result = 1;
        int prev = 0;
        int count = 0;
        for (int i : a) {
            if (i != 0) {
                if (prev == 0) {
                    prev = i;
                }else {
                    int min = prev;
                    int max = i;
                    int diff = max - min;
                    if (diff < 0) {
                        System.out.println(0);
                        break;
                    }
                    result = result * (diff + 1) % MOD;
                    prev = i;
                }
            }else {
                count++;
            }
        }
        System.out.println(result);
    }
    public static void main(String[] args) {
        behaviorWeight();
    }
}

package bishi.meituan;

import java.util.*;

public class t1 {
    /**
     * 11.
     * 行走
     */
    public static void walk() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            long sum = 0; // 使用 long 以防溢出
            for (int i = 0; i < n; i++) {
                long k = sc.nextLong(); // 使用 long 以防溢出
                sum += k;
            }
            long x = sc.nextLong();
            long y = sc.nextLong();
            long p = sc.nextLong();
            long q = sc.nextLong();

            long distance = Math.abs(x - p) + Math.abs(y - q);
            long remaining_steps = sum - distance;

            if (remaining_steps < 0 || remaining_steps % 2 != 0) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }

    /**
     * 12.
     * 小美的陡峭值操作
     */
    public static void steepest_descent() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            long originalSteepestValue = calculateSteepestValue(a);
            int index = -1;
            // 从左到右检查递减位置
            for (int i = 1; i < n; i++) {
                if (a[i] < a[i - 1]) {
                    originalSteepestValue--;
                    index = i;
                    break; // 找到一个递减位置后就可以停止
                }
            }

            // 从右到左检查递减位置
            for (int i = n - 2; i >= 0; i--) {
                if (a[i] < a[i + 1]) {
                    if (i >= index) originalSteepestValue--;
                    break; // 找到一个递减位置后就可以停止
                }
            }

            System.out.println(originalSteepestValue);
        }
    }
    private static long calculateSteepestValue(int[] a) {
        long steepestValue = 0;
        for (int i = 1; i < a.length; i++) {
            steepestValue += Math.abs(a[i] - a[i - 1]);
        }
        return steepestValue;
    }

    /**
     * 13.
     * 我们惺惺相惜
     */
    public static void mutual_appreciation() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }

            //left[j] 表示 j 左边第一个 ≥ a[j] 的元素的索引
            int[] left = new int[n];
            Arrays.fill(left, -1);
            Deque<Integer> stack = new ArrayDeque<>();
            for (int j = 0; j < n; j++) {
                while (!stack.isEmpty() && arr[stack.peek()] < arr[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    left[j] = stack.peek();
                }
                stack.push(j);
            }
            stack.clear();

            //right[j] 表示 j 右边第一个 ≤ a[j] 的元素的索引
            int[] right = new int[n];
            Arrays.fill(right, -1);
            for (int j = n - 1; j >= 0; j--) {
                while (!stack.isEmpty() && arr[stack.peek()] > arr[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    right[j] = stack.peek();
                }
                stack.push(j);
            }
            //如果 left[j] 和 right[j] 都存在，则记录 (left[j], right[j])
            List<int[]> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (left[j] != -1 && right[j] != -1) {
                    list.add(new int[] {left[j], right[j]});
                }
            }
            //按照left[j]的大小进行排序
            //由于题目最大时间为4MS，直接遍历是否符合好区间即可
            //如果时间复杂度要求更高，可以用二分优化
            list.sort((o1, o2) -> o1[0] - o2[0]);
            for (int j = 0; j < q; j++) {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                boolean falg = true;
                for (int k = 0; k < list.size(); k++) {
                    if (list.get(k)[0] >= l && list.get(k)[1] <= r) {
                        falg = false;
                        break;
                    }
                }
                System.out.println(falg ? "YES" : "NO");
            }
        }
    }
    public static void main(String[] args) {
        mutual_appreciation();
    }
}

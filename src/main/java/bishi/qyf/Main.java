package bishi.qyf;

import java.util.*;

public class Main {
    public static void perfectPosition() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 1) {
            System.out.println(0);
            return;
        }
        long[][] positions = new long[n][2];
        for (int i = 0; i < n; i++) {
            positions[i][0] = sc.nextLong();
            positions[i][1] = sc.nextLong();
        }

        // 找到所有区间的最小右端点和最大左端点
        long leftMax = Long.MIN_VALUE;
        long rightMin = Long.MAX_VALUE;

        for (long[] position : positions) {
            leftMax = Math.max(leftMax, position[0]);
            rightMin = Math.min(rightMin, position[1]);
        }

        // 如果左右不重叠，返回0
        if (leftMax > rightMin) {
            System.out.println(0);
            return;
        }

        // 计算所有区间到最佳位置的总距离
        long totalDis = 0;
        long location = (leftMax + rightMin) / 2; // 中间位置
        for (long[] position : positions) {
            if (position[1] < location) {
                totalDis += location - position[1];
            } else if (position[0] > location) {
                totalDis += position[0] - location;
            }
        }
        System.out.println(totalDis);
    }

    public static void main(String[] args) {
        perfectPosition();
    }
}
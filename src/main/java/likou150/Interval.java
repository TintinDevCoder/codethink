package likou150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Interval {
    /**
     * 228.汇总区间
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuilder sb = new StringBuilder();
            sb.append(nums[low]);
            if (low < high) {
                sb.append("->").append(nums[high]);
            }
            result.add(sb.toString());
        }
        return result;
    }

    /**
     * 56.合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o2[1];
        });
        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            }else {
                list.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        list.add(new int[]{start, end});
        return list.toArray(new int[0][]);
    }

    /**
     * 57.插入区间
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int i = 0, n = intervals.length;

        // 1. 添加所有在新区间之前的区间
        while (i < n && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i++]);
        }

        // 2. 合并重叠的区间
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        list.add(newInterval); // 添加合并后的新区间

        // 3. 添加剩余的区间
        while (i < n) {
            list.add(intervals[i++]);
        }

        return list.toArray(new int[list.size()][]);
    }

    /**
     * 452. 用最少数量的箭引爆气球
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1, o2) -> {
                if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o2[1], o1[1]);
        });
        int start = points[0][0], end = points[0][1];
        int i = 1;
        int result = 1;
        while (i < points.length) {
            if (points[i][0] <= end) {
                start = Math.max(start, points[i][0]);
                end = Math.min(end, points[i][1]);
            }else {
                result++;
                start = points[i][0];
                end = points[i][1];
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        Interval interval = new Interval();
        interval.findMinArrowShots(new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}});
    }
}

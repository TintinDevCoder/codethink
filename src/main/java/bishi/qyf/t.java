package bishi.qyf;

import java.util.*;

public class t {

    public static void shuzigame() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] cards = new int[n];
            for (int i = 0; i < n; i++) {
                cards[i] = sc.nextInt();
            }
            int[] remainderIndex = new int[m];
            for (int i = 0; i < m; i++) {
                remainderIndex[i] = -1;
            }
            remainderIndex[0] = 0;
            int pre= 0;
            boolean result = false;
            for (int i = 0; i < n; i++) {
                pre += cards[i];
                int re = pre % m;
                if (re < 0) {
                    re += m;
                }
                if (remainderIndex[re] != -1) {
                    result = true;
                    break;
                }else {
                    remainderIndex[re] = i + 1;
                }
            }
            System.out.println(result ? 1 : 0);
        }
    }
    static StringBuilder sctresult = new StringBuilder();
    static Map<String, Set<String>> sctmap = new HashMap<>();
    public static void createStackTree() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        String start = null;
        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            String[] split = line.split(",");
            if (start == null) start = split[0];
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < split.length - 1; j++) {
                s.append(split[j]);
                String string = s.toString();
                Set<String> orDefault = sctmap.getOrDefault(string, new TreeSet());
                orDefault.add(split[j + 1]);
                sctmap.put(string, orDefault);
            }
        }
        dfs(start, start);
        System.out.println(sctresult.toString());
    }
    public static void dfs(String now, String add) {
        sctresult.append(add);
        Set<String> strings = sctmap.get(now);
        if (strings == null || strings.isEmpty()) {
            return;
        }
        for (String string : strings) {
            sctresult.append("[");
            dfs(now + string, string);
            sctresult.append("]");
        }
    }
    public static void perfectPosition1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long result = Long.MAX_VALUE;
        long minLeft = Long.MAX_VALUE;
        long maxRight = Long.MIN_VALUE;
        long[] left = new long[n];
        long[] right = new long[n];
        for (int i = 0; i < n; i++) {
            left[i] = sc.nextLong();
            right[i] = sc.nextLong();
            minLeft = Math.min(minLeft, left[i]);
            maxRight = Math.max(maxRight, right[i]);
        }
        // 如果左右不重叠，返回0
        if (minLeft > maxRight) {
            System.out.println(0);
            return;
        }
        for (long i = minLeft; i <= maxRight; i++) {
            long now = 0;
            for (int j = 0; j < n; j++) {
                if (left[j] > i) {
                    now += left[j] - i;
                }else if (right[j] < i) now += i - right[j];
            }
            if (result > now) result = now;
        }
        System.out.println(result);
    }
    public static void perfectPosition2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 1) {
            System.out.println(0);
            return;
        }
        List<Long> points = new ArrayList<>();
        long[][] positions = new long[n][2];
        for (int i = 0; i < n; i++) {
            long left = sc.nextLong();
            long right = sc.nextLong();
            points.add(left);
            points.add(right);
            positions[i][0] = left;
            positions[i][1] = right;
        }
        Collections.sort(points);
        long location = points.get(points.size() / 2);
        long totalDis = 0;
        for (long[] position : positions) {
            if (position[1] < location) {
                totalDis += location - position[1];
            }else if (position[0] > location) totalDis += position[0] - location;
        }
        System.out.println(totalDis);
    }
    public static void main(String[] args) {
        perfectPosition1();
    }
}

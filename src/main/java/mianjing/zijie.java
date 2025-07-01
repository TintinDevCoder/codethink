package mianjing;

public class zijie {
    static class  Line {
        String ip;
        int start;
        int end;
        Line() {}
        Line(String ip, int start, int end) {
            this.ip = ip;
            this.start = start;
            this.end = end;
        }
    }
    public int findPeakOnlineCount(Line[] lines) {
        int len = lines.length;
        int maxSeconds = 86400; // 一天的秒数
        int[] diff = new int[maxSeconds + 2];
        for (Line line : lines) {
            diff[line.start]++;
            diff[line.end + 1]--;
        }
        int maxCount = 0;
        int currentNum = 0;
        int peekTime = 0;
        for (int i = 1; i <= maxSeconds; i++) {
            currentNum += diff[i];
            if (currentNum > maxCount) {
                maxCount = currentNum;
                peekTime = i;
            }
        }
        return peekTime;
    }

    public static void main(String[] args) {
        zijie z = new zijie();
        // 测试用例 1: 基本用例，多个用户
        Line[] logs1 = {
                new Line("192.168.1.1", 1, 5),
                new Line("192.168.1.2", 2, 6),
                new Line("192.168.1.3", 4, 8),
                new Line("192.168.1.4", 3, 7),
        };
        System.out.println("Test Case 1: " + z.findPeakOnlineCount(logs1)); // 应该返回 4

        // 测试用例 2: 无用户在线
        Line[] logs2 = {};
        System.out.println("Test Case 2: " + z.findPeakOnlineCount(logs2)); // 应该返回 0

        // 测试用例 3: 用户只在线一秒
        Line[] logs3 = {
                new Line("192.168.1.1", 5, 5),
                new Line("192.168.1.2", 5, 5),
        };
        System.out.println("Test Case 3: " + z.findPeakOnlineCount(logs3)); // 应该返回 5

        // 测试用例 4: 用户在线时间完全重叠
        Line[] logs4 = {
                new Line("192.168.1.1", 1, 10),
                new Line("192.168.1.2", 1, 10),
                new Line("192.168.1.3", 1, 10),
        };
        System.out.println("Test Case 4: " + z.findPeakOnlineCount(logs4)); // 应该返回 1

        // 测试用例 5: 用户在线时间不重叠
        Line[] logs5 = {
                new Line("192.168.1.1", 1, 2),
                new Line("192.168.1.2", 3, 4),
                new Line("192.168.1.3", 5, 6),
        };
        System.out.println("Test Case 5: " + z.findPeakOnlineCount(logs5)); // 应该返回 1

        // 测试用例 6: 用户在线时间相邻
        Line[] logs6 = {
                new Line("192.168.1.1", 1, 2),
                new Line("192.168.1.2", 2, 3),
                new Line("192.168.1.3", 3, 4),
        };
        System.out.println("Test Case 6: " + z.findPeakOnlineCount(logs6)); // 应该返回 2
    }

}

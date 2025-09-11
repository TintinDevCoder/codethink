package test;

public class tt {
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int nowJump = nums[0];
        int maxJump = nums[0];
        int step = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nowJump < i) {
                nowJump = maxJump;
                step++;
            }
            if (maxJump < nums[i] + i) {
                maxJump = nums[i] + i;
            }
            if (nowJump >= nums.length - 1) {
                return step;
            }
        }
        return step;
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int cur = 0;
        int total = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            cur += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (cur < 0) {
                start = i + 1;
                cur = 0;
            }
        }
        return total < 0 ? -1 : start;
    }
    public String longestCommonPrefix(String[] strs) {
        int maxLen = strs[0].length();
        for (String str : strs) {
            maxLen = Math.min(maxLen, str.length());
        }
        if (maxLen == 0) return "";
        for (int i = 1; i <= maxLen; i++) {
            String pre = strs[0].substring(0, i);
            for (int j = 1; j < strs.length; j++) {
                if (!strs[j].substring(0, i).equals(pre)) {
                    return strs[0].substring(0, i - 1);
                }
            }
        }
        return strs[0].substring(0, maxLen);
    }
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ABC");
            }
        });
        int n = 3;
        while (n-- != 0) {
            thread.run();
        }

    }
}

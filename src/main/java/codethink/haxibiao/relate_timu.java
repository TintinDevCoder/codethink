package codethink.haxibiao;

import codethink.lianbiao.ListNode;

import java.util.HashSet;

public class relate_timu {
    /*第202题. 快乐数https://leetcode.cn/problems/happy-number/description/*/
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<Integer>();
        int sum = n;
        while (!set.contains(sum)) {
            set.add(sum);
            int wei = 0;
            int now = 0;
            while (sum != 0) {
                wei = sum % 10;
                sum = sum / 10;
                now += wei * wei;
            }
            if (now == 1) return true;
            sum = now;
        }
        return false;
    }
    public static void main(String[] args) {
        relate_timu s = new relate_timu();
        s.isHappy(19);
    }

}

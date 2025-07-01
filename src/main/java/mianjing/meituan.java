package mianjing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class meituan {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public ListNode reverseList(ListNode head) {
        ListNode pre = head, back = head.next;
        pre.next = null;
        ListNode temp;
        while (back != null) {
            temp = back.next;
            back.next = pre;
            pre = back;
            back = temp;
        }
        return pre;
    }
    public void reorderList(ListNode head) {
        ListNode slow = new ListNode(-1, head);
        ListNode fast = slow;
        int len = 0;
        while (fast != null) {
            len++;
            fast = fast.next;
            slow = slow.next;
            if (fast != null) {
                len++;
                fast = fast.next;
            }
        }
        if (len == 0) return;
        ListNode n2 = reverseList(slow);
        ListNode n1 = head;
        boolean n = true;
        len--;
        while (len-- != 0 && n1 != n2) {
            if (n) {
                ListNode temp = n1.next;
                n1.next = n2;
                n1 = temp;
            }else {
                ListNode temp = n2.next;
                n2.next = n1;
                n2 = temp;
            }
            n = !n;
        }
    }
    public int lengthOfLongestSubstring(String s) {
        int[] target = new int[128];
        char[] charArray = s.toCharArray();
        Arrays.fill(target, -1);
        int result = 0;
        int left = 0;
        for (int i = 0; i < charArray.length; i++) {
            left = Math.max(left, target[charArray[i]] + 1);
            result = Math.max(result, i - left);
            target[charArray[i]] = i;
        }
        return result;
    }

    public static void main(String[] args) throws ParseException {
        String manufactureDate = "Fri Jun 13 00:00:00 CST 2025";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        LocalDate date = LocalDate.parse(manufactureDate, formatter);
        System.out.println("Parsed date: " + date); // 输出：2025-06-13
    }
}

package mianjing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class baidu {
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

    public boolean isPalindrome1(ListNode head) {
        ListNode h = head;
        int size = 0;
        while (h != null) {
            size++;
            h = h.next;
        }
        int[] list = new int[size];
        h = head;
        int s = 0;
        while (h != null) {
            list[s++] = h.val;
            h = h.next;
        }
        for (int i = 0; i < size / 2; i++) {
            if (list[i] != list[size - i - 1]) return false;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        int num = 0;
        ListNode slow = head, fast = head;
        while (fast != null) {
            num++;
            fast = fast.next;
            slow = slow.next;
            if (fast != null) {
                num++;
                fast = fast.next;
            }
        }
        ListNode node2 = reverseList(slow);
        ListNode node1 = head;
        while (node1 != null && node2 != null) {
            if (node1.val != node2.val) return false;
            node1 = node1.next;
            node2 = node2.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head.next;
        slow.next = null;
        while (fast != null) {
            ListNode temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        return slow;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;
        ListNode h = head;
        int n = 0;
        ListNode first = head;
        ListNode pre = null;
        boolean f = true;
        while (h != null) {
            n++;
            if (n == k) {
                ListNode next = h.next;
                reverseList2(first, h);
                if (pre != null) {
                    pre.next = h;
                }
                if (f) {
                    f = false;
                    head = h;
                }
                first.next = next;
                n = 0;
                h = first.next;
                pre = first;
                first = first.next;
            } else h = h.next;
        }
        return head;
    }

    public ListNode reverseList2(ListNode first, ListNode end) {
        if (first == null) return null;
        ListNode slow = first, fast = first.next;
        slow.next = null;
        while (fast != null) {
            ListNode temp = fast.next;
            fast.next = slow;
            slow = fast;
            if (fast == end) return slow;
            fast = temp;
        }
        return slow;
    }


    public static void main(String[] args) {


    }
}

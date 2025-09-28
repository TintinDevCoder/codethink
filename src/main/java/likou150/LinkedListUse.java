package likou150;

import codethink.lianbiao.ListNode;

import java.util.HashMap;
import java.util.Map;

public class LinkedListUse {
    /**
     * 141. 环形链表
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head.next;
        while (fast != null) {
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next;
            if (fast == null) return false;
            fast = fast.next;
        }
        return false;
    }

    /**
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode H = new ListNode(-1);
        ListNode head = H;
        int t = 0;
        while (l1 != null || l2 != null) {
            ListNode temp = new ListNode(0);
            if (l1 != null) {
                temp.val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                temp.val += l2.val;
                l2 = l2.next;
            }
            temp.val += t;
            t = temp.val / 10;
            temp.val %= 10;
            head.next = temp;
            head = temp;
        }
        if (t != 0) head.next = new ListNode(t);
        return H.next;
    }

    /**
     * 21. 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode H = new ListNode(-1);
        int t = 0;
        ListNode pre = null;
        while (list1 != null || list2 != null) {
            ListNode temp;
            if (list1 == null || (list2 != null && list1.val >= list2.val)) {
                if (H.next == null) {
                    H.next = list2;
                    pre = list2;
                }else {
                    pre.next = list2;
                    pre = list2;
                }
                list2 = list2.next;
            }else {
                if (H.next == null) {
                    H.next = list1;
                    pre = list1;
                }else {
                    pre.next = list1;
                    pre = list1;
                }
                list1 = list1.next;
            }
        }
        return H.next;
    }
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node HEAD = new Node(head.val);
        Map<Node, Node> map = new HashMap<>();
        map.put(head, HEAD);
        Node node = HEAD;
        while (head != null) {
            if (head.random != null) {
                Node random = map.getOrDefault(head.random, new Node(head.random.val));
                map.put(head.random, random);
                node.random = random;
            }

            if (head.next != null) {
                Node next = map.getOrDefault(head.next, new Node(head.next.val));
                map.put(head.next, next);
                node.next = next;
            }
            node = node.next;
            head = head.next;
        }

        return HEAD;
    }

    /**
     * 92. 反转链表 II
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;
        int i = 2;
        ListNode slow = head, fast = head.next;
        while (fast != null && i++ <= left - 1) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode node = null;
        if (left == 1) {
            node = reverseList(slow, right - left);
            return node;
        }else {
            node = reverseList(fast, right - left);
            slow.next = node;
        }
        return head;
    }
    public ListNode reverseList(ListNode head, int num) {
        ListNode slow = head, fast = head.next;
        ListNode temp = fast;
        while (fast != null && num-- != 0) {
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        head.next = temp;
        return slow;
    }
    public ListNode reverseList(ListNode head, ListNode tail) {
        ListNode slow = head, fast = head.next;
        ListNode temp;
        while (fast != null) {
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
            if (slow == tail) return slow;
        }
        return slow;
    }

    /**
     * 25. K 个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;
        int i = 1;
        ListNode start = head, end = head;
        ListNode HEAD = null;
        ListNode pre = null;
        while (end != null) {
            if (i == k) {
                ListNode temp = end.next;
                reverseList(start, end);
                if (HEAD == null) {
                    HEAD = end;
                }
                if (pre != null) {
                    pre.next = end;
                }
                i = 1;
                pre = start;
                start.next = temp;
                start = temp;
                end = temp;
            }else {
                i++;
                end = end.next;
            }
        }
        return HEAD;
    }

    public static void main(String[] args) {
        LinkedListUse ll = new LinkedListUse();

        ll.reverseKGroup(new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
    }

}

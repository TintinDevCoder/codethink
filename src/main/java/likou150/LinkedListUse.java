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
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode H = new ListNode(-1, head);
        ListNode fast = H;
        while (n-- != 0) fast = fast.next;
        ListNode slow = H;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return H.next;
    }

/*    public ListNode deleteDuplicates(ListNode head) {

    }*/

    public static void main(String[] args) {
        LinkedListUse ll = new LinkedListUse();
        Node n1 = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);
        n1.next = n2;
        n2.next = n3;
        n2.random = n1;
        n3.next = n4;
        n3.random = n5;
        n4.next = n5;
        n4.random = n3;
        n5.random = n1;
        ll.copyRandomList(n1);
    }

}

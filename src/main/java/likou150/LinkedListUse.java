package likou150;

import codethink.lianbiao.ListNode;

import java.util.*;

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

    /**
     * 82. 删除排序链表中的重复元素 II
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode H = new ListNode(-1, head);
        ListNode slow = H;
        ListNode fast = H.next;
        int val = -200;
        while (fast != null) {
            if (val == fast.val) {
                slow.next = fast.next;
                fast = fast.next;
            }
            else if (fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;
                val = fast.val;
            }else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return H.next;
    }

    /**
     * 61. 旋转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) return head;
        ListNode node = head;
        int num = 0;
        while (node != null) {
            num++;
            node = node.next;
        }
        k = k % num;
        ListNode H = new ListNode(-1, head);;
        if (k == 0) return head;
        else {
            node = H;
            while (k-- != 0) {
                node = node.next;
            }
            ListNode slow = H;
            while (node.next != null) {
                slow = slow.next;
                node = node.next;
            }
            node.next = H.next;
            H.next = slow.next;
            slow.next = null;
        }
        return H.next;
    }

    /**
     * 86. 分隔链表
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode H = new ListNode(-1, head);
        ListNode slow = H;
        while (slow.next != null && slow.next.val < x) {
            slow = slow.next;
        }
        ListNode fast = slow.next;
        ListNode pre = slow;
        while (fast != null) {
            if (fast.val < x) {
                pre.next = fast.next;
                fast.next = slow.next;
                slow.next = fast;
                slow = slow.next;
                fast = pre.next;
            }else {
                fast = fast.next;
                pre = pre.next;
            }
        }
        return H.next;
    }

    public static void main(String[] args) {
        LinkedListUse ll = new LinkedListUse();
    }

}

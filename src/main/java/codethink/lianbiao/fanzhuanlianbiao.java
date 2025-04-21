package codethink.lianbiao;

public class fanzhuanlianbiao {

    /*206.反转链表https://leetcode.cn/problems/reverse-linked-list/*/
    public ListNode reverseList(ListNode head) {
        if(head == null) return head;
        ListNode prev = head;
        ListNode curr = head.next;
        prev.next = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
    /*24. 两两交换链表中的节点https://leetcode.cn/problems/swap-nodes-in-pairs/*/
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode prev = head;
        ListNode curr = head.next;
        ListNode newHead = new ListNode(-1, curr);
        ListNode temp = newHead;
        while (prev != null && curr != null) {
            prev.next = curr.next;
            curr.next = prev;
            temp.next = curr;
            temp = prev;
            prev = prev.next;
            if (prev == null) break;
            curr = prev.next;
        }
        return newHead.next;
    }


}

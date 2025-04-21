package codethink.lianbiao;

public class relate_timu {
    /* 19.删除链表的倒数第N个节点https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode H = new ListNode(-1, head);
        ListNode fast = head;
        ListNode slow = H;
        n--;
        while (n-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return H.next;
    }
    /*160.链表相交https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lena = 0, lenb = 0;
        ListNode countA = headA, countB = headB;
        while (countA != null) {
            lena++;
            countA = countA.next;
        }
        while (countB != null) {
            lenb++;
            countB = countB.next;
        }
        if (lena == 0 || lenb == 0) return null;
        int len = Math.abs(lena - lenb);
        countA = headA;
        countB = headB;
        if (lena > lenb) {
            while (len-- != 0) {
                countA = countA.next;
            }
        }else if (lena < lenb){
            while (len-- != 0) {
                countB = countB.next;
            }
        }
        while (countA != null) {
            if (countA == countB) return countA;
            countA = countA.next;
            countB = countB.next;
        }
        return null;
    }
    /*142. 环形链表 IIhttps://leetcode.cn/problems/linked-list-cycle-ii/description/*/
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode H = new ListNode(-1, head);
        ListNode fast = H, slow = H;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        if (fast == null || fast.next == null) return null;
        ListNode temp = H;
        while (temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }
        return temp;
    }
    public static void main(String[] args) {
        System.out.println((int)'z');
    }

}

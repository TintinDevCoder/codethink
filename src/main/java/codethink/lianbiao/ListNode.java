package codethink.lianbiao;

//链表节点定义
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    public ListNode removeElements(ListNode head, int val) {
        ListNode H = new ListNode(-1, head);
        ListNode slow = H;
        ListNode fast = head;
        while (fast != null) {
            if (fast.val == val) {
                slow.next = fast.next;
                fast = slow.next;
            }else {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return H.next;
    }

    public static void main(String[] args) {

    }
}

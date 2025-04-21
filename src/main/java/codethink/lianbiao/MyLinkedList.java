package codethink.lianbiao;

public class MyLinkedList {
    class LinkList {
        int val;
        LinkList pre, next;
        LinkList(int x, LinkList p, LinkList n) {
            val = x;
            pre = p;
            next = n;
        }
    }
    LinkList head;
    int size = 0;
    public MyLinkedList() {
        head = new LinkList(-1, null, null);
    }

    public int get(int index) {
        if (size <= index || index < 0) return -1;
        LinkList H = head.next;
        while (index-- != 0) H = H.next;
        return H.val;
    }

    public void addAtHead(int val) {
        LinkList H = new LinkList(val, head, head.next);
        if (head.next != null) head.next.pre = H;
        head.next = H;
        size++;
    }

    public void addAtTail(int val) {
        LinkList fast = head.next;
        LinkList slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        LinkList H = new LinkList(val, slow, null);
        slow.next = H;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (size < index) return ;
        if (index < 0) index = 0;
        LinkList fast = head;
        while (index-- != 0) fast = fast.next;
        LinkList H = new LinkList(val, fast, fast.next);
        if (fast.next != null) fast.next.pre = H;
        fast.next = H;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (size <= index || index < 0) return ;
        LinkList slow = head;
        while (index-- != 0) slow = slow.next;
        LinkList fast = slow.next;
        slow.next = fast.next;
        if (fast.next != null) fast.next.pre = slow;
        size--;
    }


}

package hot100;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class DLinkedNode {
        int key;
        int val;
        DLinkedNode pre;
        DLinkedNode next;
        DLinkedNode(){}
        DLinkedNode(int k, int v, DLinkedNode p, DLinkedNode n) {
            this.pre = p;
            this.next = n;
            this.key = k;
            this.val = v;
        }
    }
    int capacity;
    int size;
    DLinkedNode head, tail;
    Map<Integer, DLinkedNode> cache;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        cache = new HashMap<>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedNode dLinkedNode = cache.get(key);
        if (dLinkedNode == null) return -1;
        moveToHead(dLinkedNode);
        return dLinkedNode.val;
    }

    public void put(int key, int value) {
        DLinkedNode dLinkedNode = cache.get(key);
        if (dLinkedNode == null) {
            dLinkedNode = new DLinkedNode(key, value, head, head.next);
            head.next.pre = dLinkedNode;
            head.next = dLinkedNode;
            cache.put(key, dLinkedNode);
            size++;
            if (size > capacity) {
                DLinkedNode r = tail.pre;
                removeNode(r);
                cache.remove(r.key);
                size--;
            }
        }else {
            dLinkedNode.val = value;
            moveToHead(dLinkedNode);
        }
    }

    public void moveToHead(DLinkedNode node) {
        removeNode(node);
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    public void removeNode(DLinkedNode node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }
}

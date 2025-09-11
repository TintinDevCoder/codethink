package likou150;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class DLinkedNode {
        public DLinkedNode pre, next;
        public int key, val;
        DLinkedNode() {}
        DLinkedNode(DLinkedNode p, DLinkedNode n, int k, int v) {
            this.pre = p;
            this.next = n;
            this.key = k;
            this.val = v;
        }
    }
    DLinkedNode head, tail;
    int size;
    Map<Integer, DLinkedNode> map;
    public LRUCache(int capacity) {
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
        this.size = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        DLinkedNode node = map.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if (node != null) {
            node.val = value;
            moveToHead(node);
            return;
        }
        if (map.size() >= size) {
            map.remove(tail.pre.key);
            removeNode(tail.pre);
        }
        node = new DLinkedNode(head, head.next, key, value);
        moveToHead(node);
        map.put(key, node);
    }
    public void moveToHead(DLinkedNode node) {
        removeNode(node);
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }
    public void removeNode(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        // Step 2: Execute operations
        lruCache.put(1, 1); // Cache is {1=1}
        lruCache.put(2, 2); // Cache is {1=1, 2=2}
        System.out.println(lruCache.get(1)); // Returns 1
        lruCache.put(3, 3); // Evicts key 2, Cache is {1=1, 3=3}
        System.out.println(lruCache.get(2)); // Returns -1 (not found)
        lruCache.put(4, 4); // Evicts key 1, Cache is {4=4, 3=3}
        System.out.println(lruCache.get(1)); // Returns -1 (not found)
        System.out.println(lruCache.get(3)); // Returns 3
        System.out.println(lruCache.get(4)); // Returns 4
    }
}

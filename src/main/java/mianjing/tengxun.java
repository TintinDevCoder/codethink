package mianjing;

import java.util.HashMap;

public class tengxun {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
    }
}
class LRUCache {
    class Node {
        int key;
        int value;
        Node pre, next;
        Node(){}
        Node(int k, int v) {this.key = k;this.value = v;}
    }
    int capacity;
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        removeNode(node);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            removeNode(node);
            moveToHead(node);
        }else {
            if (map.size() >= capacity) {
                map.remove(tail.pre.key);
                removeNode(tail.pre);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            moveToHead(newNode);
        }
    }
    public void moveToHead(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }
    public void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}

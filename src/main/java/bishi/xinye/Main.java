package bishi.xinye;

import java.util.*;

public class Main {
    public static class LRUCache<K, V> {
        class Node {
            K key;
            V value;
            Node prev;
            Node next;
            public Node(){}
            public Node(K k, V v) {
                this.key = k;
                this.value = v;
            }
        }
        Map<K, Node> cache = new HashMap<>();
        int capacity;
        Node head, tail;
        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }
        public void put(K key, V value) {
            Node node = cache.get(key);
            if (node != null) {
                node.value = value;
                moveToHead(node);
                return;
            }
            if (cache.size() >= capacity) {
                cache.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            node = new Node(key, value);
            node.next = head.next;
            node.prev = head;
            moveToHead(node);
            cache.put(key, node);
        }
        public V get(K key) {
            Node node = cache.get(key);
            if (node == null) return null;
            moveToHead(node);
            return node.value;
        }
        public void moveToHead(Node node) {
            removeNode(node);
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
        public void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(3);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String action = sc.next();
            if ("put".equals(action)) {
                String key = sc.next();
                String value = sc.next();
                cache.put(key, value);
            } else if ("get".equals(action)) {
                String key = sc.next();
                String value = cache.get(key);
                System.out.print(value + ",");
            }
        }
        sc.close();
    }
}

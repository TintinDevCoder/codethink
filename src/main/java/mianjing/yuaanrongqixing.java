package mianjing;

import java.util.HashMap;
import java.util.Map;

public class yuaanrongqixing {
    /**
     * 154. 寻找旋转排序数组中的最小值 II
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }else if (nums[mid] < nums[right]) {
                right = mid;
            }else {
                right--;
            }
        }
        return nums[left >= nums.length ? 0 : left];
    }




    static class LRUCache {
        class Node {
            int key, value;
            Node pre, next;
            public Node(){}
            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.pre = null;
                this.next = null;
            }
        }
        Node head, tail;
        Map<Integer, Node> cache;
        int capacity;
        public LRUCache(int capacity) {
            cache = new HashMap<>();
            head = new Node();
            tail = new Node();
            this.capacity = capacity;
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node != null) {
                remove(node);
                moveToHead(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if (node != null) {
                node.value = value;
                remove(node);
            }else {
                if (cache.size() >= capacity) {
                    cache.remove(tail.pre.key);
                    remove(tail.pre);
                }
                node = new Node(key, value);
                cache.put(key, node);
            }
            moveToHead(node);
        }

        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        public void moveToHead(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }
    }
    public static void main(String[] args) {
        String str1 = "Hello";               // 常量池中的对象
        String str2 = new String("Hello");   // 新创建的堆对象

        // 比较 char 数组的地址
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        System.out.println("str1 char array 地址: " + System.identityHashCode(charArray1));
        System.out.println("str2 char array 地址: " + System.identityHashCode(charArray2));

        // 比较内容
        System.out.println("内容相同: " + str1.equals(str2)); // true

    }
}



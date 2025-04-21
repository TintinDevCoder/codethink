package codethink.zhanduilie;

import java.util.*;
import java.util.function.BiConsumer;

public class relate_timu {
    /*20. 有效的括号https://leetcode.cn/problems/valid-parentheses/description/*/
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) return false;
            }
        }
        return stack.isEmpty();
    }
    /*1047. 删除字符串中的所有相邻重复项https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/description/*/
    public String removeDuplicates1(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || stack.peek() != c) stack.push(c);
            else stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }
    public String removeDuplicates2(String s) {
        char[] chars = s.toCharArray();
        int top = -1;
        for (int i = 0; i < chars.length; i++) {
            if (top == -1 || chars[i] != chars[top]) {
                chars[++top] = chars[i];
            }else {
                top--;
            }
        }
        return String.valueOf(chars, 0, top + 1);
    }
    /*150. 逆波兰表达式求值https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/*/
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int t2 = stack.pop();
                int t1 = stack.pop();
                switch (token) {
                    case "+":stack.push(t1 + t2);break;
                    case "-":stack.push(t1 - t2);break;
                    case "*":stack.push(t1 * t2);break;
                    case "/":stack.push(t1 / t2);break;
                }
            }else {
                int t1 = Integer.parseInt(token);
                stack.push(t1);
            }
        }
        return stack.pop();
    }
    /*239. 滑动窗口最大值https://leetcode.cn/problems/sliding-window-maximum/description/*/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] result = new int[len - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.add(nums[i]);
        }
        for (int i = k; i < len; i++) {
            result[i - k] = deque.peek();
            if (!deque.isEmpty() && deque.peek() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.add(nums[i]);
        }
        result[len - k] = deque.peek();
        return result;
    }
    /*347.前 K 个高频元素https://leetcode.cn/problems/top-k-frequent-elements/description/*/
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((t1, t2) -> t1.getValue() - t2.getValue());
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            pq.offer(entry);
            if (pq.size() > k) pq.poll();
        }
        int[] keys = new int[k];
        for (int i = 0; i < k; i++) {
            keys[i] = pq.poll().getKey();
        }
        return keys;
    }

    public static void main(String[] args) {
        relate_timu test = new relate_timu();
        test.evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"});
    }
}

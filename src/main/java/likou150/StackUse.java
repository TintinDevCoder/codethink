package likou150;

import java.util.*;

public class StackUse {
    /**
     * 20. 有效的括号
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                Character pop = stack.pop();
                if (c == ')' && pop != '(') return false;
                else if (c == ']' && pop != '[') return false;
                else if (c == '}' && pop != '{') return false;
            }else stack.push(c);
        }
        return stack.isEmpty();
    }

    /**
     * 71. 简化路径
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        String[] splits = path.split("/");
        LinkedList<String> stack = new LinkedList();
        for (String split : splits) {
            if (!split.equals("")) {
                if (split.equals("..")) {
                    if (!stack.isEmpty()) stack.pop();
                }else if (!split.equals(".")) {
                    stack.push(split);
                }
            }
        }
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            String temp = stack.pollLast();
            s.append("/" + temp);
        }
        return s.length() == 0 ? "/" : s.toString();
    }

    /**
     * 150. 逆波兰表达式求值
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            }else if (token.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            }else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            }else if (token.equals("/")) {
                Integer t1 = stack.pop();
                Integer t2 = stack.pop();
                stack.push(t2 / t1);
            } else stack.push(Integer.valueOf(token));
        }
        return stack.pop();
    }

    /**
     * 224. 基本计算器
     * @param s
     * @return
     */
    public int calculate(String s) {
        char[] sCharArray = s.toCharArray();
        int sum = 0;
        Deque<Integer> deque = new LinkedList<>();
        deque.push(1);
        int seq = 1;
        int end = 0;
        int n = sCharArray.length;
        while (end < n) {
            if (sCharArray[end] == ' ') {

            } else if (sCharArray[end] == '+') {
                seq = deque.peek();
            }else if (sCharArray[end] == '-') {
                seq = -deque.peek();
            }else if (sCharArray[end] == '(') {
                deque.push(seq);
            }else if (sCharArray[end] == ')') {
                deque.pop();
            }else {
                long num = 0;
                while (end < n && Character.isDigit(s.charAt(end))) {
                    num = num * 10 + s.charAt(end) - '0';
                    end++;
                }
                sum += seq * num;
                continue;
            }
            end++;
        }
        return sum;
    }

    public static void main(String[] args) {
        StackUse su = new StackUse();
        su.calculate("1-(     -2)");
    }

    static class MinStack {
        class MSLinkedNode {
            int val;
            int min;
            MSLinkedNode pre;
            MSLinkedNode next;
            MSLinkedNode(){}
            MSLinkedNode(int v){this.val = v;}
            MSLinkedNode(int v, int m, MSLinkedNode p, MSLinkedNode n) {
                this.val = v;
                this.min = m;
                this.pre = p;
                this.next = n;
            }
        }
        MSLinkedNode cache;
        public MinStack() {
            cache = new MSLinkedNode(Integer.MAX_VALUE);
            cache.min = Integer.MAX_VALUE;
            cache.next = cache;
            cache.pre = cache;
        }

        public void push(int val) {
            int m = cache.next.min;
            MSLinkedNode node = new MSLinkedNode(val, Math.min(val, m), cache, cache.next);
            cache.next.pre = node;
            cache.next = node;
        }

        public void pop() {
            MSLinkedNode next = cache.next;
            next.next.pre = cache;
            cache.next = next.next;
        }

        public int top() {
            return cache.next.val;
        }

        public int getMin() {
            return cache.next.min;
        }
    }
}
package codethink.zhanduilie;

import java.util.Stack;

public class queuesWithStack {
    public static void main(String[] args) {
        Stack<Integer> temp = new Stack<>();
        temp.push(1);
        temp.push(2);
        temp.push(3);
        temp.remove(0);
        System.out.println(temp.get(0));
    }
}
/* 232.用栈实现队列https://leetcode.cn/problems/implement-queue-using-stacks/description/*/
class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;
    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    public int peek() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    public boolean empty() {
        return stackOut.isEmpty() && stackIn.isEmpty();
    }
}

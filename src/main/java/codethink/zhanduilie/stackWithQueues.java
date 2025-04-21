package codethink.zhanduilie;

import java.util.LinkedList;
import java.util.Queue;

public class stackWithQueues {
    public static void main(String[] args) {

    }

}
/*225. 用队列实现栈https://leetcode.cn/problems/implement-stack-using-queues/description/*/
class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }

    public void push(int x) {
        queue1.offer(x);
    }

    public int pop() {
        int size = queue1.size() - 1;
        while (size-- != 0) {
            queue2.offer(queue1.poll());
        }
        int result = queue1.poll();
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        return result;
    }

    public int top() {
        int size = queue1.size() - 1;
        while (size-- != 0) {
            queue2.offer(queue1.poll());
        }
        int result = queue1.poll();
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        queue1.offer(result);
        return result;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
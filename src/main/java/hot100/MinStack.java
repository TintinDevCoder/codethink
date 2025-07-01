package hot100;

class MinStack {
    class DLinkedNode {
        int val;
        int nowmin;
        DLinkedNode pre;
        DLinkedNode next;
        DLinkedNode(){}
        DLinkedNode(int v, int now, DLinkedNode p, DLinkedNode n){
            this.val = v;
            this.nowmin = now;
            this.pre = p;
            this.next = n;
        }
    }
    DLinkedNode head;
    DLinkedNode tail;
    public MinStack() {
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }
    
    public void push(int val) {
        DLinkedNode newnode;
        if (head.next == tail) {
            newnode = new DLinkedNode(val, val, head, tail);
        }else {
            int minn = Math.min(val, head.next.nowmin);
            newnode = new DLinkedNode(val, minn, head, tail);
        }
        newnode.next = head.next;
        newnode.pre = head;
        head.next = newnode;
    }
    
    public void pop() {
        if (head.next != tail) {
            head.next.next.pre = head;
            head.next = head.next.next;
        }
    }

    public int top() {
        return head.next.val;
    }

    public int getMin() {
        return head.next.nowmin;
    }

    public static void main(String[] args) {
        // 创建 MinStack 实例
        MinStack minStack = new MinStack();

        // 执行操作
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        // 获取当前最小值
        System.out.println("当前最小值: " + minStack.getMin()); // 输出: -3

        // 弹出栈顶元素
        minStack.pop();

        // 获取栈顶元素
        System.out.println("栈顶元素: " + minStack.top()); // 输出: 0

        // 获取当前最小值
        System.out.println("当前最小值: " + minStack.getMin()); // 输出: -2
    }
}


package bishi;

import java.util.Arrays;
import java.util.PriorityQueue;

public class t3 {
    public long minCost1 (int[] a) {
        long result = 0l;
        PriorityQueue<Long> minHeap = new PriorityQueue();
        for(long i : a) {
            minHeap.offer(i);
        }
        while(minHeap.size() > 1) {
            long n1 = minHeap.poll();
            long n2 = minHeap.poll();
            long n3 = n1 + n2;
            result += n3;
            minHeap.offer(n3);
        }
        return result;
        // write code here
    }
    public static void main(String[] args) {
        t3 t = new t3();
        t.minCost1(new int[]{4,5,9,10});
    }
}

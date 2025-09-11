package bishi.meituan;

import java.util.*;

public class meituan {
    public static void method1() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- != 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            int orSum = 0;
            int maxSum = 0;
            for (int num : a) {
                orSum |= num;
                maxSum = Math.max(maxSum, num);
            }
            int maxBit = maxSum == 0 ? 1 : Integer.toBinaryString(maxSum).length();
            int z = 0;
            for (int k = 0; k < maxBit; k++) {
                int maxk = 1 << k;
                int tempOr = orSum;
                for (int num : a) {
                    tempOr |= (num & -maxk);
                }
                if (tempOr == orSum) {
                    continue;
                }else {
                    z |= maxk;
                }
            }
            System.out.println(orSum + " " + z);
        }
    }
    public static void method2() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            int n = sc.nextInt();
            Map<Long, Integer> map1 = new HashMap<>();
            for (int i = 0; i < n; i++) {
                long temp = sc.nextLong();
                map1.put(temp, map1.getOrDefault(temp, 0) + 1);
            }
            PriorityQueue<Long> A = new PriorityQueue<>(new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return (int) (o2 - o1);
                }
            });
            PriorityQueue<Long> B = new PriorityQueue<>(new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return (int) (o2 - o1);
                }
            });
            for (int i = 0; i < n; i++) {
                long temp = sc.nextLong();
                Integer orDefault = map1.getOrDefault(temp, 0);
                if (orDefault == 0) {
                    B.add(temp);
                }else {
                    map1.put(temp, map1.get(temp) - 1);
                }
            }
            for (Map.Entry<Long, Integer> entry : map1.entrySet()) {
                Long key = entry.getKey();
                Integer value = entry.getValue();
                while (value-- != 0) A.add(key);
            }
            int count = 0;
            while (!A.isEmpty() && !B.isEmpty()) {
                count++;
                if (A.peek() > B.peek()) {
                    Long l = A.poll();
                    l = (long) Long.bitCount(l);
                    if (B.contains(l)) {
                        B.remove(l);
                    }else {
                        A.add(l);
                    }
                }else {
                    Long l = B.poll();
                    l = (long) Long.bitCount(l);
                    if (A.contains(l)) {
                        A.remove(l);
                    }else {
                        B.add(l);
                    }
                }
            }
            System.out.println(count);
        }
    }
    public static void method3() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int right = i + 2; right < n; right++){
                for (int left = i + 1; left < n - 1; left++) {
                    if (a[i] > a[right] && a[right] > a[left]) result++;
                }
            }
        }
        System.out.println(result);
    }
    public static void main(String[] args) {
        method3();
    }

}

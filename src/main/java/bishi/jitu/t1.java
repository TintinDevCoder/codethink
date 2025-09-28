package bishi.jitu;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class t1 {
    public static void xiaohongQueen() {
        Scanner sc = new Scanner(System.in);
        String line = null;
        int[] hang = new int[8];
        int[] lie = new int[8];
        int lieposition = 0;
        int hangposition = 0;
        for (int i = 0; i < 8; i++) {
            line = sc.nextLine();
            for (int j = 0; j < 8; j++) {
                if (line.charAt(j) == '*') {
                    hang[i]++;
                    lie[j]++;
                    if (lie[j] == 8) {
                        lieposition = j + 1;
                    }
                }
            }
            if (hang[i] == 8) {
                hangposition = i + 1;
            }
        }
        System.out.println(hangposition + " " + lieposition);
    }
    public static void xiaogongJSQ() {
        Scanner sc = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        String line = sc.nextLine();
        char[] charArray = line.toCharArray();

        for (int i = 2; i < charArray.length; i++) {
            int n = (charArray[i] - '0');
            while (!deque.isEmpty() && deque.getLast() < n) deque.removeLast();
            deque.addLast(n);
        }
        System.out.print("0.");
        while (!deque.isEmpty()) {
            System.out.print(deque.getFirst());
            deque.removeFirst();
        }
    }
    public static int reverseInt(int n) {
        int result = 0;
        while (n != 0) {
            result = result * 10 + n % 10;
            n /= 10;
        }
        return result;
    }
    public static void reverseCFB() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int result = 0;
        for (int i = 1; i <= K; i++) {
            int n = N * i;
            n = reverseInt(n);
            result = Math.max(result, n);
        }
        System.out.println(result);
    }
    public static void main(String[] args) {
        reverseCFB();
    }
}

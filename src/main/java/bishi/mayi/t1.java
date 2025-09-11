package bishi.mayi;

import java.util.*;

public class t1 {
    public static void buy() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        double[] a = new double[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                a[i] *= 0.95;
            }
        }
        Arrays.sort(a);
        int num = 0;
        double now = 0;
        for (int i = 0; i < a.length; i++) {
            if (now + a[i] <= k) {
                now += a[i];
                num++;
            }
        }
        System.out.println(num);
    }
    public static void xiaohongSubString() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] c = s.toCharArray();
        List<Character> yuanyin = new ArrayList<>();
        Collections.addAll(yuanyin, 'a', 'e', 'i', 'o', 'u');
        int[] yuanNum = new int[c.length + 1];
        int[] fuNum = new int[c.length + 1];
        if (yuanyin.contains(c[0])) {
            yuanNum[0] = 1;
        }else fuNum[0] = 1;
        for (int i = 1; i < c.length; i++) {
            yuanNum[i] = yuanNum[i - 1];
            fuNum[i] = fuNum[i - 1];
            if (yuanyin.contains(c[i])) {
                yuanNum[i]++;
            }else fuNum[i]++;
        }
        int result = 0;
        for (int i = 0; i < c.length - 1; i++) {
            int t1 = Math.abs(yuanNum[i] - fuNum[i]);
            int t2 = Math.abs((yuanNum[c.length - 1] - yuanNum[i]) - (fuNum[c.length - 1] - fuNum[i]));
            if (t1 == t2) {
                result++;
            }
        }
        System.out.println(result);
    }
    // 计算两个数的最大公因子
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    // 计算多个数的最大公因子
    public static int gcdMultiple(int[] numbers) {
        return Arrays.stream(numbers).reduce(numbers[0], t1::gcd);
    }
    // 判断一个数是否为素数
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
    public static boolean canGCD(int[] a, int target) {
        int needSub = 0, needAdd = 0, all = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= target) {
                if (a[i] % target != 0) {
                    int t1 = a[i] - a[i] / target * target;
                    int t2 = (a[i] / target + 1) * target - a[i];
                    if (t1 % 2 != 0 && t2 % 2 != 0) return false;
                    if (t1 % 2 == 0 && t2 % 2 == 0) all++;
                    else if (t1 % 2 == 0) needSub += t1 / 2;
                    else needAdd += t2 / 2;
                }
            }else {
                int t = target - a[i];
                if (t % 2 != 0) return false;
                needAdd += t / 2;
            }
        }
        return Math.abs(needAdd - needSub) == all;
    }
    // 使用埃拉托斯特尼筛法生成小于等于给定数字的所有素数
    public static List<Integer> generatePrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        List<Integer> primes = new ArrayList<>();
        // 初始化所有数为素数
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }
        // 筛选出非素数
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        // 收集所有素数
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
    public static void xiaobenGCD() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            max = Math.max(max, a[i]);
        }
        List<Integer> Primes = generatePrimes(max);
        int k = gcdMultiple(a);
        Set<Integer> result = new HashSet<>();
        if (isPrime(k)) {
            result.add(k);
        }
        for (Integer prime : Primes) {
            if (canGCD(a, prime)) {
                result.add(prime);
            }
        }
        if (!result.isEmpty()) {
            System.out.println("YES");
            Integer[] array = result.toArray(new Integer[0]);
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]);
                if (i != array.length - 1) System.out.print(" ");
            }
            System.out.println();
        }else System.out.println("NO");
    }
    public static void main(String[] args) {
        xiaobenGCD();
    }
}

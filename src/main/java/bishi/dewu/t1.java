package bishi.dewu;

import java.util.*;

public class t1 {
    static class card {
        int value;
        int nums;
        public card(int v, int n) {
            this.value = v;
            this.nums = n;
        }
    }
    public static void chouka() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long count = 1;
        long result = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int i1 = sc.nextInt();
            int i2 = sc.nextInt();
            if (i2 > 0) {
                result += i1;
                count += i2 - 1;
            }else {
                list.add(i1);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        int i = 0;
        while (i < list.size() && count != 0) {
            count--;
            result += list.get(i++);
        }
        System.out.println(result);
    }
    static class baoshi {
        int num;
        int value;
        int time;
        double ba;
        public baoshi(int n, int v, int t, double b) {
            this.num = n;
            this.value = v;
            this.time = t;
            this.ba = b;
        }
    }
    public static void wajuebaoshi() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        baoshi[] bs = new baoshi[N];
        for (int i = 0; i < N; i++) {
            int i1 = sc.nextInt();
            int i2 = sc.nextInt();
            int i3 = sc.nextInt();
            bs[i] = new baoshi(i1, i2, i3, 1.0 * i2 / i3);
        }
        Arrays.sort(bs, new Comparator<baoshi>() {
            @Override
            public int compare(baoshi o1, baoshi o2) {
                return Double.compare(o2.ba, o1.ba);
            }
        });
        int i = 0;
        int result = 0;
        while (T != 0 && i < N) {
            if (T >= bs[i].time) {
                int num = T / bs[i].time;
                if (num > bs[i].num) num = bs[i].num;
                result += num * bs[i].value;
                T -= num * bs[i].time;
            }
            i++;
        }
        System.out.println(result);
    }
    public static void wajuebaoshi2() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        int[] nums = new int[N];
        int[] values = new int[N];
        int[] times = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
            values[i] = sc.nextInt();
            times[i] = sc.nextInt();
        }

        int[] dp = new int[T + 1];
        for (int i = 0; i < N; i++) {
            int num = nums[i];
            int value = values[i];
            int time = times[i];
            for (int j = T; j >= time; j--) {
                for (int k = 1; k <= num && k * time <= j; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * time] + k * value);
                }
            }
        }
        System.out.println(dp[T]);
    }

    public static void xiangsi() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            int n = sc.nextInt();
            sc.nextLine();
            String S = sc.nextLine();
            String T = sc.nextLine();
            char[] Sc = S.toCharArray();
            char[] Tc = T.toCharArray();
            int count = 0;
            for (int i = 0; i < Sc.length; i++) {
                if (Sc[i] != Tc[i]) {
                    count++;
                    if (count > 1) {
                        break;
                    }
                }
            }
            if (count <= 1) {
                System.out.println("Yes");
            }else {
                int i = 0, j = 0;
                char t1 = ' ';
                int num = 0;
                boolean is = true;
                while (i < n && j < n) {
                    if (Sc[i] == Tc[j]) {
                        i++;
                        j++;
                    }else {
                        if (num >= 2) {
                            is = false;
                            break;
                        }
                        if (t1 == ' ') {
                            if (Sc[i + 1] == Tc[j]) {
                                t1 = Sc[i];
                                i++;
                            }else if (Tc[j + 1] == Sc[i]) {
                                t1 = Tc[j];
                                j++;
                            }else {
                                is = false;
                                break;
                            }
                        }else {
                            if (t1 == Sc[i]) {
                                i++;
                            } else if (t1 == Tc[j]) {
                                j++;
                            }else {
                                is = false;
                                break;
                            }
                        }
                        num++;
                    }
                }
                //Aabaa
                //bAaaa
                if (Sc[0] != Tc[0] && Tc[0] != t1) {
                    is = false;
                }
                if (is) {
                    System.out.println("Yes");
                }else {
                    System.out.println("No");
                }
            }
        }
    }
    public static void main(String[] args) {
        xiangsi();
    }
}

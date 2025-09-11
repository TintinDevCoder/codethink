package bishi.tuyou;

import java.util.*;

public class t1 {
    public static void tupai() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T -- != 0) {
            int n = sc.nextInt();
            sc.nextLine();
            String input = sc.nextLine();
            String[] pais = input.split(" ");
            int bigsmall = 6;
            int sevenNum = 8;
            Map<Character, Integer> map = new TreeMap<>();
            map.put('C', 0);
            map.put('D', 1);
            map.put('H', 2);
            map.put('S', 3);

            int[][] tonghua = new int[4][5];
            for (String pai : pais) {
                if (pai.equals("1J") || pai.equals("2J")) {
                    bigsmall--;
                    continue;
                }
                char t1temp = pai.charAt(0);
                char t2 = pai.charAt(1);
                int t1 = 0;
                if (t1temp == 'A') {
                    t1 = 1;
                }else t1 = t1temp - '0';
                if (t1 >= 1 && t1 <= 4) {
                    int t3 = map.get(t2);
                    tonghua[t3][t1]++;
                }else if (t1 == 7) sevenNum--;
            }
            if (sevenNum + bigsmall >= 8) {
                System.out.println("YES");
                System.out.println(3270);
                continue;
            }
            boolean is = false;
            for (int[] ints : tonghua) {
                int queshi = 0;
                for (int anInt : ints) {
                    queshi += anInt;
                }
                if (bigsmall >= queshi) {
                    System.out.println("YES");
                    System.out.println(96);
                    is = true;
                    break;
                }
            }
            if (is) continue;
            System.out.println("NO");
        }
    }
    public static void qihang() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String input = sc.nextLine();
        int[] target = new int[n];
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '<') target[i] = -1;
            else target[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            int now = target[i];
            boolean is = false;
            for (int j = i + 1; j < n; j++) {
                now += target[j];
                if (now == 0) {
                    is = true;
                    break;
                }
            }
            if (!is) System.out.print(0);
            else System.out.print(1);
            if (i != n - 1) System.out.print(" ");
        }
    }
    static Map<Integer, List<int[]>> map;
    static long result = Long.MAX_VALUE;
    public static void liangcao() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            List<int[]> list = map.getOrDefault(u, new ArrayList<>());
            list.add(new int[]{v, w});
            map.put(u, list);
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
        boolean[] t = new boolean[n + 1];
        //false是卸货 true伪装或
        dfs(start, false, t, end, 0);
        dfs(start, true, t, end, 0);
        System.out.println(result == Long.MAX_VALUE ? -1 : result);
    }
    public static void dfs(int index, boolean status, boolean[] isVisited, int end, int now) {
        if (index == end) {
            result = Math.min(result, now);
            return;
        }
        List<int[]> list = map.get(index);
        if (list != null) {
            for (int[] ints : list) {
                if (!isVisited[ints[0]]) {
                    if (!status && ints[0] > index) {
                        isVisited[ints[0]] = true;
                        dfs(ints[0], true, isVisited, end, now + ints[1]);
                        isVisited[ints[0]] = false;
                    }else if (status && ints[0] < index) {
                        isVisited[ints[0]] = true;
                        dfs(ints[0], false, isVisited, end, now + ints[1]);
                        isVisited[ints[0]] = false;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        liangcao();
    }
}

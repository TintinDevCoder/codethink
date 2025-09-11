package bishi.xiaohongshu;

import java.util.*;

public class t1 {
    public static void tuijiansuanfa() {
        Stack<Integer> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        List<String> list = new ArrayList<>();
        sc.nextLine();
        String s = sc.nextLine();
        String[] split = s.split(" ");
        Collections.addAll(list, split);
        Map<Integer, List<String>> map = new HashMap<>();
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            s = sc.nextLine();
            String[] parts = s.split(" ");
            String word = parts[0]; // 字符串部分
            s = sc.nextLine();
            String[] keys = s.split(" ");
            int num = 0;
            for (String key : keys) {
                if (list.contains(key)) num++;
            }
            List<String> orDefault = map.getOrDefault(num, new LinkedList<>());
            orDefault.add(word);
            map.put(num, orDefault);
            if (!l.contains(num)) {
                l.add(num);
            }
        }
        Collections.sort(l, Collections.reverseOrder());
        for (Integer i : l) {
            List<String> strings = map.get(i);
            for (String string : strings) {
                System.out.println(string);
            }
        }
    }

    public static void main(String[] args) {
        tuijiansuanfa();
    }
}

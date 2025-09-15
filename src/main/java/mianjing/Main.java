package mianjing;

import java.util.*;

public class Main {
    static class Pet {
        long x;// 领养编号
        int a;// 第一种毛色
        int b;// 第二种毛色
        public Pet(long x, int a, int b) {
            this.x = x;
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] x = new long[n];//编号
        int[] a = new int[n];//第一种颜色
        int[] b = new int[n];//第二种颜色
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextLong();
        }
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] c = new int[m];
        for (int i = 0; i < m; i++) {
            c[i] = sc.nextInt();
        }
        //将宠物按照领养顺序排序
        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pets.add(new Pet(x[i], a[i], b[i]));
        }
        //按照编号升序排序
        pets.sort(Comparator.comparingLong(num -> num.x));
        //为每种毛色维护两个队列
        Queue<Long>[] color1 = new Queue[4];
        Queue<Long>[] color2 = new Queue[4];
        for (int i = 0; i < 4; i++) {
            color1[i] = new LinkedList<>();
            color2[i] = new LinkedList<>();
        }
        //记录毛色情况
        for (Pet pet : pets) {
            color1[pet.a].offer(pet.x);
            color2[pet.b].offer(pet.x);
        }
        Set<Long> adopted = new HashSet<>();//已领养编号
        List<Long> result = new ArrayList<>();//结果编号
        for (int color : c) {
            long adopteNum1 = -1;
            long adopteNum2 = -1;
            //检查第一种毛色
            while (!color1[color].isEmpty() && adopted.contains(color1[color].peek())) {
                color1[color].poll();
            }
            if (!color1[color].isEmpty()) {
                adopteNum1 = color1[color].peek();
            }
            //检查第二种毛色
            while (!color2[color].isEmpty() && adopted.contains(color2[color].peek())) {
                color2[color].poll();
            }
            if (!color2[color].isEmpty()) {
                adopteNum2 = color2[color].peek();
            }
            //找到可领养的宠物
            if (adopteNum1 != -1 && adopteNum2 != -1){ //两种毛色都有，比较编号，使用靠前的
                long minn = Math.min(adopteNum1, adopteNum2);
                adopted.add(minn);
                result.add(minn);
            }
            else if (adopteNum2 != -1) { //只有第一种毛色找到
                adopted.add(adopteNum2);
                result.add(adopteNum2);
            }else if (adopteNum1 != -1) { //只有第二种毛色找到
                adopted.add(adopteNum1);
                result.add(adopteNum1);
            }else { //都没有
                result.add(-1l);
            }
        }
        //输出结果
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(result.get(i));
        }
    }
}

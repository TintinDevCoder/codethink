package mianjing.jd;

import java.util.*;

public class jd {
    static class Pet {
        int x;// 领养编号
        int a;// 第一种毛色
        int b;// 第二种毛色
        public Pet(int x, int a, int b) {
            this.x = x;
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];//编号
        int[] a = new int[n];//第一种颜色
        int[] b = new int[n];//第二种颜色
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
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
        pets.sort(Comparator.comparingInt(num -> num.x));

        List<Integer> result = new ArrayList<>();//结果编号
        for (int color : c) {
            int adopteNum = -1;
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i).a == color || pets.get(i).b == color) {
                    adopteNum = pets.get(i).x;
                    pets.remove(i);
                    break;
                }
            }
            result.add(adopteNum);
        }
        //输出结果
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(result.get(i));
        }
    }
}

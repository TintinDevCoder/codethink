package bishi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class xiaomi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<testStruct> intervals = new ArrayList<>(); //区间集合
        for (int i = 0; i < n; i++) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            intervals.add(new testStruct(left, right));
        }
        System.out.println();
    }
}
class testStruct{
    int left;
    int right;
    public testStruct(int l, int r) {
        this.left = l;
        this.right = r;
    }
}
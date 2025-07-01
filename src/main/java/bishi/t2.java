package bishi;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class t2 {
    public static String process(String s) {
        char[] charArray = s.toCharArray();
        int[] nums = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            nums[i] = charArray[i] - 'a';
        }
        //判断是否可以对换两个元素后变为递增
        int n1 = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                n1 = i;
                while (n1 + 1 < nums.length && nums[n1 + 1] == nums[n1]) n1++;
            }
        }
        if (n1 == -1) {
            return "NO";
        }
        int maxx = -1;
        int n2 = -1;
        for (int i = n1 - 1; i >= 0; i--) {
            if (maxx < nums[i]) {
                maxx = nums[i];
                n2 = i;
            }
        }
        int temp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = temp;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                return "NO";
            }
        }
        return "YES";
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            in.nextLine();
            String s = in.nextLine();
            list.add(process(s));
        }
        for (String s : list) {
            System.out.println(s);
        }
    }
}

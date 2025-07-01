package bishi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class t1 {
    static List<Character> target = new ArrayList<>();
    public static int getHuiNum(char[] charArray, int i, int j) {
        int num = 0;
        while (i >= 0 && j < charArray.length && target.contains(charArray[i]) && target.contains(charArray[j])) {
            if (charArray[i] != charArray[j]) return num;
            if (j - i + 1 > 1) num++;
            i--;
            j++;
        }
        return num;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        target.addAll(Arrays.asList('A', 'H', 'I', 'M', 'O', 'T', 'U', 'V', 'W', 'X', 'Y'));
        char[] charArray = str.toCharArray();
        int i = 0;
        long result = 0;
        while (i < charArray.length) {
            int num1 = 0;
            if (target.contains(charArray[i])) {
                num1 = getHuiNum(charArray, i, i);
            }
            int num2 = 0;
            if (i + 1 < charArray.length && target.contains(charArray[i + 1]) && charArray[i] == charArray[i + 1]) {
                num2 = getHuiNum(charArray, i, i + 1);
            }
            num1 = Math.max(num1, num2);
            result+=num1;
            i++;
        }
        System.out.println(result);
    }
}

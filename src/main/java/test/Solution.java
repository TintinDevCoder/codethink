package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    static List<String> generateParenthesisres = new ArrayList<>();
    public static List<String> generateParenthesis(int n) {
        generateParenthesisDfs(0, 0, new StringBuilder(), n);
        return generateParenthesisres;
    }
    public static void generateParenthesisDfs(int leftn, int rightn, StringBuilder sb, int n) {
        if (leftn == rightn && leftn + rightn == 2 * n) {
            generateParenthesisres.add(sb.toString());
            return;
        }
        if (leftn + rightn >= 2 * n) return;
        if (rightn < n && leftn > rightn) {
            sb.append(')');
            generateParenthesisDfs(leftn, rightn + 1, sb, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (leftn < n) {
            sb.append("(");
            generateParenthesisDfs(leftn + 1, rightn, sb, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<String> strings = generateParenthesis(i);
        System.out.println(strings.toString());
    }
}
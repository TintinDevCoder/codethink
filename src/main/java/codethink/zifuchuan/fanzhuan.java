package codethink.zifuchuan;

import java.util.Scanner;

public class fanzhuan {
    /*344.反转字符串https://leetcode.cn/problems/reverse-string/submissions/586201173/*/
    public void reverseString(char[] s) {
        int len = s.length;
        for (int i = 0; i < len / 2; i++) {
            char temp = s[i];
            s[i] = s[len - i - 1];
            s[len - i - 1] = temp;
        }
    }
    /*541. 反转字符串IIhttps://leetcode.cn/problems/reverse-string-ii/description/*/
    public String reverseStr(String s, int k) {
        boolean is = true;
        char[] c = s.toCharArray();
        int len = c.length;
        if (len <= k) reverseString(c, 0, len);
        else {
            int i = 0;
            while (i < len) {
                if (i + k >= len) {
                    if (is)
                        reverseString(c, i, len - i);
                    break;
                }
                if (is) {
                    reverseString(c, i, k);
                    is = false;
                }else is = true;
                i += k;
            }
        }
        return new String(c);
    }
    public void reverseString(char[] s, int start, int k) {
        int end = start + k - 1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
    public void NumbertoString() {
        Scanner sc = new Scanner(System.in);
        char[] charArray = sc.nextLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        String number = "number";
        for (char c : charArray) {
            if (c >= '0' && c <= '9') {
                sb.append(number);
            }else sb.append(c);
        }
        System.out.println(sb);
    }
    /*151. 反转字符串中的单词https://leetcode.cn/problems/reverse-words-in-a-string/description/*/
    public String reverseWords1(String s) {
        s = s.trim();
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].length() == 0 || split[i].equals("")) continue;
            sb.append(split[i]);
            if (i != 0) sb.append(" ");
        }
        return sb.toString();
    }
    public String reverseWords2(String s) {
        StringBuilder sb = new StringBuilder();
        int end = s.length() - 1;
        int start = 0;
        while (s.charAt(end) == ' ') end--;
        int begin = 0;
        while (s.charAt(begin) == ' ') begin++;
        for (start = end - 1; start >= begin; start--) {
            if (s.charAt(start) == ' ' && s.charAt(start + 1) != ' ') {
                sb.append(s.substring(start + 1, end + 1) + ' ');
            }else if (s.charAt(start) != ' ' && s.charAt(start + 1) == ' ') {
                end = start;
            }
        }
        start++;
        sb.append(s.substring(start, end + 1));
        return sb.toString();
    }
    /*右旋字符串https://kamacoder.com/problempage.php?pid=1065*/
    public void Rightreverse() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] c = sc.next().toCharArray();
        reverse(c, 0, c.length - 1);
        reverse(c, 0, n - 1);
        reverse(c, n, c.length - 1);
        System.out.println(new String(c));
    }
    public void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        fanzhuan t = new fanzhuan();
        t.Rightreverse();
    }
}

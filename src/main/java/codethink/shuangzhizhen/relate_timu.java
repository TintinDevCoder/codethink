package codethink.shuangzhizhen;

import java.util.Scanner;

public class relate_timu {
    /*344.反转字符串https://leetcode.cn/problems/reverse-string/description/*/
    public void reverseString(char[] s) {
        int len = s.length;
        for (int i = 0; i < len / 2; i++) {
            char temp = s[i];
            s[i] = s[len - i - 1];
            s[len - i - 1] = temp;
        }
    }
    /*替换数字https://kamacoder.com/problempage.php?pid=1064*/
    public void replaceNumbers() {
        Scanner sc = new Scanner(System.in);
        char[] chars = sc.next().toCharArray();
        int len1 = chars.length;
        int num = 0;
        for (int i = 0; i < len1; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') num++;
        }
        int len2 = len1 + num * 5;
        char[] newchars = new char[len2];
        int index1 = len2 - 1;
        for (int i = len1 - 1; i >= 0; i--) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                newchars[index1--] = 'r';
                newchars[index1--] = 'e';
                newchars[index1--] = 'b';
                newchars[index1--] = 'm';
                newchars[index1--] = 'u';
                newchars[index1--] = 'n';
            }else newchars[index1--] = chars[i];
        }
        System.out.println(new String(newchars));
    }
    /*151.翻转字符串里的单词https://leetcode.cn/problems/reverse-words-in-a-string/description/*/
    public String reverseWords(String s) {
        return null;
    }
    public static void main(String[] args) {
        relate_timu rel = new relate_timu();
        rel.replaceNumbers();
    }

}

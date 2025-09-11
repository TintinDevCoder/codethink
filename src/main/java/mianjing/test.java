package mianjing;

public class test {
    public String addBinary(String a, String b) {
        char[] achar = a.toCharArray();
        char[] bchar = b.toCharArray();
        int alen = achar.length;
        int blen = bchar.length;
        StringBuilder s = new StringBuilder();
        int i = alen - 1, j = blen - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0 && achar[i] == '1') sum++;
            if (j >= 0 && bchar[j] == '1') sum++;
            s.append(sum % 2);
            carry = sum / 2;
            i--;
            j--;
        }
        if (carry > 0) s.append(carry);
        return s.reverse().toString();
    }
}


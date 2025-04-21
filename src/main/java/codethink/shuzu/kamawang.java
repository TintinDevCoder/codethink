package codethink.shuzu;

import java.util.Scanner;

public class kamawang {
    /*58. 区间和https://kamacoder.com/problempage.php?pid=1070*/
    void qujianhe() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] matrix = new int[n];
        int pre = 0;
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            matrix[i] = temp + pre;
            pre = matrix[i];
        }
        while (sc.hasNextInt()) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (x == 0) System.out.println(matrix[y]);
            else System.out.println(matrix[y] - matrix[x - 1]);
        }
    }
    void buytudi() {

    }

}

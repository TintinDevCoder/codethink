package bishi.yongyou;

public class t1 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 数字键按从小到大输出
     * @param str string字符串 包含英文26个字母和@!./标点符号的字符串
     * @return int整型二维数组
     */
    public int[][] count (String str) {
        // write code here
        int[] mobile = new int[9];
        char[] ctr = str.toCharArray();
        boolean[] isVisited = new boolean[9];
        int num = 0;
        for (char c : ctr) {
            int n = c - 'a';
            if (n < 0) {
                if (c == '@') mobile[0] += 1;
                else if (c == '!') mobile[0] += 2;
                else if (c == '.') mobile[0] += 3;
                else mobile[0] += 4;
                if (!isVisited[0]) {
                    isVisited[0] = true;
                    num++;
                }
            } else {
                if (n >= 15) {
                    if (n <= 18) {
                        mobile[6] += n - 15 + 1;
                        if (!isVisited[6]) {
                            isVisited[6] = true;
                            num++;
                        }
                    }else if (n <= 21) {
                        mobile[7] += n - 19 + 1;
                        if (!isVisited[7]) {
                            isVisited[7] = true;
                            num++;
                        }
                    }else{
                        mobile[8] += n - 22 + 1;
                        if (!isVisited[8]) {
                            isVisited[8] = true;
                            num++;
                        }
                    }
                }else {
                    int k1 = n / 3;
                    int k2 = n % 3;
                    mobile[k1 + 1] += k2 + 1;
                    if (!isVisited[k1 + 1]) {
                        isVisited[k1 + 1] = true;
                        num++;
                    }
                }
            }
        }
        int[][] result = new int[num][2];
        int j = 0;
        for(int i = 0; i < 9; i++) {
            if(isVisited[i]) {
                result[j][0] = i + 1;
                result[j][1] = mobile[i];
                j++;
            }
        }
        return result;
    }

    public int getMaxConsecutiveOnes(int[] arr, int n) {
        // write code here
        int r1 = longestSubarray(arr, 0, n);
        int r2 = longestSubarray(arr, 1, n);
        return Math.max(r1,r2);
    }
    public int longestSubarray(int[] arr, int target, int n) {
        int left = 0;
        int countS = 0;
        int maxLen = 0;
        for (int right = 0; right < arr.length; right++) {
            if (arr[right] != target) {
                if (countS == n) {
                    while (left < right && countS == n) {
                        if (arr[left] != target) {
                            countS--;
                        }
                        left++;
                    }
                }
                countS++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    public static void main(String[] args) {
        t1 t = new t1();
        t.getMaxConsecutiveOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0},2);
    }

}

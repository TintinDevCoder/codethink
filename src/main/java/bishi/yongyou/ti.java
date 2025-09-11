package bishi.yongyou;

import java.util.*;

public class ti {
    public static void coupons() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int n = sc.nextInt();
        String[] processInput = input.split(",");
        int len = processInput.length;
        int[] coupons = new int[len];
        for (int i = 0; i < len; i++) {
            if (processInput[i].equals("1")) coupons[i] = 1;
            else coupons[i] = 0;
        }
        int[][] dp = new int[len + 1][2]; //0:不发放 1:发放
        for (int i = 1; i <= len; i++) {
            if (coupons[i - 1] == 1 || (i > 1 && coupons[i - 2] == 1) || (i != len && coupons[i] == 1)) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1];
            }else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        System.out.println(Math.max(dp[len][1], dp[len][0]) >= n);
    }
    static class car{
        int arrivalTime;
        int chargintTime;
        int id;
        int endTime;
        int chargeNum;
        public car(int s, int c, int i) {
            this.arrivalTime = s;
            this.chargintTime = c;
            this.id = i;
        }

        public int getId() {
            return id;
        }
    }
    public static void charge() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Map<Integer, List<car>> list = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int k1 = sc.nextInt();
            int k2 = sc.nextInt();
            car c = new car(k1, k2, i);
            List<car> orDefault = list.getOrDefault(k1, new ArrayList<>());
            orDefault.add(c);
            list.put(k1, orDefault);
        }
        int[] chargeK = new int[k];
        car[] result = new car[n];
        for (Map.Entry<Integer, List<car>> m : list.entrySet()) {
            int arrivalTime = m.getKey();
            List<car> cars = m.getValue();
            for (car c : cars) {
                int minWaitIndex = Integer.MAX_VALUE;
                int minmaxWaitTime = Integer.MAX_VALUE;
                boolean isVisit = false;
                result[c.id] = c;
                for (int i = 0; i < chargeK.length; i++) {
                    if (arrivalTime >= chargeK[i]) {
                        isVisit = true;
                        result[c.id].endTime = arrivalTime + c.chargintTime;
                        result[c.id].chargeNum = i+1;
                        chargeK[i] = arrivalTime + c.chargintTime;
                        break;
                    }
                    if (chargeK[i] < minmaxWaitTime) {
                        minmaxWaitTime = chargeK[i];
                        minWaitIndex = i;
                    }
                }
                if (!isVisit) {
                    chargeK[minWaitIndex] += c.chargintTime;
                    result[c.id].endTime = minmaxWaitTime + c.chargintTime;
                    result[c.id].chargeNum = minWaitIndex+1;
                }
            }
        }
        for (int i = 0; i < result.length; i++) {
            System.out.println(i + " " + result[i].arrivalTime + " " + result[i].endTime + " " + result[i].chargeNum);
        }
    }
    public static void money() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = sc.nextInt();
        }
        Arrays.sort(m);
        int start = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        boolean isTarget = false;
        for (int end = 0; end < n; end++) {
            sum += m[end];
            if (sum >= target) {
                isTarget = true;
                while (start < end && sum > target) {
                    sum -= m[start];
                    start++;
                }
                if (sum == target && result > (end - start + 1)) {
                    result = end - start + 1;
                }else if (result > (end - start + 2)) {
                    result = end - start + 2;
                }
            }
        }
        System.out.println(isTarget ? result : -1);
    }
    static class Rate{
        int startTime;
        int endTime;
        int rate;
        public Rate(int s, int e, int r) {
            this.startTime = s;
            this.endTime = e;
            this.rate = r;
        }
    }
    public static void carCharge() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int queryStartTime = sc.nextInt();
        int queryEndTime = sc.nextInt();
        int[] base = new int[n];
        Rate[] rates = new Rate[m];
        for (int i = 0; i < n; i++) {
            base[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int r = sc.nextInt();
            rates[i] = new Rate(s,e,r);
        }
        int result = 0;
        for (int i = 0; i < k; i++) {
            int site = sc.nextInt();
            int preStartTime = sc.nextInt();
            int preEndTime = sc.nextInt();
            preStartTime = Math.max(preStartTime, queryStartTime);
            preEndTime = Math.min(preEndTime, queryEndTime);
            int sum = (preEndTime - preStartTime) * base[site];
            for (int j = 0; j < m; j++) {
                if (rates[j].endTime >= preStartTime && rates[j].startTime <= preEndTime) {
                    sum += (rates[j].rate - 1) * (Math.min(preEndTime, rates[j].endTime) - Math.max(preStartTime, rates[j].startTime));
                }
            }
            result += sum;
        }
        System.out.println(result);
    }
    public static void main(String[] args) {
        carCharge();
    }
}

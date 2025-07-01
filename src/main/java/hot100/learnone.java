package hot100;


import java.util.*;

public class learnone {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]))
                return new int[]{i, map.get(target - nums[i])};
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode H = head;
        int carry = 0;
        int sum = 0;
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode node = new ListNode(sum);
            head.next = node;
            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode l3 = null;
        if (l1 != null) {
            l3 = l1;
        } else if (l2 != null) {
            l3 = l2;
        }
        while (l3 != null) {
            sum = l3.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode node = new ListNode(sum);
            head.next = node;
            head = head.next;
            l3 = l3.next;
        }
        if (carry != 0) {
            ListNode node = new ListNode(carry);
            head.next = node;
        }
        return H.next;
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int[] index = new int[128];
        Arrays.fill(index, -1);
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < chars.length; right++) {
            left = Math.max(left, index[chars[right]] + 1);
            maxLen = Math.max(maxLen, right - left + 1);
            index[chars[right]] = right;
        }
        return maxLen;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int totallen = len1 + len2;
        if (totallen % 2 == 1) {
            int midIndex = totallen / 2;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex1 = totallen / 2 - 1;
            int midIndex2 = totallen / 2;
            return (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        int index1 = 0;
        int index2 = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        while (true) {
            if (index1 == len1)
                return nums2[index2 + k - 1];
            if (index2 == len2)
                return nums1[index1 + k - 1];
            if (k == 1) return Math.min(nums1[index1], nums2[index2]);
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;
            if (nums1[newIndex1] <= nums2[newIndex2]) {
                k -= newIndex1 - index1 + 1;
                index1 = newIndex1 + 1;
            } else {
                k -= newIndex2 - index2 + 1;
                index2 = newIndex2 + 1;
            }
        }
    }

    public String longestPalindrome(String s) {
        char[] c = s.toCharArray();
        int len = c.length;
        int[][] dp = new int[len + 1][len + 1];
        int maxLen = 0;
        int left = 0;
        int right = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (c[i] == c[j]) {
                    if (j - i < 2) {
                        dp[i][j] = 1;
                    } else dp[i][j] = dp[i + 1][j - 1];
                } else dp[i][j] = 0;
                if (dp[i][j] == 1 && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }


    public String longestPalindrome2(String s) {
        char[] charArray = s.toCharArray();
        int maxLen = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < charArray.length; i++) {
            int len1 = centerLongestPalindrome(charArray, i, i);
            int len2 = centerLongestPalindrome(charArray, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int centerLongestPalindrome(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && left <= right && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        char[] sCharArray = s.toCharArray();
        char[] pCharArray = p.toCharArray();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;
        for (int i = 0; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                if (pCharArray[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (match(sCharArray, pCharArray, i - 1, j - 2)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (match(sCharArray, pCharArray, i - 1, j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[slen][plen];
    }

    public boolean match(char[] sCharArray, char[] pCharArray, int sIndex, int pIndex) {
        if (sIndex < 0) return false;
        if (pCharArray[pIndex] == '.' || sCharArray[sIndex] == pCharArray[pIndex]) {
            return true;
        }
        return false;
    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int now = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, now);
            if (height[left] > height[right]) {
                right--;
            } else left++;
        }
        return maxArea;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) break;
            if (nums[i] == nums[i - 1]) continue;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[k] < 0) break;
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (k > j && nums[k - 1] == nums[k]) k--;
                    while (j < k && nums[j + 1] == nums[j]) j++;
                    k--;
                    j++;
                } else if (sum > 0) k--;
                else j++;
            }
        }
        return res;
    }

    final char[][] letterCombinationsmap = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return res;
        char[] charArray = digits.toCharArray();
        int len = charArray.length;
        int[] index = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = charArray[i] - '0';
        }
        letterCombinationsDfs(index, 0, len, new StringBuffer());
        return res;
    }

    public void letterCombinationsDfs(int[] index, int n, int len, StringBuffer sb) {
        if (n >= len) {
            res.add(sb.toString());
            return;
        }
        int now = index[n];
        for (char c : letterCombinationsmap[now]) {
            sb.append(c);
            letterCombinationsDfs(index, n + 1, len, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode H = new ListNode();
        H.next = head;
        ListNode slow = H, fast = H;
        while (n-- != 0 && fast.next != null) fast = fast.next;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return H.next;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int leftnum = 0;
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                leftnum++;
            } else {
                if (stack.isEmpty()) return false;
                Character pop = stack.pop();
                if (leftnum == 0 || (c == ')' && pop != '(') || (c == ']' && pop != '[') || (c == '}' && pop != '{')) {
                    return false;
                }
                leftnum--;
            }
        }
        return stack.isEmpty();
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode H = new ListNode();
        ListNode head = H;
        while (list1 != null || list2 != null) {
            if (list1 == null || (list2 != null && list2.val < list1.val)) {
                H.next = list2;
                list2 = list2.next;
            } else {
                H.next = list1;
                list1 = list1.next;
            }
            H = H.next;
        }
        return head.next;
    }

    List<String> generateParenthesisRes = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        generateParenthesisDfs(0, 0, n, new StringBuffer());
        return generateParenthesisRes;
    }

    public void generateParenthesisDfs(int leftnum, int rightnum, int n, StringBuffer sb) {
        if (leftnum + rightnum == 2 * n) {
            generateParenthesisRes.add(sb.toString());
            return;
        }
        if (leftnum < n) {
            sb.append('(');
            generateParenthesisDfs(leftnum + 1, rightnum, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (rightnum < leftnum) {
            sb.append(')');
            generateParenthesisDfs(leftnum, rightnum + 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeList(lists, 0, lists.length - 1);
    }

    public ListNode mergeList(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;
        int mid = (left + right) >> 1;
        return merge(mergeList(lists, left, mid), mergeList(lists, mid + 1, right));
    }

    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode H = new ListNode();
        ListNode head = H;
        while (list1 != null || list2 != null) {
            if (list1 == null || (list2 != null && list2.val < list1.val)) {
                H.next = list2;
                list2 = list2.next;
            } else {
                H.next = list1;
                list1 = list1.next;
            }
            H = H.next;
        }
        return head.next;
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        int left = i + 1;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public int longestValidParentheses(String s) {
        char[] c = s.toCharArray();
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int result = 0;
        for (int i = 0; i < c.length; i++) {
            char n = c[i];
            if (n == '(') stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else {
                    result = Math.max(result, i - stack.peek());
                }
            }
        }
        return result;
    }

    public int search(int[] nums, int target) {
        int left = 1;
        int right = nums.length - 1;
        int now = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > nums[0]) left = mid + 1;
            else {
                right = mid - 1;
                now = mid;
            }
        }
        left = now;
        right = left + nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int m = mid % (nums.length);
            if (nums[m] == target) return m;
            else if (nums[m] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        int result1 = -1;
        int result2 = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target && (mid == 0 || nums[mid - 1] != target)) {
                result1 = mid;
                break;
            } else if (nums[mid] >= target) {
                right = mid - 1;
            } else left = mid + 1;
        }
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid + 1] != target)) {
                result2 = mid;
                break;
            } else if (nums[mid] <= target) {
                left = mid + 1;
            } else right = mid - 1;
        }
        return new int[]{result1, result2};
    }

    List<List<Integer>> combinationSumRes = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumDfs(0, 0, candidates, new ArrayList<>(), target);
        return combinationSumRes;
    }

    public void combinationSumDfs(int index, int sum, int[] candidates, List<Integer> res, int target) {
        if (sum == target) {
            combinationSumRes.add(new ArrayList<>(res));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) continue;
            res.add(candidates[i]);
            combinationSumDfs(i, sum + candidates[i], candidates, res, target);
            res.remove(res.size() - 1);
        }
    }

    public int trap(int[] height) {
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int maxx = height[0];
        for (int i = 1; i < len - 1; i++) {
            left[i] = maxx;
            maxx = Math.max(maxx, height[i]);
        }
        maxx = height[len - 1];
        for (int i = len - 2; i >= 1; i--) {
            right[i] = maxx;
            maxx = Math.max(maxx, height[i]);
        }
        int result = 0;
        for (int i = 1; i < len - 1; i++) {
            int m = Math.min(left[i], right[i]);
            if (height[i] < m) result += m - height[i];
        }
        return result;
    }

    List<List<Integer>> permuteRes = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        permuteDfs(nums, new boolean[nums.length], new ArrayList<>(nums.length));
        return permuteRes;
    }

    public void permuteDfs(int[] nums, boolean[] visited, List<Integer> res) {
        if (res.size() == nums.length) {
            permuteRes.add(new ArrayList<>(res));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                res.add(nums[i]);
                permuteDfs(nums, visited, res);
                res.remove(res.size() - 1);
                visited[i] = false;
            }
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> listMap = new HashMap<>();
        for (String str : strs) {
            int[] anCount = new int[26];
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                anCount[c - 'a']++;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < anCount.length; i++) {
                sb.append(i + anCount[i] + ' ');
            }
            String string = sb.toString();
            List<String> orDefault = listMap.getOrDefault(string, new ArrayList<>());
            orDefault.add(str);
            listMap.put(string, orDefault);
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : listMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int maxx = result;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > result + nums[i]) result = nums[i];
            else result += nums[i];
            if (maxx < result) maxx = result;
        }
        return maxx;
    }

    public boolean canJump(int[] nums) {
        int canJump = 0;
        int i = 0;
        while (i < nums.length && canJump >= i) {
            canJump = Math.max(nums[i] + i, canJump);
            i++;
            if (canJump >= nums.length - 1) return true;
        }
        return false;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (t1, t2) -> t1[0] - t2[0]);
        int left = intervals[0][0];
        int right = intervals[0][1];
        ArrayList<int[]> result = new ArrayList();
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > right) {
                result.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            } else {
                right = Math.max(intervals[i][1], right);
            }
        }
        result.add(new int[]{left, right});
        return result.toArray(new int[result.size()][]);
    }

    public int lengthOfLastWord(String s) {
        char[] c = s.toCharArray();
        boolean is = false;
        int result = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] == ' ') {
                if (is) return result;
            } else {
                if (!is) is = true;
                result++;
            }
        }
        return result;
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int i = 1;
        int left = 0, right = 0;
        for (i = 1; i <= n; i++) {
            result[left][right++] = i;
        }
        right--;
        int step = n - 1;
        int target = 2;
        int[] totarget = new int[]{0, 2, 3, 4, 1};
        int m = 0;
        while (i <= n * n) {
            int j = 0;
            switch (target) {
                case 1:
                    while (j < step) {
                        result[left][++right] = i;
                        j++;
                        i++;
                    }
                    break;
                case 2:
                    while (j < step) {
                        result[++left][right] = i;
                        j++;
                        i++;
                    }
                    break;
                case 3:
                    while (j < step) {
                        result[left][--right] = i;
                        j++;
                        i++;
                    }
                    break;
                case 4:
                    while (j < step) {
                        result[--left][right] = i;
                        j++;
                        i++;
                    }
                    break;
            }
            target = totarget[target];
            m++;
            if (m == 2) {
                step--;
                m = 0;
            }
        }
        return result;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        int count = 0;
        ListNode H = head;
        while (H != null) {
            count++;
            H = H.next;
        }
        if (count <= 1 || k % count == 0) return head;
        count = count - k % count - 1;
        ListNode result = new ListNode();
        H = head;
        while (count-- != 0) {
            H = H.next;
        }
        result = H.next;
        H.next = null;
        H = result;
        while (H != null && H.next != null) {
            H = H.next;
        }
        if (H != null) H.next = head;
        H.next = head;
        return result;
    }

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    int minPathSumResult = 0;

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1];
    }

    public void minPathSumDfs(int[][] grid, boolean[][] visited, int i, int j, int sum) {
        int m = 0;
        int n = 0;
        if (i == m && j == n) {
            minPathSumResult = Math.max(minPathSumResult, sum);
            return;
        }
        if (i < m && !visited[i + 1][j]) {
            visited[i + 1][j] = true;
            minPathSumDfs(grid, visited, i + 1, j, sum + grid[i + 1][j]);
            visited[i + 1][j] = false;
        }
        if (j < n && !visited[i][j + 1]) {
            visited[i][j + 1] = true;
            minPathSumDfs(grid, visited, i, j + 1, sum + grid[i][j + 1]);
            visited[i + 1][j] = false;
        }
    }

    public int[] plusOne(int[] digits) {
        int len = digits.length - 1;
        if (len < 0) return new int[]{1};
        int temp = 1;
        while (len >= 0 && temp != 0) {
            int s = digits[len] + temp;
            digits[len--] = s % 10;
            temp = s / 10;
        }
        int[] newArray;
        if (temp != 0) {
            newArray = new int[digits.length + 1];
            newArray[0] = temp;
            for (int i = 0; i < digits.length; i++) {
                newArray[i + 1] = digits[i];
            }
        } else newArray = digits;
        return newArray;
    }

    public String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int[] map = new int[50];
        map[48] = 49;
        map[49] = 48;
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        int i = a.length() - 1;
        int j = b.length() - 1;
        char temp = '0';
        char c = ' ';
        while (i >= 0 && j >= 0) {
            if (ca[i] == '1' && cb[j] == '1') {
                sb.append(temp);
                temp = '1';
            } else if (ca[i] == '1' || cb[j] == '1') {
                c = (char) map[temp];
                sb.append(c);
            } else {
                sb.append(temp);
                temp = '0';
            }
            i--;
            j--;
        }
        while (i >= 0) {
            if (ca[i] == '1') {
                c = (char) map[temp];
            } else {
                c = temp;
                temp = '0';
            }
            sb.append(c);
            i--;
        }

        while (j >= 0) {
            if (cb[j] == '1') {
                c = (char) map[temp];
            } else {
                c = temp;
                temp = '0';
            }
            sb.append(c);
            j--;
        }
        if (temp == '1') sb.append(temp);
        return sb.reverse().toString();
    }

    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left <= right) {
            int mid = (left + right) >> 1;
            long n = (long) mid * mid;
            if (n == x) return mid;
            else if (n > x) {
                right = mid - 1;
            } else left = mid + 1;
        }
        return right;
    }

    public int climbStairs(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /*给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为 更加简洁的规范路径。

    在 Unix 风格的文件系统中规则如下：

        一个点 '.' 表示当前目录本身。
        此外，两个点 '..' 表示将目录切换到上一级（指向父目录）。
        任意多个连续的斜杠（即，'//' 或 '///'）都被视为单个斜杠 '/'。
        任何其他格式的点（例如，'...' 或 '....'）均被视为有效的文件/目录名称。

    返回的 简化路径 必须遵循下述格式：

        始终以斜杠 '/' 开头。
        两个目录名之间必须只有一个斜杠 '/' 。
        最后一个目录名（如果存在）不能 以 '/' 结尾。
        此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。

    返回简化后得到的 规范路径 。*/
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack();
        int i = 0;
        while (i < split.length) {
            String s = split[i];
            if (s.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (s.equals(".") || s.equals("")) {

            } else {
                stack.push(s);
            }
            i++;
        }
        StringBuffer sb = new StringBuffer();
        for (String s : stack) {
            sb.append(s + "/");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            return "/" + sb.toString();
        } else return "/";
    }

    public int minDistance(String word1, String word2) {
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1Array[i - 1] != word2Array[j - 1]) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }

    public void setZeroes(int[][] matrix) {
        int len1 = matrix.length;
        int len2 = matrix[0].length;
        int[] visited1 = new int[len1];
        int[] visited2 = new int[len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (matrix[i][j] == 0) {
                    visited1[i] = 1;
                    visited2[j] = 1;
                }
            }
        }
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (visited1[i] == 1 || visited2[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void sortColors(int[] nums) {
        int p0 = 0, p1 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                nums[i] = nums[p0];
                nums[p0] = 0;
                if (p0 < p1) {
                    nums[i] = nums[p1];
                    nums[p1] = 1;
                }
                p0++;
                p1++;
            } else if (nums[i] == 1) {
                nums[i] = nums[p1];
                nums[p1] = 1;
                p1++;
            }
        }
    }

    public String minWindow(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int[] count = new int[128];
        int counts = 0;
        for (char c : tc) {
            count[c]++;
            counts++;
        }
        int len = Integer.MAX_VALUE;
        int rleft = 0, rright = 0;
        int left = 0, right = 0;
        while (right < sc.length) {
            if (count[sc[right]]-- > 0) {
                counts--;
            }
            while (left <= right && counts == 0) {
                if (++count[sc[left++]] > 0) {
                    if (len > right - left + 1) {
                        rleft = left - 1;
                        rright = right;
                        len = right - left + 1;
                    }
                    counts++;
                    break;
                }
            }
            right++;
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(rleft, rright + 1);
    }

    List<List<Integer>> subsetsRes = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        subsetsDfs(nums, 0, new ArrayList<>());
        return subsetsRes;
    }

    public void subsetsDfs(int[] nums, int index, List<Integer> res) {
        if (index >= nums.length) {
            subsetsRes.add(new ArrayList<>(res));
            return;
        }
        subsetsDfs(nums, index + 1, res);
        res.add(nums[index]);
        subsetsDfs(nums, index + 1, res);
        res.remove(res.size() - 1);
    }

    boolean isexist = false;
    int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        if (board.length * board[0].length < word.length()) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    visited[i][j] = true;
                    existDfs(board, word.toCharArray(), i, j, 1, visited);
                    if (isexist) return isexist;
                }
            }
        }
        return false;
    }

    public void existDfs(char[][] board, char[] word, int x, int y, int nextPosition, boolean[][] visited) {
        if (nextPosition >= word.length) {
            isexist = true;
            return;
        }
        if (isexist) return;
        for (int[] m : move) {
            int newx = x + m[0];
            int newy = y + m[1];
            if (newx < 0 || newx >= board.length || newy < 0 || newy >= board[0].length || visited[newx][newy])
                continue;
            if (board[newx][newy] == word[nextPosition]) {
                visited[newx][newy] = true;
                existDfs(board, word, newx, newy, nextPosition + 1, visited);
                visited[newx][newy] = false;
            }
        }

    }

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = -1;
        for (int i = 1; i < len; i++) {
            int j = i - 1;
            while (j != -1 && heights[j] > heights[i]) {
                j = left[j];
            }
            left[i] = j;
        }
        right[len - 1] = len;
        for (int i = len - 2; i >= 0; i--) {
            int j = i + 1;
            while (j != len && heights[j] > heights[i]) {
                j = right[j];
            }
            right[i] = j;
        }
        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            result = Math.max(result, heights[i] * (right[i] - left[i] - 1));
        }
        return result;
    }

    public int maximalRectangle(char[][] matrix) {
        int len = matrix[0].length;
        int[] heights = new int[len];
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = -1;
        int result = 0;
        for (int k = matrix.length - 1; k >= 0; k--) {
            int num = 0;
            for (int i = k; i >= 0; i--) {
                if (matrix[i][0] == '1') num++;
                else break;
            }
            heights[0] = num;
            for (int i = 1; i < len; i++) {
                num = 0;
                for (int j = k; j >= 0; j--) {
                    if (matrix[j][i] == '1') num++;
                    else break;
                }
                heights[i] = num;
                int j = i - 1;
                while (j != -1 && heights[j] >= heights[i]) {
                    j = left[j];
                }
                left[i] = j;
            }
            right[len - 1] = len;
            for (int i = len - 2; i >= 0; i--) {
                int j = i + 1;
                while (j != len && heights[j] >= heights[i]) {
                    j = right[j];
                }
                right[i] = j;
            }
            for (int i = 0; i < heights.length; i++) {
                result = Math.max(result, heights[i] * (right[i] - left[i] - 1));
            }
        }
        return result;
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            res.add(root.val);
            TreeNode node = stack.pop();
            if (node.right != null) {
                root = node.right;
            }
        }
        return res;
    }
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i] += dp[i] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
    Integer preNode = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST(root.left);
        if (!left || (preNode != null && preNode >= root.val)) {
            return false;
        }
        preNode = root.val;
        boolean right = isValidBST(root.right);
        return left && right;
    }
    boolean fres = true;
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricDfs(root.left, root.right);
    }
    public boolean isSymmetricDfs(TreeNode left, TreeNode right) {
        if (!fres) return false;
        if (left == null && right == null) return true;
        else if (left == null || right == null || left.val != right.val){
            fres = false;
            return false;
        }
        return isSymmetricDfs(left.left, right.right) && isSymmetricDfs(left.right, right.left);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        int num = 1;
        queue.add(root);
        while (num != 0) {
            int newnum = 0;
            List<Integer> r = new ArrayList<>();
            while (num-- != 0) {
                TreeNode node = queue.poll();
                r.add(node.val);
                if (node.left != null) {
                    newnum++;
                    queue.add(node.left);
                }
                if (node.right != null) {
                    newnum++;
                    queue.add(node.right);
                }
            }
            num = newnum;
            res.add(new ArrayList<>(r));
        }

        return res;
    }
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode(preorder[0]);
        int index = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = stack.peek();
            if (node.val != inorder[index]) {
                node.left = new TreeNode(preorder[i]);
                stack.push(node.left);
            }else {
                while (!stack.isEmpty() && stack.peek().val == inorder[index]) {
                    index++;
                    node = stack.pop();
                }
                node.right = new TreeNode(preorder[i]);
                stack.push(node.right);
            }
        }

        return root;
    }
    public void flatten(TreeNode root) {
        flattenDg(root);
    }
    public TreeNode flattenDg(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        if (root.left == null) return flattenDg(root.right);
        TreeNode left = root.left;
        root.left = null;
        if (root.right == null) {
            root.right = left;
            return flattenDg(root.right);
        }
        TreeNode right = root.right;
        root.right = left;
        flattenDg(root.right).right = right;
        return flattenDg(right);
    }
    public int maxProfit(int[] prices) {
        int nowmax = 0;
        int result = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            nowmax = Math.max(nowmax, prices[i]);
            result = Math.max(result, nowmax - prices[i]);
        }
        return result;
    }
    int maxPathSumRes = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumDg(root);
        return maxPathSumRes;
    }
    public int maxPathSumDg(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(maxPathSumDg(root.left), 0);
        int right = Math.max(maxPathSumDg(root.right), 0);
        maxPathSumRes = Math.max(maxPathSumRes, root.val + left + right);
        return Math.max(left, right) + root.val;
    }
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int result = 0;
        for (Integer i : set) {
            if (set.contains(i - 1)) {
                int j = 1;
                while (set.contains(i + j)) {
                    j++;
                }
                result = Math.max(result, j - i);
            }
        }
        return result;
    }
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public boolean hasCycle(ListNode head) {
        ListNode H = new ListNode();
        H.next = head;
        ListNode fast = H, slow = H;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode H = new ListNode();
        H.next = head;
        ListNode fast = H, slow = H;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        if (fast == null || fast.next == null) return null;
        ListNode temp = H;
        while (temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }
        return slow;
    }
    public ListNode sortList(ListNode head) {
        return sortListM(head, null);
    }
    public ListNode sortListM(ListNode head, ListNode tail) {
        if (head == null) return null;
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) fast = fast.next;
        }
        return sortListMerge(sortListM(head, slow), sortListM(slow, tail));
    }
    public ListNode sortListMerge(ListNode l1, ListNode l2) {
        ListNode r = new ListNode(0);
        ListNode h = r;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                h.next = l2;
                l2 = l2.next;
            }else {
                h.next = l1;
                l1 = l1.next;
            }
            h = h.next;
        }
        if (l1 != null) h.next = l1;
        if (l2 != null) h.next = l2;
        return r.next;
    }

    public int maxProduct(int[] nums) {
        if (nums.length == 1 && nums[0] < 0)
            return nums[0];
        int leftmax = 0;
        int rightmax = 0;
        int now = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                now = 1;
            else {
                now *= nums[i];
                leftmax = Math.max(leftmax, now);
            }
        }
        now = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0)
                now = 1;
            else {
                now *= nums[i];
                rightmax = Math.max(rightmax, now);
            }
        }
        return Math.max(leftmax, rightmax);
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode h = headA;
        while (h != null) {
            lenA++;
            h = h.next;
        }
        h = headB;
        while (h != null) {
            lenB++;
            h = h.next;
        }
        if (lenA > lenB) {
            int n = lenA - lenB;
            while (n-- != 0) {
                headA = headA.next;
            }
        }else {
            int n = lenB - lenA;
            while (n-- != 0) {
                headB = headB.next;
            }
        }
        while (headA != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    public int majorityElement(int[] nums) {
        int count = 0;
        int now = nums[0];
        for (int num : nums) {
            if (count == 0) now = num;
            count += now == num ? 1 : -1;
        }
        return now;
    }
    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp1 = new int[len + 1];//偷
        int[] dp2 = new int[len + 1];
        dp1[0] = 0;
        dp2[0] = 0;
        for (int i = 1; i <= len; i++) {
            dp1[i] = dp2[i - 1] + nums[i - 1];
            dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]);
        }
        return Math.max(dp1[len], dp2[len]);
    }
    int[][] target = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean[][] numIslandsvisited;
    public int numIslands(char[][] grid) {
        int len1 = grid.length;
        int len2 = grid[0].length;
        numIslandsvisited = new boolean[len1][len2];
        int result = 0;
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (grid[i][j] == '1' && !numIslandsvisited[i][j]) {
                    result++;
                    numIslandsDfs(grid, i, j);
                }
            }
        }
        return result;
    }
    public void numIslandsDfs(char[][] grid, int x, int y) {
        for (int[] tar : target) {
            int newx = x + tar[0];
            int newy = y + tar[1];
            if (newx < 0 || newy < 0 || newx >= grid.length || newy >= grid[0].length || grid[newx][newy] == '0' || numIslandsvisited[newx][newy])  {
                continue;
            }
            numIslandsvisited[newx][newy] = true;
            numIslandsDfs(grid, newx, newy);
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> target = new ArrayList<>();
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            target.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            target.get(prerequisite[0]).add(prerequisite[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDfs(i, target, visited)) return false;
        }
        return true;
    }
    public boolean canFinishDfs(int index, List<List<Integer>> target, int[] visited) {
        if (visited[index] == 1) return true;
        if (visited[index] == 2) return false;
        visited[index] = 2;
        for (Integer i : target.get(index)) {
            if (!canFinishDfs(i, target, visited)) return false;
        }
        visited[index] = 1;
        return true;
    }
    public int findKthLargest(int[] nums, int k) {
        int maxx = nums[0], minn = nums[0];
        for (int num : nums) {
            maxx = Math.max(maxx, num);
            minn = Math.min(minn, num);
        }
        int[] target = new int[maxx - minn + 1];
        for (int num : nums) {
            target[num - minn]++;
        }
        for (int i = maxx - minn; i >= 0; i--) {
            k -= target[i];
            if (k <= 0) return i + minn;
        }
        return 0;
    }
    public int maximalSquare(char[][] matrix) {
        int len1 = matrix.length;
        int len2 = matrix[0].length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int result = 0;
        for (int i = 0; i < len2; i++) {
            dp[0][i] = matrix[0][i] - '0';
            if (result == 0 && matrix[0][i] == '1') result = 1;
        }
        for (int i = 1; i < len1; i++) {
            dp[i][0] = matrix[i][0] - '0';
            if (result == 0 && matrix[i][0] == '1') result = 1;
            for (int j = 1; j < len2; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                result = Math.max(dp[i][j], result);
            }
        }
        return result * result;
    }
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        slow.next = null;
        while (fast != null) {
            ListNode temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        return slow;
    }
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) fast = fast.next;
            else break;
            slow = slow.next;
        }
        ListNode leftNode = head;
        ListNode rightNode = null;
        if (slow != null) {
            rightNode = reverseList(slow);
        }
        slow.next = null;
        while (leftNode != null && rightNode != null) {
            if (leftNode.val != rightNode.val) return false;
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        return true;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p) return p;
        if (root == q) return q;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] dp1 = new int[len + 1];
        int[] dp2 = new int[len + 1];
        dp1[0] = 1;
        dp2[len - 1] = 1;
        for (int i = 1; i < len; i++) {
            dp1[i] = dp1[i - 1] * nums[i - 1];
        }
        for (int i = len - 2; i >= 0; i--) {
            dp2[i] = dp2[i + 1] * nums[i + 1];
        }
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = dp1[i] * dp2[i];
        }
        return result;
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int step = len - k + 1;
        int[] result = new int[step];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.add(nums[i]);
        }
        for (int i = k; i < len; i++) {
            result[i - k] = deque.peek();
            while (!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.add(nums[i]);
            if (nums[i - k] == deque.peek()) {
                deque.pop();
            }
        }
        result[step - 1] = deque.peek();
        return result;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int len1 = matrix.length, len2 = matrix[0].length;
        int x = 0, y = len2 - 1;
        while (x < len1 && y < len2) {
            if (target == matrix[x][y]) return true;
            else if (target > matrix[x][y]) x++;
            else y--;
        }
        return false;
    }
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) dp[i] = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[index++] = nums[i];
        }
        for (int i = index; i< nums.length; i++) {
            nums[i] = 0;
        }
    }
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow != fast);
        slow = 0;
        do {
            slow = nums[slow];
            fast = nums[fast];
        }while (slow != fast);
        return slow;
    }
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] target = new int[nums.length];
        int index = 1;
        target[index] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (target[index] < nums[i]) {
                target[++index] = nums[i];
            }else {
                int left = 0;
                int right = index;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (target[mid] < nums[i]) left = mid + 1;
                    else right = mid - 1;
                }
                target[left] = nums[i];
            }
        }
        return index;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] dp = new int[3];//0:持股 1:不持股并处于冷冻期 2:不持股并不处于冷冻期
        dp[0] = -prices[0];
        for (int i = 1; i< prices.length; i++) {
            int temp = dp[2];
            dp[2] = Math.max(dp[2], dp[1]);
            dp[1] = dp[0] + prices[i];
            dp[0] = Math.max(dp[0], temp - prices[i]);
        }
        return Math.max(dp[1], dp[2]);
    }
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (p1, p2) -> p2[0] - p1[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (minHeap.peek() <= intervals[i][0]) minHeap.poll(); // 释放会议室
            minHeap.add(intervals[i][1]);
        }
        return minHeap.size();
    }
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }
        return dp[amount] == Integer.MAX_VALUE - 1 ? 0 : dp[amount];
    }
    public int rob(TreeNode root) {
        int[] result = robTree(root);
        return Math.max(result[0], result[1]);
    }
    public int[] robTree(TreeNode root) {
        if (root == null) return new int[]{0,0};
        int[] left = robTree(root.left);
        int[] right = robTree(root.right);
        int[] result = new int[2];
        result[0] = root.val + left[1] + right[1];
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return result;
    }
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            }else dp[i] = dp[i / 2] + 1;
        }
        return dp;
    }
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((t1, t2) -> t1.getValue() - t2.getValue());
        for (Map.Entry<Integer,Integer> m : map.entrySet()) {
            pq.add(m);
            if (pq.size() > k) pq.poll();
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().getKey();
        }
        return result;
    }
    public String decodeString(String s) {
        Stack<String> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack<>();
        char[] c = s.toCharArray();
        int i = 0;
        while (i < c.length) {
            if ((c[i] >= '0' && c[i] <='9')) {
                int m = 0;
                while (c[i] >= '0' && c[i] <='9') {
                    m *= 10;
                    m += c[i] - '0';
                    i++;
                }
                stack2.add(m);
            }else if ((c[i] >= 'a' && c[i] <='z') || c[i] == '['){
                stack1.add(String.valueOf(c[i++]));
            }else {
                StringBuilder n1 = new StringBuilder();
                StringBuilder n2 = new StringBuilder();
                while (!stack1.peek().equals("[")) {
                    n1.append(stack1.pop());
                }
                stack1.pop();
                n1.reverse();
                Integer num = stack2.pop();
                for (int k = 0; k < num; k++) {
                    n2.append(n1);
                }
                stack1.add(n2.toString());
                i++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String string : stack1) {
            sb.append(string);
        }
        return sb.toString();
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int size = equations.size();
        Map<String, Integer> map = new HashMap<>();
        int n = 0;
        UnionFind union = new UnionFind(size * 2);
        for (int i = 0; i < size; i++) {
            List<String> equation = equations.get(i);
            String s1 = equation.get(0);
            String s2 = equation.get(1);
            if (!map.containsKey(s1)) map.put(s1, n++);
            if (!map.containsKey(s2)) map.put(s2, n++);
            union.union(map.get(s1), map.get(s2), values[i]);
        }
        size = queries.size();
        double[] result = new double[size];
        for (int i = 0; i < queries.size(); i++) {
            String s1 = queries.get(i).get(0);
            String s2 = queries.get(i).get(1);
            if (!map.containsKey(s1) || !map.containsKey(s2)) result[i] = -1.0d;
            else {
                result[i] = union.isConnected(map.get(s1), map.get(s2));
            }
        }
        return result;
    }
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        List<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[list.size()][]);
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                if (dp[j - nums[i]]) dp[j] = true;
            }
        }
        return dp[sum];
    }
    public int pathSum1(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = pathSumDfs(root, targetSum);
        ret += pathSum1(root.left, targetSum);
        ret += pathSum1(root.right, targetSum);
        return ret;
    }
    public int pathSumDfs(TreeNode root, long targetSum) {
        if (root == null) return 0;
        int ret = 0;
        if (root.val == targetSum) ret++;
        ret += pathSumDfs(root.left, targetSum - root.val);
        ret += pathSumDfs(root.right, targetSum - root.val);
        return ret;
    }
    int pathSumResult = 0;
    public int pathSum(TreeNode root, long targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0l, 1);
        pathSumDg(root, targetSum, 0, map);
        return pathSumResult;
    }
    public void pathSumDg(TreeNode root, long targetSum, long now, Map<Long, Integer> map) {
        if (root == null) return;
        now += root.val;
        pathSumResult += map.getOrDefault(now - targetSum, 0);
        map.put(now, map.getOrDefault(now, 0) + 1);
        pathSumDg(root.left, targetSum, now, map);
        pathSumDg(root.right, targetSum, now, map);
        map.put(now, map.get(now) - 1);
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int slen = s.length();
        int plen = p.length();
        if (slen < plen) return res;
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        int[] ns = new int[26];
        int[] np = new int[26];
        for (char c : cp) {
            np[c - 'a']++;
        }
        for (int i = 0; i < plen; i++) {
            ns[cs[i] - 'a']++;
        }
        if (Anagramscheck(ns, np)) res.add(0);
        int left = 0;
        for (int i = plen; i < slen; i++) {
            ns[cs[i] - 'a']++;
            ns[cs[left++] - 'a']--;
            if (Anagramscheck(ns, np)) res.add(left);
        }
        return res;
    }
    boolean Anagramscheck(int[] snum, int[] pnum) {
        for (int i = 0; i < 26; i++) {
            if (snum[i] != pnum[i]) return false;
        }
        return true;
    }
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        for (int num : nums) {
            if (nums[(num - 1) % len] <= len) nums[(num - 1) % len] += len;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] <= len) res.add(i + 1);
        }
        return res;
    }
    public int hammingDistance(int x, int y) {
        List<Boolean> ex = new ArrayList<>();
        List<Boolean> ey = new ArrayList<>();
        while (x != 0) {
            ex.add(x % 2 == 1 ? true : false);
            x /= 2;
        }
        while (y != 0) {
            ey.add(y % 2 == 1 ? true : false);
            y /= 2;
        }
        while (ex.size() != ey.size()) {
            if (ex.size() > ey.size()) ey.add(false);
            else ex.add(false);
        }
        int i = 0, len1 = ex.size();
        int j = 0, len2 = ey.size();
        int result = 0;
        while (i < len1 && j < len2) {
            if (ex.get(i++) != ey.get(j++)) result++;
        }
        return result;
    }
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) return 0;
        diff /= 2;
        int[] dp = new int[diff + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = diff; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[diff];
    }
    int convertBSTPre = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        root.val += convertBSTPre;
        convertBSTPre = root.val;
        convertBST(root.left);
        return root;
    }
    int diameterOfBinaryTreeResult = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeDfs(root);
        return diameterOfBinaryTreeResult;
    }
    public int diameterOfBinaryTreeDfs(TreeNode root) {
        if (root == null) return 0;
        int left = diameterOfBinaryTreeDfs(root.left);
        int right = diameterOfBinaryTreeDfs(root.right);
        diameterOfBinaryTreeResult = Math.max(diameterOfBinaryTreeResult, left + right);
        return Math.max(left, right) + 1;
    }
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        boolean[] res = new boolean[len + 1];
        int leftmax = nums[0];
        int rightmin = nums[len - 1];
        for (int i = 1; i < nums.length; i++) {
            if (leftmax > nums[i]) res[i] = true;
            leftmax = Math.max(leftmax, nums[i]);
        }
        for (int i = len - 2; i >= 0; i--) {
            if (rightmin < nums[i]) res[i] = true;
            rightmin = Math.min(rightmin, nums[i]);
        }
        int left = -1, right = -1;
        for (int i = 0; i < len; i++) {
            if (left == -1 && res[i]) left = i;
            if (res[i]) right = i;
        }
        if (left == -1 && right == -1) return 0;
        return right - left + 1;
    }
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        int max = 0;
        for (char task : tasks) {
            counts[task - 'A']++;
        }
        Arrays.sort(counts);
        max = counts[25];
        int result = (max - 1) * (n + 1) + 1;
        for (int i = 0; i < 25; i++) {
            if (counts[i] == max) result++;
        }
        return Math.max(result, tasks.length);
    }
    public int countSubstrings1(String s) {
        char[] c = s.toCharArray();
        int len = c.length;
        boolean[][] dp = new boolean[len + 1][len + 1];
        int result = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (c[i] == c[j]) {
                    if ((j - i <= 1) || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        result++;
                    }
                }
            }
        }
        return result;
    }
    public int countSubstrings(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int left = i;
            int right = i + 1;
            while (left >= 0 && right < chars.length && left <= right && chars[left--] == chars[right++]) result++;
            left = i;
            right = i;
            while (left >= 0 && right < chars.length && left <= right && chars[left--] == chars[right++]) result++;
        }
        return result;
    }
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int len = temperatures.length;
        int[] result = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) result[i] = 0;
            else {
                result[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        return result;
    }
    public static void main(String[] args) {
        learnone m = new learnone();
        int[][] array = {
                {1,1}
        };
        m.findUnsortedSubarray(new int[]{2,6,4,8,10,9,15});
        ListNode listNode = new ListNode(1, new ListNode(2));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class UnionFind {
    int[] parents;
    double[] values;
    public UnionFind(int size){
        parents = new int[size];
        values = new double[size];
        for (int i = 0; i < size; i++) {
            parents[i] = i;
            values[i] = 1.0d;
        }
    }
    public int find(int x) {
        if (parents[x] != x) {
            int parent = parents[x];
            parents[x] = find(parents[x]);
            values[x] = values[x] * values[parent];
        }
        return parents[x];
    }
    public void union(int x, int y, double value) {
        int parentx = find(x);
        int parenty = find(y);
        if (parentx != parenty) {
            parents[parentx] = parenty;
            values[parentx] = values[y] * value / values[x];
        }
    }
    public double isConnected(int x, int y) {
        int parentx = find(x);
        int parenty = find(y);
        if (parentx != parenty) return -1.0;
        return values[x] / values[y];
    }
}
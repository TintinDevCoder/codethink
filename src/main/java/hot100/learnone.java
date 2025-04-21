package hot100;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode H = head;
        int carry = 0;
        int sum = 0;
        while(l1 != null && l2 != null) {
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
        }else if (l2 != null) {
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
        }else {
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
            }else {
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
                    }else dp[i][j] = dp[i + 1][j - 1];
                }else dp[i][j] = 0;
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
        while (left >=0 && right <chars.length && left <= right && chars[left] == chars[right]) {
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
                }else {
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
            }else left++;
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
                if (sum == 0){
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (k > j && nums[k - 1] == nums[k]) k--;
                    while (j < k && nums[j + 1] == nums[j]) j++;
                    k--;
                    j++;
                }
                else if (sum > 0) k--;
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
            }else {
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
            }else {
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
            }else {
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
        if(i >= 0) {
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
            }else if (nums[mid] >= target) {
                right = mid - 1;
            }else left = mid + 1;
        }
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid + 1] != target)) {
                result2 = mid;
                break;
            }else if (nums[mid] <= target) {
                left = mid + 1;
            }else right = mid - 1;
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
            right[i] =maxx;
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
        for (Map.Entry<String, List<String>> entry : listMap.entrySet()){
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
            }else {
                right = Math.max(intervals[i][1], right);
            }
        }
        result.add(new int[]{left, right});
        return result.toArray(new int[result.size()][]);
    }
    public static void main(String[] args) {
        learnone m = new learnone();
        ListNode listNode1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode listNode2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        m.canJump(new int[]{2,0,0});
    }
}

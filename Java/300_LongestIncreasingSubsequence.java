Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

Method 1: 
Time complexity: O(n2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int[] f = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            f[i] = 1;
        }
        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        int ans = 1;
        for (int i = 0; i < nums.length; i++){
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}

Method 2:
https://discuss.leetcode.com/topic/39681/fast-java-binary-search-solution-with-detailed-explanation/4

Note that the length is correct but the sequence may be wrong,
because even it did the substitution, it didn't change the LIS' length. so even some of the number is added incorrectly, 
it won't change the fact that there existed such a sequence with this length.
so whenever a new digit added, there are two cases. one is that it been added to somewhere in the middle, 
which won't change the last digit. the last digit matters if we will add a new one or not. 
Second case is it been added to the last, which indicate it will also works for the true LIS.
So the list DP maybe wrong, the length is right
Here's another example:
[1, 6, 8, 9 ,2, 3]
The sequence will be like:
1;
1, 6;
1, 6, 8;
1, 6, 8, 9;
1, 2, 8, 9;
1, 2, 3, 9;
Obviously, 1,2 ,3, 9 is not the correct sequence, but the length value is correct.

Time complexity: O(nlogn)
 class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int end = 0;
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            int index = binarySearch(dp, 0, end, nums[i]);
            if (nums[i] < dp[index]){
                dp[index] = nums[i];
            }
            if (index > end){
                dp[index] = nums[i];
                end++;
            }
        }
        return end+1;
    }
    private int binarySearch(int[] A, int start, int end, int target){
        int length = end - start + 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] == target){
                return mid;
            }else if (A[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (A[end] < target){
            return length;
        }else if (A[start] > target){
            return 0;
        }else if (A[start] == target){
            return start;
        }
        return end;
    }
}

https://leetcode.com/problems/longest-increasing-subsequence/discuss/74825/Short-Java-solution-using-DP-O(n-log-n)
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int len = 0;
        for (int i = 0; i < nums.length; i++){
            int index = Arrays.binarySearch(dp, 0, len, nums[i]);
            if (index < 0){
                index = - (index + 1);
            }
            dp[index] = nums[i];
            if (index == len){
                len++;
            }
        }
        return len;
    }
}

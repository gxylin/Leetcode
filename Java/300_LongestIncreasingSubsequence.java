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

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i : dp){
            max = Math.max(max, i);
        }
        return max;
    }
}

Method 2:
https://discuss.leetcode.com/topic/39681/fast-java-binary-search-solution-with-detailed-explanation/4
https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation

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


Best solution:
Time complexity: O(nlogn)
https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
Key idea: The end element of smaller list is smaller than end elements of larger lists
Three cases:
1. If A[i] is smallest among all end 
   candidates of active lists, we will start 
   new active list of length 1.
2. If A[i] is largest among all end candidates of 
  active lists, we will clone the largest active 
  list, and extend it by A[i].

3. If A[i] is in between, we will find a list with 
  largest end element that is smaller than A[i]. 
  Clone and extend this list by A[i]. We will discard all
  other lists of same length as that of this modified list.
Algorithm:

Querying length of longest is fairly easy. Note that we are dealing with end elements only. We need not to maintain all the lists.
We can store the end elements in an array. Discarding operation can be simulated with replacement, and extending a list is analogous 
to adding more elements to array.

We will use an auxiliary array to keep end elements. The maximum length of this array is that of input. In the worst case the array 
divided into N lists of size one (note that it does’t lead to worst case complexity). To discard an element, we will trace ceil value
of A[i] in auxiliary array (again observe the end elements in your rough work), and replace ceil value with A[i]. We extend a list by 
adding element to auxiliary array. We also maintain a counter to keep track of auxiliary array length.
    
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int[] tail = new int[n];
        tail[0] = nums[0];
        int len = 1;
        for (int i = 1; i < n; i++){
            if (nums[i] < tail[0]){
                tail[0] = nums[i];
            }else if (nums[i] > tail[len-1]){
                tail[len++] = nums[i];
            }else{
                int index = binarySearch(tail, 0, len - 1, nums[i]); // find the index of the first that is greater than target
                tail[index] = nums[i];
            }
        }
        return len;
    }
    private int binarySearch(int[] nums, int start, int end, int target){
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target){
                end = mid;
            }else{
                start = mid;
            }
        }
        if (nums[start] >= target){
            return start;
        }
        return end;
    }
    
    //the following binary search works too
    private int binarySearch(int[] nums, int start, int end, int target){
        while (start <= end){
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return start;
    }
}

 Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

    0 < i, i + 1 < j, j + 1 < k < n - 1
    Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.

where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element 
indexed R.

Example:

Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1

Note:

    1 <= n <= 2000.
    Elements in the given array will be in range [-1,000,000, 1,000,000].


Method 1:
Time complexity: O(n^2)
Here j is used for middle cut, i for left cut and k for right cut.
Iterate middle cuts and then find left cuts which divides the first half into two equal quarters, 
store that quarter sums in the hashset. Then find right cuts which divides the second half into two equal 
quarters and check if quarter sum is present in the hashset. If yes return true.


class Solution {
    public boolean splitArray(int[] nums) {
        int n = nums.length;
        if (n < 7){
            return false;
        }
        int[] sum = new int[n];
        sum[0] = nums[0];
        for (int i = 1; i < n; i++){
            sum[i] = sum[i-1] + nums[i];
        }
        for (int j = 3; j < n - 3; j++){
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++){
                if (sum[i-1] == sum[j-1] - sum[i]){
                    set.add(sum[i-1]);
                }
            }
            for (int k = j + 2; k < n -1; k++){
                if (sum[n-1] - sum[k] == sum[k-1] - sum[j]){
                    if (set.contains(sum[n-1] - sum[k])){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class Solution {
    public boolean splitArray(int[] nums) {
        int n = nums.length;
        if (n < 7){
            return false;
        }
        int[] preSum = new int[n+1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++){
          preSum[i] = preSum[i-1] + nums[i-1];
        }
        for (int j = 3; j < n - 3; j++){
          Set<Integer> set = new HashSet<>();
           for (int i = 1; i < j-1; i++){
              if (preSum[j] - preSum[i+1] ==  preSum[i]){
                  set.add(preSum[i]);
              }
           }
           for (int k = j + 1; k < n-2; k++){
               if (preSum[n] - preSum[k+1] == preSum[k] - preSum[j+1]){
                   if (set.contains(preSum[n] - preSum[k+1]){
                      return true;
                   }
               }
           }
        }
        return false;
    }
}
                       
                       
                       
                       
Methodd 2: DFS TLE
class Solution {
    public boolean splitArray(int[] nums) {
        int n = nums.length;
        if (n < 7){
            return false;
        }
        int total = 0;
        for (int i : nums){
            total += i;
        }
        int sum = 0;
        int splitCount = 3;
        for (int i = 0; i < n - (2*splitCount-1); i ++){
            sum += nums[i];
            if (dfs(nums, i+1, sum, splitCount - 1, total - sum - nums[i+1])){
                return true;
            }   
        }
        return false;
    }
    private boolean dfs(int[] nums, int start, int sum, int splitCount, int left){
        if (splitCount == 0){
            if (left == sum){
                return true;
            }
            return false;
        }
        int sumSum = 0;
        for (int i = start + 1; i < nums.length - (2*splitCount-1); i++){
            sumSum += nums[i];
            if (sumSum == sum){
                if (dfs(nums, i+1, sum, splitCount - 1, left - sum - nums[i+1])){
                    return true;
                }
            }
        }
        return false;
    }
}

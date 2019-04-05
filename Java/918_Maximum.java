Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

Here, a circular array means the end of the array connects to the beginning of the array.  
 (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

Also, a subarray may only include each element of the fixed buffer A at most once. 
 (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)

 
Example 1:

Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
Example 2:

Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
Example 3:

Input: [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
Example 4:

Input: [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
Example 5:

Input: [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1
 

Note:

-30000 <= A[i] <= 30000
1 <= A.length <= 30000

https://www.jianshu.com/p/e09a051f5420
那么碰到循环数组的问题，无外乎三个套路。
拆分（HOUSE ROBBER那道，循环道路时用的是这个技巧）
倍增（就是在原数组后补到2倍长度，在2倍长数组的里处理，那么原本的N长的数组，我们可以变成N个新的长度为N的数组）
0->N-1， 1- >N， 2- >N+1 ..... N-1 -> 2*n-2
分别对每种循环的可能做处理，最后汇总得到解，这题可以运用这个策略，但是时间复杂度为N^2

取反（求最大，变成求最小）
在这道题里的运用，就是如果是2端加起来最大。那么必定我们需要在中间找一个最小。然后用SUM去减掉中间最小。就得到2端最大。


Method 1: 倍增
Time complexity: O(N^2)
Space Complexity: O(N)
class Solution {
    public int maxSubarraySumCircular(int[] A) {
        int n = A.length;
        int[] B = new int[2*n];
        for (int i = 0; i < 2*n; i++){
            B[i] = A[i%n];
        }
        int res = Integer.MIN_VALUE;
        int[] preSum = new int[2*n+1];
        preSum[0] = 0;
        for (int i = 1; i <= 2*n; i++){
            preSum[i] = preSum[i-1] + B[i-1];
        }
        for (int i = 0; i <= 2*n; i++){
            for (int j = 0; j < i; j++){
                if (i - j <= n){
                    res = Math.max(res, preSum[i] - preSum[j]);
                }
            }
        }
        return res;
    }
}

Method 2: 取反
class Solution {
    public int maxSubarraySumCircular(int[] A) {
        //two cases
        //case 1: max exits in the middle, standard greedy method to find max
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i : A){
            sum += i;
            max = Math.max(max, sum);
            if (sum < 0){
                sum = 0;
            }
        }
        // case 2: max exits at two sides, standard greed method to find min and sum - max
        int min = Integer.MAX_VALUE;
        sum = 0;
        for (int i : A){
            sum += i;
            min = Math.min(min, sum);
            if (sum > 0){
                sum = 0;
            }
        }
        sum = 0;
        for (int i : A){
            sum += i;
        }
        //Corner case
        //if max is negative, it indicates that all the numbers are negative.
        if (max < 0){
            return max;
        }
        return Math.max(max, sum - min);
    }
}
Corner case:
If all number are negative,
return the maximum one,
(which equals to the max subarray sum)


Best solution: O(N)
class Solution {
    public int maxSubarraySumCircular(int[] A) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int localMax = 0;
        int localMin = 0;
        for (int i : A){
            sum += i;
            localMax = Math.max(i, localMax + i);
            max = Math.max(max, localMax);
            localMin = Math.min(i, localMin + i);
            min = Math.min(min, localMin);
        }
        if (max < 0){
            return max;
        }
        return Math.max(max, sum - min);
    }
}




Side note: Refer to leetcode 53
If it is not circular array,
https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
Largest Sum Contiguous Subarray

Method 1: greedy
int maxSubArraySum(int[] A){
  int max = Integer.MIN_VALUE;
  int sum = 0;
  for (int i : A){
     sum += i;
     max = Math.max(max, sum);
     if (sum < 0){
        sum = 0;
     }
  }
  return max;
}
Method 2: DP
 class Solution {
    public int maxSubArray(int[] nums) {
        int local = 0;
        int global = Integer.MIN_VALUE;
        for (int num : nums){
            local = Math.max(local + num, num);
            global = Math.max(global, local);
        }
        return global;
    }
}
    
    

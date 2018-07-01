In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Note:
nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).

Method:
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        //prefix sum to construct W array with each elemenet representing the sum of k in nums
        int[] W = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            if (i >= k){
                sum -= nums[i - k];
            }
            if (i >= k - 1){
                W[i - k + 1] = sum;
            }
        }
        //build left array with each element representing the first occurance of largest element in W from left
        int[] left = new int[W.length];
        int best = 0;
        for (int i = 0; i < W.length; i++){
            if (W[i] > W[best]){//Note not equal
                best = i;
            }
            left[i] = best;
        }
        //build right array with each element representing the last occurance of the lraget element in W from right
        int[] right = new int[W.length];
        best = W.length - 1;
        for (int i = W.length - 1; i >= 0; i--){
            if (W[i] >= W[best]){//Note must use equal
                best = i;
            }
            right[i] = best;
        }
        //find the largest sum with the mid subarray j as the pointer
        int[] res = new int[]{-1, -1, -1};
        for (int j = k; j < W.length - k; j++){
            int i = left[j-k];
            int m = right[j+k];
            if (res[0] == -1 || W[res[0]] + W[res[1]] + W[res[2]] < W[i] + W[j] + W[m]){
                res[0] = i;
                res[1] = j;
                res[2] = m;
            }
        }
        return res;
    }
}

Intuition

It is natural to consider an array W of each interval's sum, where each interval is the given length K. 
To create W, we can either use prefix sums, or manage the sum of the interval as a window slides along the array.

From there, we approach the reduced problem: Given some array W and an integer K, what is the lexicographically 
smallest tuple of indices (i, j, k) with i + K <= j and j + K <= k that maximizes W[i] + W[j] + W[k]?

Algorithm

Suppose we fixed j. We would like to know on the intervals i∈[0,j−K]i \in [0, j-K]i∈[0,j−K] and k∈[j+K,len(W)−1]k \in [j+K, 
\text{len}(W)-1]k∈[j+K,len(W)−1], where the largest value of W[i]W[i]W[i] (and respectively W[k]W[k]W[k]) occurs first. 
(Here, first means the smaller index.)

We can solve these problems with dynamic programming. For example, if we know that iii is where the largest value of W[i]W[i]W[i]
occurs first on [0,5][0, 5][0,5], then on [0,6][0, 6][0,6] the first occurrence of the largest W[i]W[i]W[i] must be either iii or 666.
If say, 666 is better, then we set best = 6.

At the end, left[z] will be the first occurrence of the largest value of W[i] on the interval i∈[0,z]i \in [0, z]i∈[0,z], 
and right[z] will be the same but on the interval i∈[z,len(W)−1]i \in [z, \text{len}(W) - 1]i∈[z,len(W)−1]. This means that for some choice j, the candidate answer must be (left[j-K], j, right[j+K]). We take the candidate that produces the maximum W[i] + W[j] + W[k].

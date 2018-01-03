Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.

The idea is always keep an max-product-window less than K;
Every time shift window by adding a new number on the right(j), if the product is greater than k, 
then try to reduce numbers on the left(i), until the subarray product fit less than k again, (subarray could be empty);
Each step introduces x new subarrays, where x is the size of the current window (j + 1 - i);
example:
for window (5, 2), when 6 is introduced, it add 3 new subarray: (5, (2, (6)))
        (6)
     (2, 6)
  (5, 2, 6)
  
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0){
            return 0;
        }
        int count = 0;
        int product = 1;
        for (int start = 0, end = 0; end < nums.length; end++){
            product *= nums[end];
            while (product >= k && start <= end){
                product /= nums[start];
                start++;
            }
            count += 1 + end - start;
        }
        return count;
    }
}

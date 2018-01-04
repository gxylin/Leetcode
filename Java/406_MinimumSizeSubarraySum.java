Given an array of n positive integers and a positive integer s, find the minimal length of a subarray 
of which the sum ≥ s. If there isn't one, return -1 instead.

Have you met this question in a real interview? Yes
Example
Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.

Challenge 
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

Method 1: two points
time complexity: O(n)
public class Solution {
    /*
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
       if (nums == null || nums.length == 0 ){
           return -1;
       }
       int start = 0;
       int end = 0;
       int ans = Integer.MAX_VALUE;
       int sum = 0;
       while (end < nums.length){
           while (sum < s && end < nums.length){
               sum += nums[end];
               end++;
           }
           while (sum >= s && start < end){
               ans = Math.min(ans, end - start);
               sum -= nums[start];
               start++;
           }
       }
       if (ans == Integer.MAX_VALUE){
           return -1;
       }
       return ans;
    }
}

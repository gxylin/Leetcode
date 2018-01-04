Given an array of integers, find the subarray with smallest sum.

Return the sum of the subarray.

 Notice
The subarray should contain one integer at least.

Have you met this question in a real interview? Yes
Example
For [1, -1, -2, 1], return -3.

Method: greedy
public class Solution {
    /*
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(List<Integer> nums) {
        if (nums == null || nums.size() == 0){
            return 0;
        }
        int local = nums.get(0);
        int global = nums.get(0);
        for (int i = 1; i < nums.size(); i++){
            local = Math.min(nums.get(i), nums.get(i) + local);
            global = Math.min(global, local);
        }
        return global;
    }
}

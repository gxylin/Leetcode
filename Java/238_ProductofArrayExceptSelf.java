Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to 
the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count 
as extra space for the purpose of space complexity analysis.)

Method:
the first scan: ans keeps the product of all numbers ahead of the current index;
the second scan: right keeps the product of all numbers behind the current index;
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++){
            ans[i] = ans[i-1] * nums[i-1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >=0; i--){
            ans[i] *= right;
            right *= nums[i];
        }
        return ans;
    }
}

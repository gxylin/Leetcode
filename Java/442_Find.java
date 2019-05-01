Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]

https://leetcode.com/problems/find-all-duplicates-in-an-array/discuss/92411/Java-O(1)-space-O(n)-time-solution-with-swapping

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0){
                result.add(Math.abs(nums[i]));
            }
            nums[index] = - nums[index];
        }
        return result;
    }
}


Best solution:
The same as Leetcode 41 the first missing positive https://github.com/optimisea/Leetcode/blob/master/Java/41_FirstMissingPositive.java

The same solution as Leetcode 448 Find All Numbers Disappeared in an Array

https://github.com/optimisea/Leetcode/blob/master/Java/448_Find.java

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            while (nums[i] > 0 && nums[i] <= nums.length && i != nums[i] - 1 && nums[i] != nums[nums[i]-1]){
                swap(nums, i, nums[i]-1);
            }
        }
        for (int i = 0; i < nums.length; i++){
            if (nums[i] - 1 != i){
                res.add(nums[i]);
            }
        }
        return res;
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.


The key here is to use swapping to keep constant space and also make use of the length of the array,
which means there can be at most n positive integers. 
So each time we encounter an valid integer, find its correct position and swap. Otherwise we continue.

class Solution {
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length){
            if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == nums[nums[i] -1]){
                i++;
            }else if (nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }
        i = 0;
        while (i < nums.length && nums[i] == i + 1){
            i++;
        }
        return i+1;
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


Best solution:
The same as https://github.com/optimisea/Leetcode/blob/master/Java/442_Find.java

class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            while (nums[i] > 0 && nums[i] <= nums.length && i != nums[i] - 1 && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i]-1);
            }
        }
        int i = 0;
        while (i < nums.length && i == nums[i] - 1){
            i++;
        }
        return i+1;
    }
    private void swap (int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the new length.

class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 0 ; i < nums.length; i++){
            if (nums[i] != nums[index]){
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }
}


Method 2: inspired by 

https://github.com/optimisea/Leetcode/blob/master/Java/80_RemoveDuplicatesII.java

class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++){
            if (index < 1 || nums[index-1] != nums[i]){
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}

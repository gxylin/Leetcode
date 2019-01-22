Given an array of integers, replace every element with the next greatest element
(greatest element on the right side) in the array. Since there is no element next to the last element, replace it with -1. For example, 
if the array is [16, 17, 4, 3, 5, 2], then it should be modified to [17, 5, 5, 5, 2, -1].

Have you met this question in a real interview? Yes
Example
Give nums = [16, 17, 4, 3, 5, 2], change nums to [17, 5, 5, 5, 2, -1]
You should do it in place.

public class Solution {
    /*
     * @param : An array of integers.
     * @return: nothing
     */
    public void arrayReplaceWithGreatestFromRight(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        int max = nums[nums.length - 1];
        nums[nums.length - 1] = -1;
        for (int i = nums.length - 2; i >= 0; i--){
            int temp = nums[i];
            nums[i] = max;
            max = Math.max(max, temp);
        }
    }
}

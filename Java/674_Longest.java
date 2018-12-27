Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1.

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int max = 0;
        int n = nums.length;
        int i = 0;
        int start = 0;
        while (i < n){
            while (i < n && ((i > 0 && nums[i] > nums[i-1]) || i == 0)){
                i++;
            }
            max = Math.max(max, i - start);
            start = i;
            i++;
        }
        return max;
    }
}

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int max = 0;
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++){
            if (i == 0 || nums[i] > nums[i-1]){
                max = Math.max(max, ++count);
            }else{
                count = 1;
            }
        }
        return max;
    }
}

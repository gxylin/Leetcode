Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int start = 0;
        int i = 0;
        while (i < nums.length){
            while (i < nums.length && nums[i] == 0){
                i++;
            }
            start = i;
            while (i < nums.length && nums[i] == 1){
                i++;
            }
            max = Math.max(max, i - start);
        }
        return max;
    }
}

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 1){
                count++;
                max = Math.max(max, count);
            }else{
                count = 0;
            }
        }
        return max;
    }
}

Best solution derived from max consecutive ones II
Sliding window
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        int low = 0;
        int high = 0;
        int k = 0;//flip most number
        while (high < nums.length){
            if (nums[high] == 0){
                count++;
            }
            high++;
            while (count > k){
                if (nums[low] == 0){
                    count--;
                }
                low++;
            }
            max = Math.max(max, high - low);
        }
        return max;
    }
}

Given a non-empty array of integers, return the third maximum number in this array. 
If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.

class Solution {
    public int thirdMax(int[] nums) {
        long firstMax = Long.MIN_VALUE;
        long secondMax = Long.MIN_VALUE;
        long thirdMax = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == firstMax || nums[i] == secondMax || nums[i] == thirdMax){
                continue;
            }
            if (nums[i] > firstMax){
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = nums[i];
            }else if (nums[i] > secondMax){
                thirdMax = secondMax;
                secondMax = nums[i];
            }else if (nums[i] > thirdMax){
                thirdMax = nums[i];
            }
        }
        if (thirdMax == Long.MIN_VALUE){
            return (int) firstMax;
        }
        return (int) thirdMax;
    }
}

class Solution {
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long res = Long.MIN_VALUE;
        for (int num : nums){
            if (num > first){
                res = second;
                second = first;
                first = num;
            }else if (num > second && num < first){
                res = second;
                second = num;
            }else if (num > res && num < second){
                res = num;
            }
        }
        if (res == Long.MIN_VALUE){
            return (int)first;
        }
        return (int)res;
    }
}

Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.

Method 1: TLE
Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    public int findMaxLength(int[] nums) {
        int dp = 0;
        for (int i = 0; i < nums.length; i++){
            int one = 0;
            int zero = 0;
            for (int j = i; j < nums.length; j++){
                if (nums[j] == 1){
                    one++;
                }else{
                    zero++;
                }
                if (one == zero){
                    dp = Math.max(dp, one * 2);
                }
            }
        }
        return dp;
    }
}

Method 2:
In this approach, we make use of a countcount variable, which is used to store the relative number of ones and zeros 
encountered so far while traversing the array. The countcount variable is incremented by one for every \text{1}1 encountered 
and the same is decremented by one for every \text{0}0 encountered.

We start traversing the array from the beginning. If at any moment, the countcount becomes zero, it implies that we've encountered equal number of zeros and ones from the beginning till the current index of the array(ii). Not only this, another point to be noted is that if we encounter the same countcount twice while traversing the array, it means that the number of zeros and ones are equal between the indices 
corresponding to the equal countcount values. The following figure illustrates the observation for the sequence [0 0 1 0 0 0 1 1]:

https://leetcode.com/articles/contiguous-array/

class Solution {
    public int findMaxLength(int[] nums) {
        int max = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();//map stores sum-index pair
        map.put(0, -1); //initialize: sum = 0; index = -1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0){
                count--;
            }else{
                count++;
            }
            if (!map.containsKey(count)){
                map.put(count, i);
            }else{
                max = Math.max(max, i - map.get(count)); 
            }
        }
        return max;
    }
}

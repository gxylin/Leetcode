Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way 
you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each 
matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false,
to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.

https://leetcode.com/problems/matchsticks-to-square/discuss/95752/Java-DFS-solution-with-various-optimizations-(sorting-sequential-partition-DP)
https://leetcode.com/problems/matchsticks-to-square/discuss/95729/Java-DFS-Solution-with-Explanation

The idea is to assign one number into different sums and try different combinations. 
numIndex is increased by one to ensure only one visit. 

Method 1: without sorting... 423ms
class Solution {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int i : nums){
            sum += i;
            max = Math.max(max, i);
        }
        if (sum % 4 != 0 || max > sum / 4){
            return false;
        }
        int side = sum / 4;
        int[] sums = new int[4];
        return makeSqure(nums, nums.length - 1, sums, side);
    }
    private boolean makeSqure(int[] nums, int numsIndex, int[] sums, int target){
        if (numsIndex < 0){
            if (sums[0] == target && sums[1] == target && sums[2] == target && sums[3] == target){
                return true;
            }
            return false;
        }
        for (int i = 0; i < 4; i++){
            if (sums[i] + nums[numsIndex] > target){
                continue;
            }
            sums[i] += nums[numsIndex];
            if (makeSqure(nums, numsIndex-1, sums, target)){
                return true;
            }
            sums[i] -= nums[numsIndex];
        }
        return false;
    }
}

Method 2: with sorting 40ms refer to Leetcoe 698 for the genelized format for rationale
class Solution {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int i : nums){
            sum += i;
            max = Math.max(max, i);
        }
        if (sum % 4 != 0 || max > sum / 4){
            return false;
        }
        int side = sum / 4;
        int[] sums = new int[4];
        Arrays.sort(nums);
        return makeSqure(nums, nums.length - 1, sums, side);
    }
    private boolean makeSqure(int[] nums, int numsIndex, int[] sums, int target){
        if (numsIndex < 0){
            if (sums[0] == target && sums[1] == target && sums[2] == target && sums[3] == target){
                return true;
            }
            return false;
        }
        for (int i = 0; i < 4; i++){
            if (sums[i] + nums[numsIndex] > target){
                continue;
            }
            sums[i] += nums[numsIndex];
            if (makeSqure(nums, numsIndex-1, sums, target)){
                return true;
            }
            sums[i] -= nums[numsIndex];
        }
        return false;
    }
}


Backtracking: 
refer to Leetcoe 698 for the genelized format in which k = 4

class Solution {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int sum = 0;
        for (int i : nums){
            sum += i;
        }
        if (sum % 4 != 0){
            return false;
        }
        int target = sum / 4;
        int[] sums = new int[4];
        return canPartition(nums, sums, target, nums.length - 1);
    }
    private boolean canPartition(int[] nums, int[] sums, int target, int pos){
        if (pos < 0){
            for (int i = 0; i < 4; i++){
                if (sums[i] != target){
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < 4; i++){
            if (sums[i] + nums[pos] > target){
                continue;
            }
            sums[i] += nums[pos];
            if (canPartition(nums, sums, target, pos - 1)){
                return true;
            }
            sums[i] -= nums[pos];
        }
        return false;
    }
}

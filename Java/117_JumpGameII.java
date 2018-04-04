Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Have you met this question in a real interview? Yes
Example
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Method 1: like Coin Change O(n^2)
public class Solution {
    /*
     * @param A: A list of integers
     * @return: An integer
     */
    public int jump(int[] A) {
        if (A== null || A.length == 0){
            return 0;
        }
        int[] minSteps = new int[A.length];
        for (int i = 0; i < A.length; i++){
            minSteps[i] = Integer.MAX_VALUE;
        }
        minSteps[0] = 0;
        for (int i = 1; i < A.length; i++){
            for (int j = 0; j < i; j++){
                if (minSteps[j] != Integer.MAX_VALUE && A[j] + j >= i){
                    minSteps[i] = Math.min(minSteps[i], minSteps[j] + 1);
                }
            }
        }
        return minSteps[A.length - 1];
    }
}

Method 2: Like BFS O(n)
http://www.allenlipeng47.com/blog/index.php/2016/09/12/jump-game-ii/
class Solution {
    public int jump(int[] nums) {
        int level = 0;
        int nextMaxIndex = 0;
        int currMaxIndex = 0;
        for (int i = 0; i < nums.length; i++){
            if (currMaxIndex >= nums.length - 1){
                return level;
            }
            nextMaxIndex = Math.max(nextMaxIndex, i + nums[i]);
            if (i == currMaxIndex){
                level++;
                currMaxIndex = nextMaxIndex;
            } 
        }
        return 0;
    }
}

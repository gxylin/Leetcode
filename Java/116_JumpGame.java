Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

 Notice
This problem have two method which is Greedy and Dynamic Programming.

The time complexity of Greedy method is O(n).

The time complexity of Dynamic Programming method is O(n^2).

We manually set the small data set to allow you pass the test in both ways. This is just to let you learn 
how to use this problem in dynamic programming ways. If you finish it in dynamic programming ways,
you can try greedy method to make it accept again.

Method 1:
dynamic programming
Time complexity: O(n^2)
public class Solution {
    /*
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0){
            return false;
        }
        boolean[] can = new boolean[A.length];
        can[0] = true;
        for (int i = 1; i < A.length; i++){
            for (int j = 0; j <i; j++){
                if (can[j] && j + A[j] >= i){
                    can[i] = true;
                    break;
                }
            }
        }
        return can[A.length - 1];
    }
}

Method 2:  Greedy
Time complexity: O(n)
 class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++){
            if (max < i){
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
}

class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for (int i = 0; i < nums.length; i++){
            if (i > farthest){
                return false;
            }
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest >= nums.length - 1){
                return true;
            }
        }
        return false;
    }
}

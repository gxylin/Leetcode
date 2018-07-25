Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3 
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.


Method 1:
Time complexity: O(n^2)
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            long sum = 0;
            for (int j = i; j < nums.length; j++){
                sum += nums[j];
                if (sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE){//avoid overflow
                    break;
                }
                if (sum >= lower && sum <= upper){
                    count++;
                }
            }
        }
        return count;
    }
}

Method 2:

public class Solution {
    private class TreeNode {
        long val = 0;
        int count = 1;
        int leftSize = 0;
        int rightSize = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(long v) {
            this.val = v;
            this.count = 1;
            this.leftSize = 0;
            this.rightSize = 0;
        }
    }
    
    private TreeNode insert(TreeNode root, long val) {
        if(root == null) {
            return new TreeNode(val);
        } else if(root.val == val) {
            root.count++;
        } else if(val < root.val) {
            root.leftSize++;
            root.left = insert(root.left, val);
        } else if(val > root.val) {
            root.rightSize++;
            root.right = insert(root.right, val);
        }
        return root;
    }
    
    private int countSmaller(TreeNode root, long val) {
        if(root == null) {
            return 0;
        } else if(root.val == val) {
            return root.leftSize;
        } else if(root.val > val) {
            return countSmaller(root.left, val);
        } else {
            return root.leftSize + root.count + countSmaller(root.right, val);
        }
    }
    
    private int countLarger(TreeNode root, long val) {
        if(root == null) {
            return 0;
        } else if(root.val == val) {
            return root.rightSize;
        } else if(root.val < val) {
            return countLarger(root.right, val);
        } else {
            return countLarger(root.left, val) + root.count + root.rightSize;
        }
    }
    
    private int rangeSize(TreeNode root, long lower, long upper) {
        int total = root.count + root.leftSize + root.rightSize;
        int smaller = countSmaller(root, lower);    // Exclude everything smaller than lower
        int larger = countLarger(root, upper);      // Exclude everything larger than upper
        return total - smaller - larger;
    }
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums.length == 0) {
            return 0;
        }
        long[] sums = new long[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        TreeNode root = new TreeNode(sums[0]);
        int output = 0;
        for(int i = 1; i < sums.length; i++) {
            output += rangeSize(root, sums[i] - upper, sums[i] - lower);
            insert(root, sums[i]);
        }
        return output;
    }
}
https://leetcode.com/problems/count-of-range-sum/discuss/78003/Java-BST-solution-averagely-O(nlogn)

Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].

Time complexity: O(n)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }
    private TreeNode dfs(int[] nums, int start, int end){
        if (start > end){
            return null;
        }
        int max = nums[start];
        int index = start;
        for (int i = start; i <= end; i++){
            if (max < nums[i]){
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = dfs(nums, start, index-1);
        root.right = dfs(nums, index+1, end);
        return root;
    }
}

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }
    private TreeNode dfs(int[] nums, int start, int end){
        if (start > end){
            return null;
        }
        int index = start;
        for (int i = start; i <= end; i++){
            if (nums[index] < nums[i]){
                index = i;
            }
        }
        TreeNode root = new TreeNode(nums[index]);
        root.left = dfs(nums, start, index-1);
        root.right = dfs(nums, index+1, end);
        return root;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0){
            return null;
        }
        return build(nums, 0, nums.length - 1);
    }
    private TreeNode build(int[] nums, int start, int end){
        if (start > end){
            return null;
        }
        if (start == end){
            return new TreeNode(nums[start]);
        }
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = start; i <= end; i++){
            if (max < nums[i]){
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = build(nums, start, index-1);
        root.right = build(nums, index+1, end);
        return root;
    }
}

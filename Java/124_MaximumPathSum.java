Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node 
in the tree along the parent-child connections. The path must contain at least one node and does not 
need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

              
Similar as 687. Longest Univalue Path, Refer to the solution in 687
Method 1: use global variable
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
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumIncludeRoot(root);
        return max;
    }
    private int maxPathSumIncludeRoot(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = maxPathSumIncludeRoot(root.left);
        int right = maxPathSumIncludeRoot(root.right);
        int leftMax = Math.max(left, 0);
        int rightMax = Math.max(right, 0);
        max = Math.max(max, leftMax + rightMax + root.val);
        return root.val + Math.max(leftMax, rightMax);
    }
}

Method 2: without using global variable
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
    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathDown(root, max);
        return max[0];
    }
    private int maxPathDown(TreeNode root, int[] max){
        if (root == null){
            return 0;
        }
        int left = maxPathDown(root.left, max);
        int right = maxPathDown(root.right, max);
        int leftMax = Math.max(0, left);
        int rightMax = Math.max(0, right);
        max[0] = Math.max(max[0], leftMax + rightMax + root.val);
        return Math.max(leftMax, rightMax) + root.val;
    }
}

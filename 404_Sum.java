Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

Method 1: tree traversal
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
    int sum;
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root, root);
        return sum;
    }
    private void dfs(TreeNode root, TreeNode parent){
        if (root == null){
            return;
        }
        if (root == parent.left && root.left == null && root.right == null){
            sum += root.val;
        }
        dfs(root.left, root);
        dfs(root.right, root);
    }
}

Method 2: divide and conquer
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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null){
            return 0;
        }
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null){
            sum += root.left.val;
        }
        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);
        return sum;
    }
}

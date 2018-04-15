Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,

              5
             / \
            1   5
           / \   \
          5   5   5

return 4. 

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
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null){
            return 0;
        }
        int[] count = new int[1]; //serve similar as global variable
        helper(root, count);
        return count[0];
    }
    private boolean helper(TreeNode root, int[] count){
        if (root == null){
            return true;
        }
        boolean left = helper(root.left, count);
        boolean right = helper(root.right, count);
        if (left && right){
            if (root.left != null && root.left.val != root.val || root.right != null && root.right.val != root.val){
                return false;
            } 
            count[0]++;
            return true;
        }
        return false;
    }
}

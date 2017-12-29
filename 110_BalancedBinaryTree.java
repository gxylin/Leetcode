Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which 
the depth of the two subtrees of every node never differ by more than 1.

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
    class ResultType{
    boolean isBal;
    int depth;
    public ResultType(boolean isBal, int depth){
        this.isBal = isBal;
        this.depth = depth;
        }
    }
    public boolean isBalanced(TreeNode root) {
        ResultType result = helper(root);
        return result.isBal;
    }
    private ResultType helper(TreeNode root){
        if (root == null){
            return new ResultType(true, -1);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        boolean bal;
        if (left.isBal == true && right.isBal == true && Math.abs(left.depth - right.depth) <= 1){
            bal = true;
        }else{
            bal = false;
        }
        return new ResultType(bal, Math.max(left.depth, right.depth) + 1);
    }
}

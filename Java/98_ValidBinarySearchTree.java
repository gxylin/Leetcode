iven a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.

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
        boolean isValid;
        int min;
        int max;
        public ResultType(boolean isValid, int min, int max){
            this.isValid = isValid;
            this.min = min;
            this.max = max;
        }
    }
    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return true;
        }
        ResultType result = helper(root);
        return result.isValid;
    }
    private ResultType helper(TreeNode root){
        if (root == null){
            return new ResultType(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        if (!left.isValid || !right.isValid){
            return new ResultType(false, 0, 0);
        }
        //note that must check root.left != null for the corner case which root.val = Integer.MAX_VALUE  or Integer.MIN_VALUE
        if (root.left != null && left.max >= root.val || root.right != null && right.min <= root.val){
            return new ResultType(false, 0, 0);
        }
        
        return new ResultType(true, Math.min(left.min, root.val), Math.max(right.max, root.val));
    }
}

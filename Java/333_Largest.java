Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:

    10
    / \
   5  15
  / \   \ 
 1   8   7

The Largest BST Subtree in this case is the highlighted one.
The return value is the subtree's size, which is 3.

Follow up:
Can you figure out ways to solve it with O(n) time complexity? 

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
    class Result {
        int size;
        int lower;
        int upper;
        public Result (int size, int lower, int upper){
            this.size = size;
            this.upper = upper;
            this.lower = lower;
        }
    }
    int max = -1;
    public int largestBSTSubtree(TreeNode root) {
        if (root == null){
            return 0;
        }
        postOrder(root);
        return max;
    }
    private Result postOrder(TreeNode root){
        if (root == null){
            return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Result left = postOrder(root.left);
        Result right = postOrder(root.right);
        if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower){
            return new Result(-1, 0, 0);
        }
        int size = left.size + right.size + 1;
        max = Math.max(max, size);
        return new Result(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
}

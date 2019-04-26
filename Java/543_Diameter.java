Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a 
binary tree is the length of the longest path between any two nodes in a tree. This path may or
may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

https://leetcode.com/articles/diameter-of-binary-tree/
Method 1:
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
    private int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    private int depth(TreeNode root){
        if (root == null){
            return 0;
        }
        int L = depth(root.left);
        int R = depth(root.right);
        ans = Math.max(ans, L+R+1);
        return Math.max(L, R) + 1;
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
    public int diameterOfBinaryTree(TreeNode root) {
        int[] ans = new int[1];
        depth(root, ans);
        return ans[0] - 1 < 0 ? 0 : ans[0] - 1;
    }
    private int depth(TreeNode root, int[] ans){
        if (root == null){
            return 0;
        }
        int L = depth(root.left, ans);
        int R = depth(root.right, ans);
        ans[0] = Math.max(ans[0], L+R+1);
        return Math.max(L, R) + 1;
    }
}



Best: the same method as Binary Tree Maximum Path Sum, Longest Univalue Path
best solution:

If need find for n-ary tree, just find the longest and 2nd longest in all children, then similar as below
consider left as the longest,
and right as the 2nd longest

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=514267&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3046%255D%255Bvalue%255D%3D2%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D2%26searchoption%255B3109%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=2


class Solution {
    int max = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        maxDiaIncludeRoot(root);
        return max;
    }
    private int maxDiaIncludeRoot(TreeNode root){
        if (root == null){
            return -1;
        }
        int left = maxDiaIncludeRoot(root.left);
        int right = maxDiaIncludeRoot(root.right);
        int leftMax = left + 1;
        int rightMax = right + 1;
        max = Math.max(max, leftMax + rightMax);
        return Math.max(leftMax, rightMax);
    }
}

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        int[] ans = new int[1];
        diameterInlcudeRoot(root, ans);
        return ans[0];
    }
    private int diameterInlcudeRoot(TreeNode root, int[] ans){
        if (root == null){
            return 0;
        }
        int left = diameterInlcudeRoot(root.left, ans);
        int right = diameterInlcudeRoot(root.right, ans);
        ans[0] = Math.max(ans[0], left + right);
        return Math.max(left, right) + 1;
    }
}

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.


best solution:
preorder traversal
class Solution {
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }
    private int helper(TreeNode root, int sum){
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return sum * 10 + root.val;
        }
        sum = sum * 10 + root.val;
        return helper(root.right, sum) + helper(root.left, sum);
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
    int res = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfs(root, 0);
        return res;
    }
    private void dfs(TreeNode root, int sum){
        if (root.left == null && root.right == null){
            res += sum * 10 + root.val;
            return;
        }
        sum = sum * 10 + root.val;
        if (root.left != null){
            dfs(root.left, sum);
        }
        if (root.right != null){
            dfs(root.right, sum);
        }
    }
}

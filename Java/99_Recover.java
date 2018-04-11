Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
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
    TreeNode lastNode = new TreeNode(Integer.MIN_VALUE);
    TreeNode wrong1 = null;
    TreeNode wrong2 = null;
    public void recoverTree(TreeNode root) {
       // TreeNode wrong1 = new TreeNode(Integer.MIN_VALUE);
       // TreeNode wrong2 = new TreeNode(Integer.MIN_VALUE);  //pass by value is not working
       // TreeNode lastNode = new TreeNode(Integer.MIN_VALUE);
        dfs(root);
        int temp = wrong1.val;
        wrong1.val = wrong2.val;
        wrong2.val = temp;
    }
    private void dfs(TreeNode root){
        if (root == null){
            return;
        }
        dfs(root.left);
        if (wrong1 == null && root.val < lastNode.val){
                wrong1 = lastNode;
        }
        if (wrong1 != null && root.val < lastNode.val){
                wrong2 = root;
        }
        lastNode = root;
        dfs(root.right);
    }
    
}

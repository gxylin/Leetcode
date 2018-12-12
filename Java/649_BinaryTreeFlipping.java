Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or
empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

Have you met this question in a real interview? Yes
Example
Given a binary tree {1,2,3,4,5}

    1
   / \
  2   3
 / \
4   5
return the root of the binary tree {4,5,2,#,#,3,1}.

   4
  / \
 5   2
    / \
   3   1  


Method 1: Recursion
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: new root
     */
    TreeNode newRoot;
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null){
            return null;
        }
        dfs(root);
        return newRoot;
    }
    private void dfs(TreeNode cur){
        if (cur.left == null){
            newRoot = cur;
            return;
        }
        dfs(cur.left);
        cur.left.right = cur;
        cur.left.left = cur.right;
        cur.left = null;
        cur.right = null;
    }
}


Method 2: iteration (best)
public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: new root
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null){
            return root;
        }
        TreeNode cur = root;
        TreeNode next = null;
        TreeNode temp = null;
        TreeNode prev = null;
        while (cur != null){
            next = cur.left;
            cur.left = temp;
            
            temp = cur.right;
            cur.right = prev;
            
            prev = cur;
            cur = next;
        }
        return prev;
    }
}

We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.

Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

(Recall that the subtree of a node X is X, plus every node that is a descendant of X.)

Example 1:
Input: [1,null,0,0,1]
Output: [1,null,0,null,1]
 
Explanation: 
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.


Example 2:
Input: [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]



Example 3:
Input: [1,1,0,1,1,0,1,0]
Output: [1,1,0,1,1,null,1]


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
Method 1: must use post order traversal because it checks both children before checking root
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        TreeNode parent = new TreeNode(0);
        parent.left = root;
        postOrder(root, parent);
        return parent.left;
    }
    private void postOrder(TreeNode root, TreeNode parent){
        if (root == null){
            return;
        }
        postOrder(root.left, root);
        postOrder(root.right, root);
        if (root.left == null && root.right == null && root.val == 0){
            if (root == parent.right){
                parent.right = null;
            }else{
                parent.left = null;
            }
        }
    }
}

Method 2: divide conquer (Best solution)
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null){
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0){
            return null;
        }
        return root;
    }
}

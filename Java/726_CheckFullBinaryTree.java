A full binary tree is defined as a binary tree in which all nodes have either zero or two child nodes. 
Conversely, there is no node in a full binary tree, which has one child node. More information about
full binary trees can be found here.

Full Binary Tree
      1
     / \
    2   3
   / \
  4   5

Not a Full Binary Tree
      1
     / \
    2   3
   / 
  4   
Have you met this question in a real interview? Yes
Example
Given tree {1,2,3}, return true
Given tree {1,2,3,4}, return false
Given tree {1,2,3,4,5} return true

public class Solution {
    /*
     * @param : the given tree
     * @return: Whether it is a full tree
     */
    public boolean isFullTree(TreeNode root) {
        if (root == null){
            return true;
        }
        
        if (root.left == null && root.right != null || root.right == null && root.left != null){
            return false;
        }
        
        boolean left = isFullTree(root.left);
        boolean right = isFullTree(root.right);
        
        if (left && right){
            return true;
        }
        return false;
    }
}

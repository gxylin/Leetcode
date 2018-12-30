A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.


Method 1: preorder traversal
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null){
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node.val != root.val){
                return false;
            }
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return true;
    }
}

Method 2: inorder traversal
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
    public boolean isUnivalTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode node = stack.pop();
            if (node.val != root.val){
                return false;
            }
            curr = node.right;
        }
        return true;
    }
}

Method 3: recursion
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null){
            return true;
        }
        return isValid(root, root.val);
    }
    private boolean isValid(TreeNode node, int val){
        if (node == null){
            return true;
        }
        if (node.val != val){
            return false;
        }
        return isValid(node.left, val) && isValid(node.right, val);
    }
}

Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node)
or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

Example:

Input: [1,2,3,4,5]

    1
   / \
  2   3
 / \
4   5

Output: return the root of the binary tree [4,5,2,#,#,3,1]

   4
  / \
 5   2
    / \
   3   1  


Similar as reverse LinkedList, this is reverse tree
recursion: bottom up
iteration: top down
Method 1: recursion with global variable
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
    TreeNode newRoot = null;
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        inOrder(root);
        return newRoot;
    }
    private void inOrder(TreeNode root){
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null){
            if (newRoot == null){
                newRoot = root;
            }
            return;
        }
        inOrder(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        inOrder(root.right);
    }
}

Method 2: recursion without global variable
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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return inOrder(root);
    }
    private TreeNode inOrder(TreeNode root){
        if (root == null || (root.left == null && root.right == null)){
            return root;
        }
        TreeNode newRoot = inOrder(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}

public TreeNode upsideDownBinaryTree(TreeNode root) {
    if(root == null || root.left == null) {
        return root;
    }
    
    TreeNode newRoot = upsideDownBinaryTree(root.left);
    root.left.left = root.right;   // node 2 left children
    root.left.right = root;         // node 2 right children
    root.left = null;
    root.right = null;
    return newRoot;
}

Method 3: Iteration (best solution)
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode prev = null;
        TreeNode curr = root;
        TreeNode next = null;
        TreeNode temp = null;
        while (curr != null){
            next = curr.left;
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;
            
            prev = curr;
            curr = next;
        }
        return prev;
    }
}


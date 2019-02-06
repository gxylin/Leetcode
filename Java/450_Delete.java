Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7

Method: 
https://leetcode.com/problems/delete-node-in-a-bst/discuss/93296/Recursive-Easy-to-Understand-Java-Solution
Divde and Conquer
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }
        if (key < root.val){
            root.left = deleteNode(root.left, key);
        }else if (key > root.val){
            root.right = deleteNode(root.right, key);
        }else{
            if (root.left == null){
                return root.right;
            }else if (root.right == null){
                return root.left;
            }
            TreeNode rightSuccessor = findMin(root.right);
            root.val = rightSuccessor.val;
            root.right = deleteNode(root.right, rightSuccessor.val);
        }
        return root;
    }
    private TreeNode findMin(TreeNode root){
        while (root.left != null){
            root = root.left;
        }
        return root;
        
    }
}

Method 2: Use inorderSuccessor
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }
        if (key < root.val){
            root.left = deleteNode(root.left, key);
        }else if (key > root.val){
            root.right = deleteNode(root.right, key);
        }else{
            if (root.left == null){
                return root.right;
            }else if (root.right == null){
                return root.left;
            }
            TreeNode rightSuccessor = inorderSuccessor(root, root.val); //not root.right
            root.val = rightSuccessor.val;
            root.right = deleteNode(root.right, rightSuccessor.val);
        }
        return root;
    }
    private TreeNode inorderSuccessor(TreeNode root, int val){
        if (root == null){
            return root;
        }
        if (val >= root.val){
            return inorderSuccessor(root.right, val);
        }else{
            TreeNode left = inorderSuccessor(root.left, val);
            return left != null ? left : root;
        }    
    }
}



Better Version:
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode dummy = new TreeNode(-1);
        TreeNode parent = dummy;
        dummy.left = root;
        delete(parent, root, key);
        return dummy.left;
    }
    private void delete(TreeNode parent, TreeNode root, int key){
        if (root == null){
            return;
        }
        if (key == root.val){
            if (root.right == null){
                if (root == parent.left){
                    parent.left = root.left;
                }else{
                    parent.right = root.left;
                }
            }else{
                if (root == parent.left){
                    parent.left = root.right;
                }else{
                    parent.right = root.right;
                }
                TreeNode node = root.right;
                while (node.left != null){
                    node = node.left;
                }
                node.left = root.left;
            }
            root.left = null;
            root.right = null;
        }else if (key > root.val){
            delete(root, root.right, key);
        }else{
            delete(root, root.left, key);
        }
    }
}

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        delete(root, dummy, key);
        return dummy.left;
    }
    private void delete(TreeNode root, TreeNode parent, int key){
        if (root == null){
            return;
        }
        if (root.val == key){
            if (root.left == null){
                if (root == parent.left){
                    parent.left = root.right;
                }else{
                    parent.right = root.right;
                }
            }else{
                if (root == parent.left){
                    parent.left = root.left;
                }else{
                    parent.right = root.left;
                }
                TreeNode node = root.left;
                while (node.right != null){
                    node = node.right;
                }
                node.right = root.right;
            }
        }else if (root.val > key){
            delete(root.left, root, key);
        }else{
            delete(root.right, root, key);
        }
    }
}

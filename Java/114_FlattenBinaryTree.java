Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.


Method 1: Divide && Conquer
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
    public void flatten(TreeNode root) {
        getLastNode(root);
    }
    //flatten and get the last node
    private TreeNode getLastNode(TreeNode root){
        if (root == null){
            return root;
        }
        TreeNode leftLastNode = getLastNode(root.left);
        TreeNode rightLastNode = getLastNode(root.right);
                
        if (leftLastNode != null){
            leftLastNode.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        if (rightLastNode != null){
            return rightLastNode;
        }
        if (leftLastNode != null){
            return leftLastNode;
        }
        return root;
    }
}

Method 2: Preorder traversal
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
    private TreeNode lastNode = null;
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        if (lastNode != null){
            lastNode.right = root;
            lastNode.left = null;
        }
        lastNode = root;
        TreeNode temp = root.right;
        flatten(root.left);
        flatten(temp);
    }
}

With extra space and just the same as standard preorder traversal
class Solution {
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode curr = stack.pop();
            if (curr.right != null){
                stack.push(curr.right);
            }
            if (curr.left != null){
                stack.push(curr.left);
            }
            if (!stack.isEmpty()){
                curr.right = stack.peek();
                curr.left = null;
            }
        }
    }
}


Best solution:
Because the required traversal is preorder, we think/work from reverse preorder traversal
class Solution {
    TreeNode lastNode = null;
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        //reverse preorder
        flatten(root.right);
        flatten(root.left);
        root.right = lastNode;
        root.left = null;
        lastNode = root;
    }
}

// helper function takes in the previous head, do the flattening and returns the head of the flatten binary tree
class Solution {
    public void flatten(TreeNode root) {
        root = reversePreorder(root, null);
    }
    private TreeNode reversePreorder(TreeNode root, TreeNode prev){
        if (root == null){
            return prev;
        }
        prev = reversePreorder(root.right, prev);//assign prev to the root.right
        prev = reversePreorder(root.left, prev);//assign prev to the root.left
        root.right = prev;
        root.left = null;
   //     prev = root;
        return root;
    }
}

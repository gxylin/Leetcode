Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \   
    1   3
         \
          4

Method 1: recursion
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            root = new TreeNode(val);
        }else{
            insert(root, val);
        }
        return root;
    }
    private void insert(TreeNode root, int val){
        if (root.val < val){
            if (root.right == null){
                TreeNode node = new TreeNode(val);
                root.right = node;
                return;
            }
            insertIntoBST(root.right, val);
        }else{
            if (root.left == null){
                TreeNode node = new TreeNode(val);
                root.left = node;
                return;
            }
            insertIntoBST(root.left, val);
        }
    }
}

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }else if (root.val < val){
            root.right = insertIntoBST(root.right, val);
        }else{
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}

Method 2: iteration
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode cur = root;
        while (cur != null){
            if (cur.val < val){
                if (cur.right == null){
                    cur.right = new TreeNode(val);
                    break;
                }
                cur = cur.right;
            }else{
                if (cur.left == null){
                    cur.left = new TreeNode(val);
                    break;
                }
                cur = cur.left;
            }
        }
        return root;
    }
}

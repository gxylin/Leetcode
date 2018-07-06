Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.

Example 1:

Input: root = [2,1,3], p = 1

  2
 / \
1   3

Output: 2

Example 2:

Input: root = [5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /   
1

Output: null


Method 1: tree traversal
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
    List<TreeNode> list = new ArrayList<TreeNode>();
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inOrder(root, list);
        for (int i = 0; i < list.size(); i++){
            TreeNode node = list.get(i);
            if (node.val == p.val){
                if (i == list.size() - 1){
                    return null;
                }else{
                    return list.get(i+1);
                }
            }
        }
        return null;
    }
    private void inOrder(TreeNode root, List<TreeNode> list){
        if (root == null){
            return;
        }
        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }
}

Method 2: divide conquer
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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null){
            return null;
        }
        if (p.val >= root.val){
            return inorderSuccessor(root.right, p);
        }
        TreeNode left = inorderSuccessor(root.left, p);
        return left == null ? root : left;
    }
}

For Predecessor
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null){
            return null;
        }
        if (p.val <= root.val){
            return inorderSuccessor(root.left, p);
        }
        TreeNode right = inorderSuccessor(root.right, p);
        return right == null ? root : right;
    }
}

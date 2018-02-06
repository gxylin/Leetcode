Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
   
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    private TreeNode build(int[] inorder, int startI, int endI, int[] postorder, int startP, int endP){
        if (startI > endI || startP > endP){
            return null;
        }
        TreeNode root = new TreeNode(postorder[endP]);
        int index = findIndex(inorder, startI, endI, postorder[endP]);
        root.left = build(inorder, startI, startI+index-1, postorder, startP, startP+index-1);
        root.right = build(inorder, startI+index+1, endI, postorder, startP+index, endP-1);
        return root;
    }
    private int findIndex(int[] A, int start, int end, int target){
        for (int i = start; i <= end; i++){
            if (A[i] == target){
                return i - start;
            }
        }
        return -1;
    }
}

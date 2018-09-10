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
Method 1: Recursion
Time complexity: O(nlogn)
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

Method 2: Recursion + HashMap
Time complexity: O(n)
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return dfs(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
    private TreeNode dfs(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd, Map<Integer, Integer> map){
        if (iStart > iEnd || pStart > pEnd){
            return null;
        }
        TreeNode root = new TreeNode(postorder[pEnd]);
        int deltaIndex = map.get(postorder[pEnd]) - iStart;
        root.left = dfs(inorder, iStart, iStart + deltaIndex - 1, postorder, pStart, pStart + deltaIndex -1, map);
        root.right = dfs(inorder, iStart + deltaIndex + 1, iEnd, postorder, pStart + deltaIndex, pEnd - 1, map);
        return root;
    }
}

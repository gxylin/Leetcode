Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);        
    }
    private TreeNode build(int[] preorder, int startP, int endP, int[] inorder, int startI, int endI){
        if (startP > endP || startI > endI){
            return null;
        }
        TreeNode root = new TreeNode(preorder[startP]);
        int index = findIndex(inorder, startI, endI, preorder[startP]);
        
        root.left = build(preorder, startP+1, startP+index, inorder, startI, startI+index-1);
        root.right = build(preorder, startP+1+index, endP, inorder, startI+index+1, endI);
        return root;
    }
    private int findIndex(int[] A, int start, int end, int target){
        for (int i = start; i <= end; i++){
            if (A[i] == target){
                return i - start;//must use relative distance index
            }
        }
        return -1;
    }
}

Method 2: Recursion + HashMap
Time complexity: O(n)
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }
    private TreeNode dfs(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd, Map<Integer, Integer> map){
        if (pStart > pEnd || iStart > iEnd){
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int deltaIndex = map.get(preorder[pStart]) - iStart;
        root.left = dfs(preorder, pStart + 1, pStart + deltaIndex, inorder, iStart, iStart + deltaIndex - 1, map);
        root.right = dfs(preorder, pStart + deltaIndex + 1, pEnd, inorder, iStart + deltaIndex + 1, iEnd, map);
        return root;
    }
}

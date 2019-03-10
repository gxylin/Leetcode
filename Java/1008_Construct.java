Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

 

Note: 

1 <= preorder.length <= 100
The values of preorder are distinct.


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
    public TreeNode bstFromPreorder(int[] preorder) {
        return dfs(0, preorder.length - 1, preorder);
    }
    private TreeNode dfs(int start, int end, int[] preorder){
        if (start > end){
            return null;
        }
        if (start == end){
            return new TreeNode(preorder[start]);
        }
        int pivot = preorder[start];
        TreeNode root = new TreeNode(pivot);
        int index = findFirstLarge(pivot, preorder, start+1, end);
        if (index != -1){
            root.left = dfs(start+1, index-1, preorder);
            root.right = dfs(index, end, preorder);
        }else{//if there is larger one, then no right tree
            root.left = dfs(start+1, end, preorder);
        }
        return root;
    }
    private int findFirstLarge(int num, int[] A, int start, int end){
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] > num){
                end = mid;
            }else{
                start = mid;
            }
        }
        if (A[start] > num){
            return start;
        }else if (A[end] > num){
            return end;
        }
        return -1;
    }
}

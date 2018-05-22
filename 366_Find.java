Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree

          1
         / \
        2   3
       / \     
      4   5    

Returns [4, 5, 3], [2], [1].

Explanation:

1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         / 
        2          

2. Now removing the leaf [2] would result in this tree:

          1          

3. Now removing the leaf [1] would result in the empty tree:

          []         

Returns [4, 5, 3], [2], [1]. 

Method 1:
O(n^2)
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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        while (root != null){
            List<Integer> item = new ArrayList<>();
            root = dfs(item, root);
            result.add(item);
        }
        return result;
    }
    private TreeNode dfs(List<Integer> item, TreeNode root){
        if (root == null){
            return root;
        }
        if (root.left == null && root.right == null){
            item.add(root.val);
            root = null;
            return null;
        }
        root.left = dfs(item, root.left);
        root.right = dfs(item, root.right);
        return root;
    }
}


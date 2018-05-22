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
Time complexity: O(n^2)
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

The following method is wrong because:
Java does manipulate objects by reference, and all object variables are references. 
          However, Java doesn't pass method arguments by reference; it passes them by value. 
          Java copies and passes the reference by value, not the object.
Hence, your just modified the copy of root and set it to null instead of the original root. So your code will run forever and TLE. 
          The workaround is to return the root so it can be modified as shown in method 1

 WRONG SOLUTION!!!
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        while (root != null){
            List<Integer> item = new ArrayList<>();
            dfs(item, root);
            result.add(item);
        }
        return result;
    }
    private void dfs(List<Integer> item, TreeNode root){
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null){
            item.add(root.val);
            root = null;
            return;
        }
        dfs(item, root.left);
        dfs(item, root.right);
    }
}

Method 2: 
Time complexity: O(n)
bottom-up approach. The key is to find the height of each node. Here the definition of height is:
The height of a node is the number of edges from the node to the deepest leaf. --CMU 15-121 Binary Trees
According to the definition, the height of leaf is 0. h(node) = 1 + max(h(node.left), h(node.right)).
The height of a node is also the its index in the result list (res). For example, leaves, whose heights are 0, are stored in res[0]. 
Once we find the height of a node, we can put it directly into the result.
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        height(result, root);
        return result;
    }
    private int height(List<List<Integer>> result, TreeNode root){
        if (root == null){
           return -1;
        }
        int left = height(result, root.left);
        int right = height(result, root.right);
        int currHeight = 1 + Math.max(left, right);
        if (currHeight == result.size()){
            result.add(new ArrayList<Integer>());
        }
        result.get(currHeight).add(root.val);
        root = null;
        return currHeight;
        
    }
}

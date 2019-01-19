 Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
  The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5

Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1

Longest consecutive sequence path is 2-3,not3-2-1, so return 2. 


Method: tranverse
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
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root, null, 0);
        return max;
    }
    private void helper(TreeNode root, TreeNode parent, int lengthWithoutRoot){
        if (root == null){
            return;
        }
        int len = (parent != null && parent.val + 1 == root.val) ? lengthWithoutRoot + 1 : 1;
        helper(root.left, root, len);
        helper(root.right, root, len);
        max = Math.max(max, len);
    }
}

Method 2: Best solution
Refer to https://github.com/optimisea/Leetcode/blob/master/Java/549_BinaryTree.java
Refer to https://github.com/optimisea/Leetcode/blob/master/Java/687_Longest.java

int max = 0;
public int longestConsecutive(TreeNode root){
    longestIncludeRoot(root);
    return max;
}
private int longestIncludeRoot(TreeNode root){
  if (root == null){
      return 0;
  }
  int left = longestIncludeRoot(root.left);
  int right = longestIncludeRoot(root.right);
  int leftIncludeRoot = 1;
  int rightIncludeRoot = 1;
  if (root.left != null && (root.left.val > root.val){
        leftIncludeRoot = left + 1;
  }
  if (root.right != null && root.right.val > root.val){
        rightIncludeRoot = right + 1);
  }
  max = Math.max(max, Math.max(leftIncludeRoot, rightIncludeRoot));
  return Math.max(leftIncludeRoot, rightIncludeRoot);
}

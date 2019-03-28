Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] 
(R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input: 
    1
   / \
  0   2

  L = 1
  R = 2

Output: 
    1
      \
       2
Example 2:
Input: 
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output: 
      3
     / 
   2   
  /
 1
 
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
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null){
            return null;
        }
        if (root.val == L){
            root.right = trimBST(root.right, L, R);
            root.left = null;
            return root;
        }
        if (root.val == R){
            root.left = trimBST(root.left, L, R);
            root.right = null;
            return root;
        }
        if (L < root.val && root.val < R){
            root.left = trimBST(root.left, L, root.val);
            root.right = trimBST(root.right, root.val, R);
            return root;
        }
        if (L > root.val){
            return trimBST(root.right, L, R);
        }
        if (R < root.val){
            return trimBST(root.left, L, R);
        }
        return null;
    }
}


class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null){
            return null;
        }
        if (L > root.val){
            return trimBST(root.right, L, R);
        }
        if (R < root.val){
            return trimBST(root.left, L, R);
        }
        root.left = trimBST(root.left, L, root.val);
        root.right = trimBST(root.right, root.val, R);
        return root;
    }
}

Best solutoin:
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return root;
        if (root.val > R) return trimBST(root.left, L, R);
        if (root.val < L) return trimBST(root.right, L, R);

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}


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
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null){
            return root;
        }
        if (root.val > R){
            return trimBST(root.left, L, R);
        }else if (root.val < L){
            return trimBST(root.right, L, R);
        }else if (root.val == R){
            root.right = null;
            root.left = trimBST(root.left, L, R);
            return root;
        }else if (root.val == L){
            root.left = null;
            root.right = trimBST(root.right, L, R);
            return root;
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}

Complexity Analysis

    Time Complexity: O(N), where N is the total number of nodes in the given tree. We visit each node at most once.

    Space Complexity: O(N). Even though we don't explicitly use any additional memory, the call stack of our recursion could be as large as the number of nodes in the worst case.

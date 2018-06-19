Given a binary search tree with non-negative values, find the minimum absolute difference 
between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).

Method 1:
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
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        Collections.sort(list);
        int diff = Integer.MAX_VALUE;
        for (int i = 0 ; i < list.size() - 1; i++){
            int candid = Math.abs(list.get(i+1) - list.get(i));
            if (candid < diff){
                diff = candid;
            }
        }
        return diff;
    }
    private void preOrder(TreeNode root, List<Integer> list){
        if (root == null){
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }
}

Since this is a BST, the inorder traversal of its nodes results in a sorted list of values. Thus, 
the minimum absolute difference must occur in any adjacently traversed nodes. 
   I use the global variable "prev" to keep track of each node's inorder predecessor.
Method 2:
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
    int ans = Integer.MAX_VALUE;
    TreeNode prev = null;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return ans;
    }
    private void inOrder(TreeNode root){
        if (root == null){
            return;
        }
        inOrder(root.left);
        if (prev != null){
            ans = Math.min(ans, Math.abs(root.val - prev.val));
        }
        prev = root;
        inOrder(root.right);
    }
}

Method 3:
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
    TreeNode prev = null;
    public int getMinimumDifference(TreeNode root) {
        int[] ans = new int[1];
        ans[0] = Integer.MAX_VALUE;
        inOrder(root, ans);
        return ans[0];
    }
    private void inOrder(TreeNode root, int[] ans){
        if (root == null){
            return;
        }
        inOrder(root.left, ans);
        if (prev != null){
            ans[0] = Math.min(ans[0], root.val - prev.val);
        }
        prev = root;
        inOrder(root.right, ans);
    }
}


Wrong answer below,

the answer below is the minimum difference between root and children
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
    public int minDiffInBST(TreeNode root) {
        int[] ans = new int[1];
        ans[0] = Integer.MAX_VALUE;
        inOrder(root, null, ans);
        return ans[0];
    }
    private void inOrder(TreeNode root, TreeNode prev, int[] ans){
        if (root == null){
            return;
        }
        inOrder(root.left, root, ans);
        if (prev != null){
            ans[0] = Math.min(ans[0], Math.abs(root.val - prev.val));
        }
        inOrder(root.right, root, ans);
    }
}

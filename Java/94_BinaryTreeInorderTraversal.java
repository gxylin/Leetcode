Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?


Method 1: divide & conquer
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);

        result.addAll(left);
        result.add(root.val);
        result.addAll(right);
        return result;
    }
}

Method 2: recursion
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(result, root);
        return result;
    }
    private void helper(List<Integer> result, TreeNode node){
        if (node == null){
            return;
        }
        helper(result, node.left);
        result.add(node.val);
        helper(result, node.right);
    }
}
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()){
            if (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            if (curr == null && !stack.isEmpty()){
                TreeNode node = stack.pop();
                res.add(node.val);
                curr = node.right;
            }
        }
        return res;
    }
}


Method 3: iteration:
Template: https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
Best version:
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            while (node != null){
                stack.psh(node);
                node = node.left;
            }
            TreeNode curr = stack.pop();
            res.add(curr.val);
            node = curr.right;
        }
        return res;
    }
}

Tree Iteration template
https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)

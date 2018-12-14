Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]

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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        if (root.left == null && root.right == null){
            result.add(Integer.toString(root.val));
            return result;
        }
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        
        for (String str : left){
            result.add(root.val + "->" + str);
        }
        for (String str : right){
            result.add(root.val + "->" + str);
        }
        return result;
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        if (!left.isEmpty()){
            for (String s : left){
                res.add(root.val + "->" + s);
            }
        }
        if (!right.isEmpty()){
            for (String s : right){
                res.add(root.val + "->" + s);
            }   
        }
        if (left.isEmpty() && right.isEmpty()){
            res.add(String.valueOf(root.val));
        }
        return res;
    }
}

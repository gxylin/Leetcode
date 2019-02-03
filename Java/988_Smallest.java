Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.

Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

(As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)

 
 
Method 1:
class Solution {
    String res = "";
    public String smallestFromLeaf(TreeNode root) {
        if (root == null){
            return "";
        }
        dfs(root, "");
        return res; 
    }
    private void dfs(TreeNode root, String str){
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null){
            str = (char)(root.val + 'a') + str;
            if (res.equals("") || str.compareTo(res) < 0){
                res = str;// or res = new String(str);
            }
            return;
        }
        str = (char)(root.val + 'a') + str;
        dfs(root.left, str);
        dfs(root.right, str);
    }
}

Method 2: without global
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
    public String smallestFromLeaf(TreeNode root) {
        if (root == null){
            return "";
        }
        String[] res = new String[1];
        dfs(root, "", res);
        return res[0]; 
    }
    private void dfs(TreeNode root, String str, String[] res){
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null){
            str = (char)(root.val + 'a') + str;
            if (res[0] == null || str.compareTo(res[0]) < 0){
                res[0] = str;
            }
            return;
        }
        str = (char)(root.val + 'a') + str;
        dfs(root.left, str, res);
        dfs(root.right, str, res);
    }
}

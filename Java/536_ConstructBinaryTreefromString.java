You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:

Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   

Note:

    There will only be '(', ')', '-' and '0' ~ '9' in the input string.
    An empty tree is represented by "" instead of "()".


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
           

           
Method: dfs - pre order traversal

The string has 3 parts : root (left)(right). However, (left) and (right) might be empty.
Actually, after we find the left part, we can break the loop and build right child using 
the rest string directly if the rest is not empty.


class Solution {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0 || s.equals("")){
            return null;
        }
        int index = s.indexOf('(');
        int num = index == -1 ? Integer.parseInt(s) : Integer.parseInt(s.substring(0, index));
        TreeNode root = new TreeNode(num);
        if (index == -1){
            return root;
        }
        int i = index;
        int count = 0;
        while (i < s.length()){
            if (s.charAt(i) == '('){
                count++;
            }else if (s.charAt(i) == ')'){
                count--;
            }
            if (count == 0){
                root.left = str2tree(s.substring(index + 1, i));
                break;
            }
            i++;
        }
        if (i < s.length() - 1){
            root.right = str2tree(s.substring(i+2, s.length() - 1));
        }
        return root;
    }
}

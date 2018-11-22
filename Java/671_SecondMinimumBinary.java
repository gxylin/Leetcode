Given a non-empty special binary tree consisting of nodes with the non-negative value, 
where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, 
then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of 
all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.


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
    long firstMin = Long.MAX_VALUE;
    long secondMin = Long.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root);
        if (secondMin == Long.MAX_VALUE){
            return -1;
        }
        return (int) secondMin;
    }
    private void dfs(TreeNode root){
        if (root == null){
            return;
        }
        if (root.val < firstMin){
            secondMin = firstMin;
            firstMin = root.val;
        }else if (root.val > firstMin && root.val < secondMin){
            secondMin = root.val;
        }
        dfs(root.left);
        dfs(root.right);
    }
}


Better version:
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
    public int findSecondMinimumValue(TreeNode root) {
        int[] res = new int[2];
        res[0] = Integer.MAX_VALUE;
        res[1] = Integer.MAX_VALUE;
        preorder(root, res);
        return res[1] == Integer.MAX_VALUE ? -1 : res[1];
    }
    private void preorder(TreeNode root, int[] res){
        if (root == null){
            return;
        }
        if (root.val < res[0]){
            res[1] = res[0];
            res[0] = root.val;
        }else if (root.val > res[0] && root.val < res[1]){
            res[1] = root.val;
        }
        preorder(root.left, res);
        preorder(root.right, res);
    }
}

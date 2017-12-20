Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.

 Notice

LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum sum and the given binary tree is not an empty tree.

Have you met this question in a real interview? Yes
Example
Given a binary tree:

     1
   /   \
 -5     2
 / \   /  \
0   3 -4  -5 
return the node with value 3.

public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: the maximum weight node
     */
    int max = Integer.MIN_VALUE;
    TreeNode node = null;
    public TreeNode findSubtree(TreeNode root) {
       int sum = dfs(root);
       return node;
    }
    private int dfs(TreeNode cur){
        if (cur == null){
            return 0;
        }
        int leftSum = dfs(cur.left);
        int rightSum = dfs(cur.right);
        int sum = leftSum + rightSum + cur.val;
        if (max < sum){
            max = sum;
            node = cur;
        }
        return sum;
    }
}

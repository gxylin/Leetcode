Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Have you met this question in a real interview? Yes
Example
Given a binary search Tree `{5,2,13}｀:

              5
            /   \
           2     13
Return the root of new tree

             18
            /   \
          20     13

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

算法流程:
1. 定义累计求和变量sum=0
2. 按照右中左这样的“反中根遍历”,依次访问每个节点 1 更新累计求和变量 sum = sum + 当前节点的值
2 更新当前节点的值 = sum
按照DFS的定义宏观理解:
我们这里DFS函数的定义是什么?任务是什么? 
1. “反中根遍历”的顺序访问cur这颗树每个节点 
2. sum要在当前的基础上要加上所有访问元素的值 
3. 所有访问元素要变成大于等于它的元素之和

public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: the new root
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
       dfs(root);
       return root;
    }
    private void dfs(TreeNode cur){
        if (cur == null){
            return;
        }
        dfs(cur.right);
        sum += cur.val;
        cur.val = sum;
        dfs(cur.left);
    }
}

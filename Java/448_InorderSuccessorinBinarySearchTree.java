Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.

If the given node has no in-order successor in the tree, return null.

 Notice

It's guaranteed p is one node in the given tree. (You can directly compare the memory address to find p)

Have you met this question in a real interview? Yes
Example
Given tree = [2,1] and node = 1:

  2
 /
1
return node 2.

Given tree = [2,1,3] and node = 2:

  2
 / \
1   3
return node 3.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

• 节点的successor 后继就是比给定节点大的所有节点中最小的那个
思路:
• 考虑p和root之间的关系(p 为给定的节点,要找到p节点的后继)
• 两种情况:
1. root的值 =< p的值 答案就在右子树中
2. root的值 > p的值 答案有两个备选: a) 就是root
b) 左子树中继续找,并且如果找到就一定是它,因为左子树的中的元素都比根小


public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null){
            return null;
        }
        if (p.val >= root.val){
            return inorderSuccessor(root.right, p);
        }else{
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }
}

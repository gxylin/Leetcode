Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
 DFS
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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0){
            return new ArrayList<TreeNode>();
        }
        return dfs(1, n);
    }
    private List<TreeNode> dfs(int start, int end){
        List<TreeNode> list = new ArrayList<>();
        if (start == end){
            list.add(new TreeNode(start));
            return list;
        }
        if (start > end){
            list.add(null);
            return list;
        }
        
        for (int i = start; i <= end; i++){
            List<TreeNode> left = dfs(start, i - 1);
            List<TreeNode> right = dfs(i + 1, end);
            for (TreeNode lnode : left){
                for (TreeNode rnode : right){
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }
        }
        return list;
    }
}

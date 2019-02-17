In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

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
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null){
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            boolean foundX = false;
            boolean foundY = false;
            int parentX = -1;
            int parentY = -1;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                    if (node.left.val == x){
                        parentX = node.val;
                        foundX = true;
                    }
                    if (node.left.val == y){
                        parentY = node.val;
                        foundY = true;
                    }
                }
                if (node.right != null){
                    queue.offer(node.right);
                    if (node.right.val == x){
                        parentX = node.val;
                        foundX = true;
                    }
                    if (node.right.val == y){
                        parentY = node.val;
                        foundY = true;
                    }
                }
            }
            if (foundX && foundY){
                if (parentX == parentY){
                    return false;
                }
                return true;
            }else if (foundX && !foundY || !foundX && foundY){
                return false;
            }
        }
        return false;
    }
}

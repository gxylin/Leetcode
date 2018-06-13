You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

Method 1: BFS
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}

Method 2: DFS
Just a simple pre-order traverse idea. Use depth to expand result list size and put the max value in the appropriate position.

class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(res, root, 0);
        return res;
    }
    private void preOrder(List<Integer> res, TreeNode root, int depth){
        if (root == null){
            return;
        }
        if (depth == res.size()){
            res.add(root.val);
        }else{
            res.set(depth, Math.max(root.val, res.get(depth)));
        }
        preOrder(res, root.left, depth+1);
        preOrder(res, root.right, depth+1);
    }
}

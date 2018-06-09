Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.


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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            double sum = 0.0;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(sum / size);
        }
        return res;
    }
}

Method 2: DFS
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
    class Node {
        double sum;
        int count;
        public Node (double sum, int count){
            this.sum = sum;
            this.count = count;
        }
    }
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        List<Node> list = new ArrayList<>();
        dfs(root, list, 0);
        for (int i = 0; i < list.size(); i++){
            res.add(list.get(i).sum / list.get(i).count);
        }
        return res;
    }
    private void dfs(TreeNode root, List<Node> list, int level){
        if (root == null){
            return;
        }
        if (list.size() == level){
            list.add(new Node(root.val, 1));
        }else{
            list.get(level).sum += root.val;
            list.get(level).count++;
        }
        dfs(root.left, list, level + 1);
        dfs(root.right, list, level + 1);
    }
}

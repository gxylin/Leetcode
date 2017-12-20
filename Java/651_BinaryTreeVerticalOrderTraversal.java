Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Have you met this question in a real interview? Yes
Example
Given binary tree {3,9,20,#,#,15,7}

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
Return its vertical order traversal as:
[[9],[3,15],[20],[7]]

Given binary tree {3,9,8,4,0,1,7}

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
Return its vertical order traversal as:
[[4],[9],[3,0,1],[8],[7]]

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

思路:
• 这一题有三个顺序:
– 第一,列数从小到大
– 第二,列数相同时行数从小到大
– 第三,列数行数都相同时,从左到右
怎样计算列数?
– Root为0 向左-1 向右+1
• 怎样保证第一个顺序,列数从小到大 – Hash
怎样保证第二个顺序,行数从小到大 – BFS 一行一行的访问


public class Solution {
    /*
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> qNode = new LinkedList<>();
        Queue<Integer> qCol = new LinkedList<>();
        
        qNode.offer(root);
        qCol.offer(0);
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while (!qNode.isEmpty()){
            TreeNode node = qNode.poll();
            int col = qCol.poll();
            
            if (!map.containsKey(col)){
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);
            
            min = Math.min(min, col);
            max = Math.max(max, col);
            
            if (node.left != null){
                qNode.offer(node.left);
                qCol.offer(col - 1);
            }
            if (node.right != null){
                qNode.offer(node.right);
                qCol.offer(col + 1);
            }
        }
        for (int i = min; i <= max; i++){
            result.add(map.get(i));
        }
        return result;
    }
}

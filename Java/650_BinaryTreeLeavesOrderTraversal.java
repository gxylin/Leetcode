Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Have you met this question in a real interview? Yes
Example
Given binary tree:

          1
         / \
        2   3
       / \     
      4   5    
Returns [[4, 5, 3], [2], [1]].

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

• 看看每个子树的高度?
– 其实第k层包含的就是所有高度为k的节点
同一层中,要求的顺序是从左往右 (use postorder traversal to keep the order)
所以怎么求高度?怎么保存答案? – DFS计算节点高度,hash 保存答案


Method 1: label node from bottom to top, e.g., bottom: 0...
public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: collect and remove all leaves
     */
    Map<Integer, List<Integer>> map = new HashMap<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        int depth = dfs(root);
        for (int i = 0; i <= depth; i++){
            result.add(map.get(i));
        }
        return result;
    }
    private int dfs(TreeNode cur){
        if (cur == null){
            return -1;
        }
        int leftDepth = dfs(cur.left);
        int rightDepth = dfs(cur.right);
        int depth = Math.max(leftDepth, rightDepth) + 1;
        if (!map.containsKey(depth)){
            map.put(depth, new ArrayList<Integer>());
        }
        map.get(depth).add(cur.val);
        return depth;
    }
}

Method 2: label node (label node from top to bottom, e.g. top: 0,1,2........)
Similar to https://github.com/optimisea/Leetcode/blob/master/Java/865_Smallest.java

iven a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]


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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        List<Integer> list = new ArrayList<Integer>();
        list.add(root.val);
        dfs(result, list, root, sum);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, TreeNode root, int sum){
        if(root.left == null && root.right == null && root.val == sum){
            result.add(new ArrayList<Integer>(item));
            return;
        }
        if (root.left != null){
            item.add(root.left.val);
            dfs(result, item, root.left, sum - root.val);
            item.remove(item.size() - 1);
        }
        if (root.right != null){
            item.add(root.right.val);
            dfs(result, item, root.right, sum - root.val);
            item.remove(item.size() - 1);
        }
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), root, sum);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> item, TreeNode root, int sum){
        if (root == null){
            return;
        }
        if (root.val == sum && root.left == null && root.right == null){
            item.add(root.val);
            res.add(new ArrayList<>(item));
            item.remove(item.size() - 1);
            return;
        }
        item.add(root.val);
        dfs(res, item, root.left, sum - root.val);
        dfs(res, item, root.right, sum - root.val);
        item.remove(item.size() - 1);
    }
}

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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        dfs(res, new ArrayList<Integer>(), root, sum);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> item, TreeNode root, int sum){
        if (root.val == sum && root.left == null && root.right == null){
            item.add(root.val);
            res.add(new ArrayList<>(item));
            item.remove(item.size() - 1);
            return;
        }
        item.add(root.val);
        if (root.left != null){
            dfs(res, item, root.left, sum - root.val);
        }
        if (root.right != null){
            dfs(res, item, root.right, sum - root.val);
        }
        item.remove(item.size() - 1);
    }
}
